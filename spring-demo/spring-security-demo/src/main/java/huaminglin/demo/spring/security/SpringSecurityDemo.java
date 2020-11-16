package huaminglin.demo.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemo {

  public static void main(String[] args)  {
    SpringApplication.run(SpringSecurityDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
