package huaminglin.demo.spring.typeconversion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  @Value("${my.service.name}")
  private MyName name;

  public MyName getName() {
    return name;
  }

  public void setName(MyName name) {
    this.name = name;
  }
}
