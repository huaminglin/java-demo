package huaminglin.demo.spring.boot;


import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootProducerDemo {

  private static final Logger logger = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private static void sendFirstMessage(KafkaTemplate kafkaTemplate, String topic) {
    kafkaTemplate.send(topic, "first-message");
  }

  private static void sendSecondMessage(KafkaTemplate kafkaTemplate, String topic) {
    kafkaTemplate.send(topic, "second-message");
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
        .run(SpringBootProducerDemo.class, args);
    KafkaTemplate kafkaTemplate = context.getBean(KafkaTemplate.class);
    String topic = "my-topic";
    sendFirstMessage(kafkaTemplate, topic);
    sendSecondMessage(kafkaTemplate, topic);
    logger.info("Main Thread exits: {}, {}", Thread.currentThread().getId(),
        Thread.currentThread().getName());
  }

}
