package huaminglin.demo.spring.boot.stomp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebSocketDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringWebSocketDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
