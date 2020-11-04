package huaminglin.demo.spring.cloud.consul;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCloudConsulController {

  @RequestMapping(value = "/hello")
  public String main() {
    return "Hello World.";
  }
}
