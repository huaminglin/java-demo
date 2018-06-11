package huaminglin.demo.spring.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
