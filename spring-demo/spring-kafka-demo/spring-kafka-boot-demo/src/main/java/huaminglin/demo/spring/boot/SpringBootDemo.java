package huaminglin.demo.spring.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class SpringBootDemo {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static ConfigurableApplicationContext context;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "test", groupId = "demo")
    public void listen(String message) {
        logger.info("Received Messasge in group demo: " + message);
        kafkaTemplate.send("test", "triggered by listener");
        context.close();
    }

    public static void main(String[] args) throws Exception {
        context = SpringApplication.run(SpringBootDemo.class, args);
        logger.info("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
