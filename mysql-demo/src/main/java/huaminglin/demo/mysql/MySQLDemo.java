package huaminglin.demo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MySQLDemo {
    private static void executeSql(Connection conn, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            StringBuilder strHeader = new StringBuilder();
            for (int i = 1; i <= cols; i++) {
                String colName = rsmd.getColumnName(i);
                String colType = rsmd.getColumnTypeName(i);
                strHeader.append("\t" + colName + "(" + colType + ")");
            }
            System.out.println(strHeader.toString());
            while (rs.next()) {
                StringBuilder rowData = new StringBuilder();
                for (int i = 1; i <= cols; i++) {
                    String colType = rsmd.getColumnTypeName(i);
                    String columnString = "\t" + rs.getString(i);
                    rowData.append(columnString);
                }
                System.out.println(rowData);
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
        }
    }
    private static void executeSql(String url, String ... sqls) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            for (String sql: sqls) {
                executeSql(conn, sql);
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
        
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) { } // ignore
                conn = null;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String charsetSQL = "SELECT @@SESSION.character_set_client, @@SESSION.character_set_connection, @@SESSION.character_set_results";

        System.out.println("Default:");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&connectionCollation=latin1_swedish_ci", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterSetResults=latin1", charsetSQL, "SELECT '是'");


        System.out.println("Output result as latin1:");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1&connectionCollation=latin1_swedish_ci&characterSetResults=latin1", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1&connectionCollation=utf8_general_ci&characterSetResults=latin1", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=latin1_swedish_ci&characterSetResults=latin1", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=utf8_general_ci&characterSetResults=latin1", charsetSQL, "SELECT '是'");

        System.out.println("Output result as utf8:");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1&connectionCollation=latin1_swedish_ci&characterSetResults=utf8", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=latin1&connectionCollation=utf8_general_ci&characterSetResults=utf8", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=latin1_swedish_ci&characterSetResults=utf8", charsetSQL, "SELECT '是'");
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false&characterEncoding=utf8&connectionCollation=utf8_general_ci&characterSetResults=utf8", charsetSQL, "SELECT '是'");

        System.out.println("Conclusion: ");
        System.out.println("MySQL JDBC sends Login Request with \"Charset: utf8 COLLATE utf8_general_ci\" at MySQL Protocol::HandshakeResponse");
        System.out.println("connectionCollation has higher priority than characterEncoding when connectionCollation has a valid value.");
    }
}
