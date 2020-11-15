# Spring Retry Demo

## mvn spring-boot:run

```
huaminglin.demo.spring.boot.SimpleRetryService#retryService
huaminglin.demo.spring.boot.SimpleRetryService#retryService
huaminglin.demo.spring.boot.SimpleRetryService#retryService
Exception in thread "main" huaminglin.demo.spring.boot.retry.MyRetryException
	at huaminglin.demo.spring.boot.retry.SimpleRetryService.retryService(SimpleRetryService.java:12)
	at huaminglin.demo.spring.boot.retry.SimpleRetryService$$FastClassBySpringCGLIB$$30197a5.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)
	at org.springframework.retry.interceptor.RetryOperationsInterceptor$1.doWithRetry(RetryOperationsInterceptor.java:91)
	at org.springframework.retry.support.RetryTemplate.doExecute(RetryTemplate.java:287)
	at org.springframework.retry.support.RetryTemplate.execute(RetryTemplate.java:164)
	at org.springframework.retry.interceptor.RetryOperationsInterceptor.invoke(RetryOperationsInterceptor.java:118)
	at org.springframework.retry.annotation.AnnotationAwareRetryOperationsInterceptor.invoke(AnnotationAwareRetryOperationsInterceptor.java:153)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)
	at huaminglin.demo.spring.boot.retry.SimpleRetryService$$EnhancerBySpringCGLIB$$4bea96df.retryService(<generated>)
	at huaminglin.demo.spring.boot.retry.SpringBootRetryDemo.main(SpringBootRetryDemo.java:15)
```
