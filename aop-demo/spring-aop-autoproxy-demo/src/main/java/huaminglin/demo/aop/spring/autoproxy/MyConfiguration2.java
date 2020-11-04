package huaminglin.demo.aop.spring.autoproxy;

import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration2 {

  @Bean
  public NameMatchMethodPointcutAdvisor myAdvisor(MyAdvice advice) {
    NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
    advisor.setClassFilter(clazz -> clazz.getName().endsWith("MyBean"));
    advisor.setAdvice(advice);
    advisor.setMappedName("hello");
    return advisor;
  }

}
