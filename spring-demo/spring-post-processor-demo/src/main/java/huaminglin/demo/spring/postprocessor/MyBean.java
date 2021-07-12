package huaminglin.demo.spring.postprocessor;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

  static {
    System.out.println("MyBean.static");
  }

  public MyBean() {
    System.out.println("MyBean()");
  }

  @PostConstruct
  public void init() {
    System.out.println("@PostConstruct: init()");
  }
}
