package huaminglin.demo.spring.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpringCloudHystrixService {

  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "defaultMessage")
  public String serverLocation() {
    return this.restTemplate.getForObject(
        "http://localhost:9091/", String.class);
  }

  private String defaultMessage() {
    return "Default message.";
  }
}
