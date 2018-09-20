package huaminglin.demo.spring.kafka.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringKafkaContainerDemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringKafkaContainerDemo.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String msg) {
        kafkaTemplate.send("test", msg);
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("huaminglin.demo.spring.kafka.container");
        context.refresh();
        SpringKafkaContainerDemo bean = context.getBean(SpringKafkaContainerDemo.class);
        logger.info("Sending a message to topic test ...");
        bean.sendMessage("test", "hello");
        Thread.sleep(10 * 1000);
        context.close();
    }
}
