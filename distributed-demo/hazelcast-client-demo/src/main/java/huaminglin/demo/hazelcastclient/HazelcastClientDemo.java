package huaminglin.demo.hazelcastclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HazelcastClientDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HazelcastClientDemo.class, args);
        HazelcastClientService hazelcastClientService = context.getBean(HazelcastClientService.class);
        hazelcastClientService.sendMessage("topic1", "Hello world");
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }
}
