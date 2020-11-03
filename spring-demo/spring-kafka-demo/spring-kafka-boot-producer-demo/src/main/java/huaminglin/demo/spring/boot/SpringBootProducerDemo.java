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
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        String message = "producer";
        if (args.length > 0) {
            message = args[0];
        }
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootProducerDemo.class, args);
        KafkaTemplate kafkaTemplate = context.getBean(KafkaTemplate.class);
        kafkaTemplate.send("test", message);
        logger.info("Main Thread exits: {}, {}", Thread.currentThread().getId(),
            Thread.currentThread().getName());
    }

}
