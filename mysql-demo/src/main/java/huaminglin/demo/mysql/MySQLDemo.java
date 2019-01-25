package huaminglin.demo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MySQLDemo {

    private static ResultSet createResultSet(Connection conn, String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs == null) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { } // ignore
                    stmt = null;
                }
            }
        }
        return rs;
    }

    private static void closeStatement(ResultSet rs) {
        if (rs == null) {
            return;
        }
        Statement statement = null;
        try {
            statement = rs.getStatement();
        } catch (SQLException sqlEx) {
        }
        try {
            rs.close();
        } catch (SQLException sqlEx) {
        }
        if (statement == null) {
            try {
                statement.close();
            } catch (SQLException sqlEx) {
            }
        }
    }

    private static void printResultSet(ResultSet rs) {
        try {
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
        }
    }

    private static void executeSql(Connection conn, String sql) {
        ResultSet rs = createResultSet(conn, sql);
        if (rs == null) {
            return;
        }
        printResultSet(rs);
        closeStatement(rs);
    }

    private static Connection createConnection(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

    private static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) { } // ignore
            conn = null;
        }
    }

    private static void executeSql(String url, String ... sqls) {
        Connection conn = createConnection(url);
        if (conn == null) {
            return;
        }
        try {
            for (String sql: sqls) {
                executeSql(conn, sql);
            }
        } finally {
            closeConnection(conn);
        }
    }

    private static void demoCharacterSet() {
        String initialSQL = "/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout";
        executeSql("jdbc:mysql://localhost:6603/mysql?" +
        "user=root&password=demo&useSSL=false", initialSQL);

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

    public static void main(String[] args) throws InterruptedException, java.sql.SQLException {
        // demoCharacterSet();
        // 1548333895 -> Thu, 24 Jan 2019 12:44:55 GMT
        Connection conn = createConnection("jdbc:mysql://localhost:6603/mysql?user=root&password=demo&useSSL=false");
        if (conn == null) {
            return;
        }
        ResultSet rs = null;
        try {
            rs = createResultSet(conn, "select FROM_UNIXTIME(1548333895)");
            Time time = rs.getTime(1);
            Timestamp timestamp = rs.getTimestamp(1);
            Date date = rs.getDate(1);
            System.out.println(time);
            System.out.println(timestamp);
            System.out.println(date);
        } finally {
            closeStatement(rs);
            closeConnection(conn);
        }
    }
}
