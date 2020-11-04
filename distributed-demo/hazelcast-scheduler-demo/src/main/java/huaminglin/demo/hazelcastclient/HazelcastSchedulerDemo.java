package huaminglin.demo.hazelcastclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HazelcastSchedulerDemo {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
        .run(HazelcastSchedulerDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }
}
