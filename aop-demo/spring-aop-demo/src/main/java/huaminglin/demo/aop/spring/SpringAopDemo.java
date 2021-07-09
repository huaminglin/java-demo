package huaminglin.demo.aop.spring;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
Hello origin
class com.sun.proxy.$Proxy10
[interface huaminglin.demo.aop.spring.MyInterface, interface org.springframework.aop.SpringProxy, interface org.springframework.aop.framework.Advised, interface org.springframework.core.DecoratingProxy]
MethodBeforeAdvice: class java.lang.reflect.Method#hello
Hello proxy
AfterReturningAdvice: class java.lang.reflect.Method#hello
 */
public class SpringAopDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan(SpringAopDemo.class.getPackage().getName());
    context.refresh();
    MyBean bean = (MyBean) context.getBean("myBean");
    bean.hello("origin");

    MyInterface myInterface = (MyInterface) context.getBean("myBeanProxy");
    System.out.println(myInterface.getClass());
    System.out.println(Arrays.toString(myInterface.getClass().getInterfaces()));
    myInterface.hello("proxy");
  }

}
