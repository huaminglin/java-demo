package huaminglin.demo.spring.bean.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

  @Bean MyHandler myHandler() {
    return new MyHandler();
  }
}
