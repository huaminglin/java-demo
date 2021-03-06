package huaminglin.demo.aop.spring;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
public class MyAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

  public void before(Method method, Object[] args, Object target) {
    System.out.println("MethodBeforeAdvice: " + method.getClass() + "#" + method.getName());
  }

  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
    System.out.println("AfterReturningAdvice: " + method.getClass() + "#" + method.getName());
  }
}
