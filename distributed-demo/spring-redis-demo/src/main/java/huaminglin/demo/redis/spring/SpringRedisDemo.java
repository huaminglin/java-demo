package huaminglin.demo.redis.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringRedisDemo {

  public static void main(String[] args) {

    ConfigurableApplicationContext context = SpringApplication.run(SpringRedisDemo.class, args);
    StudentService studentService = context.getBean(StudentService.class);
    studentService.save("id1", "name1");
    System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
  }
}
