package huaminglin.demo.jdbc.spring.transaction;

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
public class SpringTransactionDemo {

  private static Logger logger = LoggerFactory.getLogger(SpringTransactionDemo.class);

  static {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();
    java.util.logging.Logger.getLogger("").setLevel(java.util.logging.Level.FINEST);
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
        .run(SpringTransactionDemo.class, args);
    TransactionService transactionService = context.getBean(TransactionService.class);
    transactionService.sql1();
    transactionService.sql2();
    transactionService.sql3();
    transactionService.sql4();
    transactionService.sql5();
    logger.info(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
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
}
