package huaminglin.demo.aop.spring;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryDemo {

  public static void main(String[] args) {
    MyBean target = new MyBean();
    Advice advice = new MyAdvice();

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvice(advice);
    pf.setTarget(target);

    MyBean proxy = (MyBean) pf.getProxy();

    proxy.hello("name");
  }
}
