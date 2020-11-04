package huaminglin.demo.spring.boot;


import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SpringBootDemo {

  private static final Logger logger = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  private static ConfigurableApplicationContext context;

  public static void main(String[] args) {
    context = SpringApplication.run(SpringBootDemo.class, args);
    logger.info("Main Thread exits: {}, {}", Thread.currentThread().getId(),
        Thread.currentThread().getName());
  }

  @KafkaListener(topics = "test", groupId = "demo")
  public void listen(String message) {
    logger.info("Received Messasge in group demo: {}", message);
    if (message.contains("quit")) {
      // Shutdown this application; it seems that the consumer never has chance to send the ack to Kafka server.
      context.close();
    }
  }

}
