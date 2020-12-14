package huaminglin.demo.spring.typeconversion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  @Value("myname")
  private MyName name;

  @Value("89")
  private MyAge age;

  public MyName getName() {
    return name;
  }

  public void setName(MyName name) {
    this.name = name;
  }

  public MyAge getAge() {
    return age;
  }

  public void setAge(MyAge age) {
    this.age = age;
  }
}
