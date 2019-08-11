package huaminglin.demo.spring.boot.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringBootAmqpDemo {
	@Autowired(required=false)
    private MessagePublisher publisher;

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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootAmqpDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
