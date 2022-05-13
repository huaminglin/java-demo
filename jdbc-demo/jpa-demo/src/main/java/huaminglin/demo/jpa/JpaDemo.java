package huaminglin.demo.jpa;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaDemo implements CommandLineRunner, ApplicationContextAware {
  ApplicationContext applicationContext;

  @Bean
  public DataSource postgresqlDataSource() {
    String url = "jdbc:postgresql://localhost:5432/pgdb";
    Properties properties = new Properties();
//        properties.put("loggerLevel", "TRACE");
    DriverManagerDataSource dataSource = new DriverManagerDataSource(url, properties);
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUsername("pgdemo");
    dataSource.setPassword("123456");

    return dataSource;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void run(String... args) {
    // If we autowire UserService to JpaDemo, circular happens.
    UserService userService = applicationContext.getBean(UserService.class);
    userService.addUser(args.length >=2, args.length > 0 ? args[1] : "user1");
  }

  public static void main(String[] args) {
    SpringApplication
        .run(JpaDemo.class, args);
  }
}