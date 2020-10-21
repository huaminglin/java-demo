package huaminglin.demo.spring.boot.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAdminClientDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootAdminClientDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
