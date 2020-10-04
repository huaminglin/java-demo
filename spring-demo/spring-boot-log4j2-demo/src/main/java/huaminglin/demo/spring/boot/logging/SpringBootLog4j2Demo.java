package huaminglin.demo.spring.boot.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootLog4j2Demo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLog4j2Demo.class, args);
        Object bean = context.getBean(LoggingApplicationListener.LOGGING_SYSTEM_BEAN_NAME);
        System.out.println(LoggingApplicationListener.LOGGING_SYSTEM_BEAN_NAME + ": " + bean.getClass());
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

}
