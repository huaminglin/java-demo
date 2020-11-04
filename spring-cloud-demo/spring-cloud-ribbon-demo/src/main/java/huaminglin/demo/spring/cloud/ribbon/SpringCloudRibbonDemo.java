package huaminglin.demo.spring.cloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudRibbonDemo {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudRibbonDemo.class, args);
  }

  @LoadBalanced
  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
