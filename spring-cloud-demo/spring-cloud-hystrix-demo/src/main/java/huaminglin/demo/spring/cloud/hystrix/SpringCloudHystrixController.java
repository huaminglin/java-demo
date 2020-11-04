package huaminglin.demo.spring.cloud.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCloudHystrixController {

  @Autowired
  SpringCloudHystrixService service;

  @RequestMapping("/")
  public String serverLocation() {
    return this.service.serverLocation();
  }
}
