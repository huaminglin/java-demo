package huaminglin.demo.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWebDemo extends SpringBootServletInitializer {

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebDemo.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
