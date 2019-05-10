import java.sql.*;
public class sql {
        public static void main(String[] args){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/mybus?user=root&password=12345678";

                Connection con = DriverManager.getConnection(url);
            } catch (Exception ex) {
            }
        }

    }
