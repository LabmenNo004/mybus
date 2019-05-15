import java.sql.*;

public class mysql {
    static Connection conn = null;
    static ResultSet rs = null;
    static Statement stmt= null;

    public static ResultSet getResult(String sql){
        getConn();
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception ex) {
            System.out.println("error");
        }
        return null;
    }
    public static void close(){
        try {
            conn.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println("error");
        }
        conn=null;
    }
    public static void getConn() {
        try {
            String url = "jdbc:mysql://localhost:3306/mybus?user=root&password=12345678" +
                    "&serverTimezone=UTC";
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public static int authentication(int type, String name, String password){
        //type=1:user;type=2:driver;type=3:admin;
        //return value is the ID; return -1 if authentication fails; return -2 if error occurs
        String tableName=null;
        String IDName=null;
        switch (type){
            case 1:{
                tableName="user";
                IDName="uid";
                break;
            }
            case 2:{
                tableName="driver";
                IDName="driver_name";
                break;
            }
            case 3:{
                tableName="admin";
                IDName="admin_id";
            }
        }
        String sql="select "+IDName+" from "+tableName+" where "+tableName+"_name="+name+" and password="+password;
        getResult(sql);
        try{
            if (!rs.next()) return -1;
            int temp=rs.getInt(IDName);
            rs.close();
            return temp;
        } catch (SQLException ex) {
            return -2;
        }
    }
}
