package huaminglin.demo.spring.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudConsulDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulDemo.class, args);
	}
}
