import java.sql.*;

public class sql {
    static Connection conn = null;

    public static void main(String[] args) {
        LoadDriver();
        getConn();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select ID from student");
            while (rs.next()){
                System.out.println(rs.getString("ID"));
            }
            rs.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    public static void LoadDriver() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception ex) {
            }
        }

    static void getConn() {
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
