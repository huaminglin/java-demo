package huaminglin.demo.jdbc.spring.transaction;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class SpringTransactionDemo {

    @Bean
    public DataSource postgresqlDataSource() {
        String url = "jdbc:postgresql://localhost:5432/pgdb";
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, properties);
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("pgdemo");
        dataSource.setPassword("123456");
        return dataSource;
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
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }
}
