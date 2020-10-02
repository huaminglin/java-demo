package huaminglin.demo.quartz.spring;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringQuartzDemo {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringQuartzDemo.class, args);
    context.getBean(DataSource.class);
    System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
  }
}
