import java.sql.*;

public class sql {
    Connection conn = null;
    ResultSet rs = null;

    public ResultSet getResult(String sql){
        getConn();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select ID from student");
            return rs;
        } catch (Exception ex) {
            System.out.println("error");
        }
        close();
        return null;
    }
    public void close(){
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
    public void getConn() {
        try {
            String url = "jdbc:mysql://localhost:3306/mybus?user=root&password=12345678" +
                    "&serverTimezone=UTC";
            conn = DriverManager.getConnection(url);
            System.out.println(conn);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
