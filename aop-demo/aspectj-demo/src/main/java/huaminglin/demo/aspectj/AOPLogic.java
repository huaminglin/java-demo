package huaminglin.demo.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPLogic {

  @Pointcut("execution (public * *.execute(..)) && this(huaminglin.demo.aspectj.BusinessLogic)" +
      " && args(value)")
  public void callAt(String value) {
  }

  @Around("callAt(value)")
  public Object around(ProceedingJoinPoint pjp, String value) throws Throwable {
    System.out.println(pjp.getSignature());
    return "{" + pjp.proceed() + "}";
  }
}
