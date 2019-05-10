import java.util.HashSet;

public class main {
    LineList[] lists = fetchList();
    int minPrice=99;
    int mintime=999;

    public void findPath(String from, String to) {
        findPath(from, to, null, new HashSet<String>(), new HashSet<String>(), "->",0,0,0); //node为null则为换乘，不为null则还没下车
    }

    public void findPath(String from, String to, LineNode transferFrom, HashSet<String> visitedLine,
                         HashSet<String> visitedStation, String pre,int transferCount,int time,int price) {
        HashSet<String> stationCopy = new HashSet<>(visitedStation); //复制临时hashset
        if (!stationCopy.add(from)) return; //加入失败说明之前已经来过此站
        if (transferFrom != null) findPath(from, to, transferFrom.next, visitedLine, stationCopy, pre,transferCount); //坐下一站
        int lineCount = lists.length;
        for (int i = 0; i < lineCount; i++) {   //从所有路线中搜索此站
            LineNode temp = lists[i].search(from);
            if (temp != null && !visitedLine.contains(lists[i].lineName)) { //搜索到此站
                if (lists[i].canReach(from, to)) {//可直达终点
                    System.out.println(pre + "\tTake Route " + lists[i].lineName + " until you get to " + to + ".");
                    return;
                }
                //转车
                HashSet<String> lineCopy = new HashSet<>(visitedLine);
                lineCopy.add(lists[i].lineName);
                if (pre.length() > 4){
                    pre += "\tGet off at " + from + ";\n\t and take Route " + lists[i].lineName + ";";
                }
                else pre += "\tTake Route " + lists[i].lineName + ";";
                findPath(temp.getNextStation(), to, temp.next, lineCopy, stationCopy, pre,transferCount+1);
            }
        }
    }

    public LineList[] fetchList() {

    }

}