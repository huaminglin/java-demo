package huaminglin.demo.spring.bean.java;

import org.springframework.beans.factory.annotation.Autowired;

public class MyHandler {
  @Autowired
  private MyService service;

  public boolean isActive() {
    return service.isActive();
  }
}
