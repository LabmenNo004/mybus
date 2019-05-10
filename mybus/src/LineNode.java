public class LineNode {
    private String stationName;
    private String nextStation;
    LineNode next=null;

    LineNode(String stationName,String nextStation){
        this.stationName=stationName;
        this.nextStation=nextStation;
    }
    public String getStationName() {
        return stationName;
    }

    public String getNextStation() {
        return nextStation;
    }
}
