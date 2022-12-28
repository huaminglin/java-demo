package huaminglin.demo.spring.boot;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemo {

  private static final Logger logger = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDemo.class, args);
    logger.info("Main Thread exits: {}, {}", Thread.currentThread().getId(),
        Thread.currentThread().getName());
  }

}
