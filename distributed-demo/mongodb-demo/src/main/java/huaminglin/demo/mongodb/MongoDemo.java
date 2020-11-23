package huaminglin.demo.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDemo {

  public static void main(String[] args)  {
    SpringApplication.run(MongoDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
