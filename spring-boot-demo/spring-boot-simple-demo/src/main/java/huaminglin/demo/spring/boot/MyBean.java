package huaminglin.demo.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MyBean {
  @Value("${user.home}")
  private String userHome;

  @Value("1628737705")
  private MyValue myValue;

  @Autowired
  private MyAwareBean myAwareBean;

  static {
    System.out.println("MyBean.static");
  }

  public MyBean() {
    System.out.println("MyBean()");
  }

  public String getUserHome() {
    return userHome;
  }

  public void setUserHome(String userHome) {
    this.userHome = userHome;
  }
}
