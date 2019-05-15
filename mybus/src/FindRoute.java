import java.sql.ResultSet;
import java.util.HashSet;

public class FindRoute {

    static LineList[] lists = fetchList();
    int mintime = 999;
    String out;

    public void findPath(String from, String to) {
        findPath(from, to, null, new HashSet<String>(), new HashSet<String>(), "->", 0, 0, 0); //node为null则为换乘，不为null则还没下车
        System.out.println(out);
        mintime=200;
        out=null;
    }

    public void findPath(String from, String to, LineNode currentBus, HashSet<String> visitedLine,
                         HashSet<String> visitedStation, String pre, int transferCount, int time, int price) {

        if (time > mintime) return;

        HashSet<String> stationCopy = new HashSet<>(visitedStation); //复制临时hashset
        if (stationCopy.contains(from)) return; //加入失败说明之前已经来过此站
        stationCopy.add(from);
        if (currentBus != null&&currentBus.next!=null) findPath(currentBus.next.getStationName(), to, currentBus.next, visitedLine,
                stationCopy, pre, transferCount, time + currentBus.time, price); //坐下一站

        if (transferCount < 4) {  //转车次数<4时就下车
            int lineCount = lists.length;
            for (int i = 0; i < lineCount; i++) {   //从所有路线中搜索此站
                LineNode temp = lists[i].search(from);
                if(temp==null||temp.next==null) return;
                if (!visitedLine.contains(lists[i].lineName)) { //搜索到此站
                    if (lists[i].canReach(from, to)) {//可直达终点
                        while (temp.next != null && !temp.getStationName().equals(to)) {
                            time += temp.time;
                            temp = temp.next;
                        }
                        if (time < mintime) {
                            mintime = time;
                            out = pre + "\tTake Route " + lists[i].lineName + " until you get to " + to + "." + "\nExpected time: " + time + " minutes.";
                        }
                        return;
                    }

                    //转车

                    HashSet<String> lineCopy = new HashSet<>(visitedLine);
                    lineCopy.add(lists[i].lineName);
                    if (pre.length() > 4) {
                        pre += "\tGet off at " + from + ";\t and take Route " + lists[i].lineName + ";";
                    } else pre += "\tTake Route " + lists[i].lineName + ";";

                    if(temp.next!=null)
                    findPath(temp.next.getStationName(), to, temp.next, lineCopy,
                            stationCopy, pre, transferCount + 1, time+temp.time + 10, price + lists[i].price);
                }
            }
        }
    }

    public static LineList[] fetchList() {
        LineList[] lists = new LineList[2];
        String sql = "select * from line where line_id=" + 532;
        ResultSet rs = mysql.getResult(sql);
        try {
            rs.next();
            lists[0] = new LineList(rs.getString("line_name"), rs.getInt("price"));
            lists[1] = new LineList(rs.getString("line_name"), rs.getInt("price"));
            mysql.close();

            sql = "select * from station_line where line_id=" + 532 + " order by station_sequence";
            rs = mysql.getResult(sql);
            while (rs.next()) {
                lists[0].addNode(new LineNode(rs.getString("station_name"), rs.getInt("next_time")));
            }
            mysql.close();
            sql = "select * from station_line where line_id=" + 532 + " order by station_sequence desc";
            rs = mysql.getResult(sql);
            while (rs.next()) {
                lists[1].addNode(new LineNode(rs.getString("station_name"), rs.getInt("next_time")));
            }
            mysql.close();

/*        LineList[] lists = new LineList[597 * 2];
        for (int i = 0; i < 597; i++) {
            String sql = "select * from line where line_id=" + (i + 1);
            ResultSet rs = mysql.getResult(sql);
            try {
                rs.next();
                lists[i] = new LineList(rs.getString("line_name"), rs.getInt("price"));
                lists[i + 597] = new LineList(rs.getString("line_name"), rs.getInt("price"));
                mysql.close();

                sql = "select * from station_line where line_id=" + (i + 1) + " order by station_sequence";
                rs = mysql.getResult(sql);
                while (rs.next()) {
                    lists[i].addNode(new LineNode(rs.getString("station_name"), rs.getInt("next_time")));
                }
                mysql.close();
                sql = "select * from station_line where line_id=" + (i + 1) + " order by station_sequence desc";
                rs = mysql.getResult(sql);
                while (rs.next()) {
                    lists[i + 597].addNode(new LineNode(rs.getString("station_name"), rs.getInt("next_time")));
                }
                mysql.close();*/
            } catch (Exception e) {
            }

        return lists;
    }
}
