package huaminglin.demo.spring.cloud.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudEurekaConsumerDemo {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaConsumerDemo.class, args);
  }
}
