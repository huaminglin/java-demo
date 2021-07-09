package huaminglin.demo.aop.spring.autoproxy;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
com.sun.proxy.$Proxy11
[interface huaminglin.demo.aop.spring.autoproxy.MyInterface, interface org.springframework.aop.SpringProxy, interface org.springframework.aop.framework.Advised, interface org.springframework.core.DecoratingProxy]
MethodBeforeAdvice: class java.lang.reflect.Method#hello
Hello origin
AfterReturningAdvice: class java.lang.reflect.Method#hello
 */
public class SpringAopAutoProxyDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan(SpringAopAutoProxyDemo.class.getPackage().getName());
    context.refresh();
    MyInterface bean = (MyInterface) context.getBean("myBean");
    System.out.println(bean.getClass().getName());
    System.out.println(Arrays.toString(bean.getClass().getInterfaces()));
    bean.hello("origin");
  }

}
