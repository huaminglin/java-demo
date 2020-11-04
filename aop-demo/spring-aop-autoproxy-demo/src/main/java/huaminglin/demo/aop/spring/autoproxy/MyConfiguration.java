package huaminglin.demo.aop.spring.autoproxy;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

public class MyConfiguration {

  @Bean
  public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
    // Bean 'myConfiguration' of type [huaminglin.demo.aop.spring.autoproxy.MyConfiguration$$EnhancerBySpringCGLIB$$ab45cdf5] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
    DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
    creator.setAdvisorBeanNamePrefix("myAdvisor");
    return creator;
  }

}
