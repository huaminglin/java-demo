package huaminglin.demo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MySQLDemo {
    private static void executeSql(String url, String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn =
            DriverManager.getConnection(url);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String value = rs.getString(1);
                System.out.println(value);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
        
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) { } // ignore
                conn = null;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false", "SELECT '是'");
        // "是" is print.

        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1", "SELECT '是'");
        // "?" is print.

        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1&connectionCollation=utf8", "SELECT '是'");
        // "?" is print.

        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=utf8", "SELECT '是'");
        // "是" is print.

        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=utf8&characterSetResults=latin1", "SELECT '是'");
        // "?" is print.
    }
}
