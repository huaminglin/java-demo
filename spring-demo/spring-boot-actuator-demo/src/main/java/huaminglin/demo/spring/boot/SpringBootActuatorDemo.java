package huaminglin.demo.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootActuatorDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootActuatorDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
