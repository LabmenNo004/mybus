public class LineList {
    LineNode start=new LineNode("start",0);
    String lineName;
    int price;
    LineList(String lineName,int price){
        this.lineName=lineName;
        this.price=price;
    }
    public void addNode (LineNode node){
        LineNode cur=start;
        while (cur.next!=null) cur=cur.next;
        cur.next=node;
    }
    public boolean canReach(String from,String dest){
        for (LineNode cur=search(from);cur!=null;cur=cur.next)
            if (cur.getStationName().equals(dest)) return true;
        return false;
    }
    public void addAfter(LineNode tar,LineNode node){
        node.next=tar.next;
        tar.next=node;
    }
    public boolean addAfter(String name,LineNode node){
        LineNode tar=search(name);
        if (tar==null) return false;
        node.next=tar.next;
        tar.next=node;
        return true;
    }
    public LineNode search (String name){
        LineNode cur=start.next;
        while (cur!=null){
            if (cur.getStationName().equals(name)) return cur;
            cur=cur.next;
        }
        return null;
    }
}
