public class LineNode {
    private String stationName;
    int time;
    LineNode next=null;

    LineNode(String stationName,int time){
        this.stationName=stationName;
        this.time=time;
    }
    public String getStationName() {
        return stationName;
    }

}
