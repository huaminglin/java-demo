package huaminglin.demo.jdbc.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariJdbcDemo {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl( "jdbc:postgresql://host/database" );
        config.setUsername( "database_username" );
        config.setPassword( "database_password" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        HikariDataSource ds = new HikariDataSource( config );
        System.out.println(ds);
    }
}
