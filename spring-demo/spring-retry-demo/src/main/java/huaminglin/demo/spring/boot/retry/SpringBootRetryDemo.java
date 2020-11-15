package huaminglin.demo.spring.boot.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringBootRetryDemo {

  public static void main(String[] args) throws MyRetryException {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootRetryDemo.class, args);
    RetryService service = context.getBean(RetryService.class);
    service.retryService();
    System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
  }

}
