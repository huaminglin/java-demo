package huaminglin.demo.spring.redis.cache;

import java.time.Instant;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  @Autowired
  private MyService service;

  @RequestMapping("/hello")
  public String welcome() {
    return "hello world: " + service.getInstant();
  }
}
