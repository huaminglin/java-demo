package huaminglin.demo.aop.spring;

import java.util.Arrays;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

/*
class huaminglin.demo.aop.spring.MyBean$$EnhancerBySpringCGLIB$$57752f2f
[interface org.springframework.aop.SpringProxy, interface org.springframework.aop.framework.Advised, interface org.springframework.cglib.proxy.Factory]
MethodBeforeAdvice: class java.lang.reflect.Method#hello
Hello name
AfterReturningAdvice: class java.lang.reflect.Method#hello
 */
public class ProxyFactoryDemo {

  public static void main(String[] args) {
    MyBean target = new MyBean();
    Advice advice = new MyAdvice();

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvice(advice);
    pf.setTarget(target);

    MyBean proxy = (MyBean) pf.getProxy();
    System.out.println(proxy.getClass());
    System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));

    proxy.hello("name");
  }
}
