package huaminglin.demo.aop.spring;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

  @Bean("myBeanProxy")
  public ProxyFactoryBean proxyFactoryBean(@Autowired MyBean mybean) throws ClassNotFoundException {
    ProxyFactoryBean bean = new ProxyFactoryBean();
    bean.setTarget(mybean);
    bean.setProxyInterfaces(new Class[]{MyInterface.class});
    bean.setInterceptorNames("myAdvice");
    return bean;
  }
}
