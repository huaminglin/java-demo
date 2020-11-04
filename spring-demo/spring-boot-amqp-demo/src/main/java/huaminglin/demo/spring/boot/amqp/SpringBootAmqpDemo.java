package huaminglin.demo.spring.boot.amqp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAmqpDemo {

  @Autowired(required = false)
  private MessagePublisher publisher;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringBootAmqpDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

  @Bean
  public Queue myQueue() {
    return new Queue("myQueue", false);
  }

  @PostConstruct
  public void initialize() {
    if (publisher != null) {
      String now = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss").format(LocalDateTime.now());
      publisher.publish("myQueue", "message: " + now);
    }
  }

}
