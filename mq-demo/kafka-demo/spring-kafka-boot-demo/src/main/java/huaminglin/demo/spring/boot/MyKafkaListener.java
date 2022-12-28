package huaminglin.demo.spring.boot;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MyKafkaListener {

  private static final Logger logger = LoggerFactory
          .getLogger(MethodHandles.lookup().lookupClass());
  private static ConfigurableApplicationContext context;

  @KafkaListener(topics = "${mytopic}")
  public void listen(String message) {
    logger.info("Received Messasge in group demo: {}", message);
    if (message.contains("quit")) {
      // Shutdown this application; it seems that the consumer never has chance to send the ack to Kafka server.
      context.close();
    }
  }
}
