package huaminglin.demo.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaClientDemo {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaClientDemo.class, args);
  }
}
