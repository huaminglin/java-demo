package huaminglin.demo.spring.cloud.consul.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudConsulFeignDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulFeignDemo.class, args);
    }
}
