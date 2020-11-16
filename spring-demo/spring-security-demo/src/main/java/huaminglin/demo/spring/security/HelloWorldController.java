package huaminglin.demo.spring.security;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @RequestMapping("/hello")
  public String welcome(HttpSession httpSession) {
    Integer count = (Integer) httpSession.getAttribute("count");
    if (count == null) {
      count = 0;
    }
    count++;
    httpSession.setAttribute("count", count);
    return "hello world: " + count;
  }
}
