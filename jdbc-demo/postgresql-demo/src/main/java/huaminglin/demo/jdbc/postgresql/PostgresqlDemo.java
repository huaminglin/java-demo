package huaminglin.demo.jdbc.postgresql;

import java.util.Properties;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class PostgresqlDemo {

  private static Logger logger = LoggerFactory.getLogger(PostgresqlDemo.class);

  static {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.FINEST);
  }

  @Bean
  public DataSource postgresqlDataSource() {
    String url = "jdbc:postgresql://localhost:5432/pgdb";
    Properties properties = new Properties();
//        properties.put("loggerLevel", "TRACE");
    DriverManagerDataSource dataSource = new DriverManagerDataSource(url, properties);
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUsername("pgdemo");
    dataSource.setPassword("123456");

    DataSource dataSourceProxy = ProxyDataSourceBuilder.create(dataSource).logQueryBySlf4j(
        SLF4JLogLevel.INFO).build();
    return dataSourceProxy;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
        .run(PostgresqlDemo.class, args);
    DbTimeZoneService dbTimeZoneService = context.getBean(DbTimeZoneService.class);
//        dbTimeZoneService.jvm_timezone();
//        dbTimeZoneService.session_timezone();
//    dbTimeZoneService.insertRows(null);
//    dbTimeZoneService.insertRows("UTC");
    context.getBean(TableNamePlaceholderPreparedStatementService.class).execute();
    logger.info(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }
}
