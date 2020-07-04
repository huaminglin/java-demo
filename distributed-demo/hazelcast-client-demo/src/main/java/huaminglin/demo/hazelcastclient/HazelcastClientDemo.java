package huaminglin.demo.hazelcastclient;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.MessageListener;
import huaminglin.demo.hazelcastclient.mq.HazelcastClientPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastClientDemo {

    @Bean
    public ITopic<String> hazelcastTopic(HazelcastInstance hazelcastInstance, MessageListener messageListener) {
        ITopic<String> topic = hazelcastInstance.<String>getTopic("topic1");
        topic.addMessageListener(messageListener);
        return topic;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HazelcastClientDemo.class, args);
        HazelcastClientPublisher publisher = context.getBean(HazelcastClientPublisher.class);
        publisher.sendMessage("Hello world");
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }
}
