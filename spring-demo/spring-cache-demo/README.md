# Spring Cache Demo

## Run the applciation

mvn spring-boot:run

## Exception when fails to config the "instant" cache

```
2020-11-17 21:46:03.875 ERROR 6455 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalStateException: No cache could be resolved for 'Builder[public java.time.Instant huaminglin.demo.spring.cache.MyService.getInstant()] caches=[] | key='' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'' using resolver 'org.springframework.cache.interceptor.SimpleCacheResolver@576a4d84'. At least one cache should be provided per cache operation.] with root cause

java.lang.IllegalStateException: No cache could be resolved for 'Builder[public java.time.Instant huaminglin.demo.spring.cache.MyService.getInstant()] caches=[] | key='' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'' using resolver 'org.springframework.cache.interceptor.SimpleCacheResolver@576a4d84'. At least one cache should be provided per cache operation.
        at org.springframework.cache.interceptor.CacheAspectSupport.getCaches(CacheAspectSupport.java:256) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.cache.interceptor.CacheAspectSupport$CacheOperationContext.<init>(CacheAspectSupport.java:724) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.cache.interceptor.CacheAspectSupport.getOperationContext(CacheAspectSupport.java:266) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.cache.interceptor.CacheAspectSupport$CacheOperationContexts.<init>(CacheAspectSupport.java:615) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:346) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.cache.interceptor.CacheInterceptor.invoke(CacheInterceptor.java:61) ~[spring-context-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749) ~[spring-aop-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691) ~[spring-aop-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at huaminglin.demo.spring.cache.MyService$$EnhancerBySpringCGLIB$$daeda9b.getInstant(<generated>) ~[classes/:na]
        at huaminglin.demo.spring.cache.HelloWorldController.welcome(HelloWorldController.java:17) ~[classes/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:626) ~[tomcat-embed-core-9.0.38.jar:4.0.FR]
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:733) ~[tomcat-embed-core-9.0.38.jar:4.0.FR]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.38.jar:9.0.38]
        at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
```

## http://127.0.0.1:8080/actuator/metrics

```
{
  "names": [
    "cache.evictions",
    "cache.gets",
    "cache.puts",
    "cache.removals",
    "http.server.requests",
    "jvm.buffer.count",
    "jvm.buffer.memory.used",
    "jvm.buffer.total.capacity",
    "jvm.classes.loaded",
    "jvm.classes.unloaded",
    "jvm.gc.live.data.size",
    "jvm.gc.max.data.size",
    "jvm.gc.memory.allocated",
    "jvm.gc.memory.promoted",
    "jvm.memory.committed",
    "jvm.memory.max",
    "jvm.memory.used",
    "jvm.threads.daemon",
    "jvm.threads.live",
    "jvm.threads.peak",
    "jvm.threads.states",
    "logback.events",
    "process.cpu.usage",
    "process.files.max",
    "process.files.open",
    "process.start.time",
    "process.uptime",
    "system.cpu.count",
    "system.cpu.usage",
    "system.load.average.1m",
    "tomcat.sessions.active.current",
    "tomcat.sessions.active.max",
    "tomcat.sessions.alive.max",
    "tomcat.sessions.created",
    "tomcat.sessions.expired",
    "tomcat.sessions.rejected"
  ]
}
```

http://127.0.0.1:8080/actuator/metrics/cache.evictions
```
{"name":"cache.evictions","description":"cache evictions","baseUnit":null,"measurements":[{"statistic":"COUNT","value":0.0}],"availableTags":[{"tag":"cache","values":["instant"]},{"tag":"name","values":["instant"]},{"tag":"cacheManager","values":["cacheManager"]}]}
```

http://127.0.0.1:8080/actuator/metrics/cache.gets
```
{"name":"cache.gets","description":"The number of times cache lookup methods have returned a cached value.","baseUnit":null,"measurements":[{"statistic":"COUNT","value":0.0}],"availableTags":[{"tag":"result","values":["hit","miss"]},{"tag":"cache","values":["instant"]},{"tag":"name","values":["instant"]},{"tag":"cacheManager","values":["cacheManager"]}]}
```

http://127.0.0.1:8080/actuator/metrics/cache.removals
```
{"name":"cache.removals","description":"Cache removals","baseUnit":null,"measurements":[{"statistic":"VALUE","value":0.0}],"availableTags":[{"tag":"cache","values":["instant"]},{"tag":"name","values":["instant"]},{"tag":"cacheManager","values":["cacheManager"]}]}
```

## JConsole to check the ehcache MBeans

It seems JConsole can access JMX MBeans through local protocol.

No com.sun.management.jmxremote. config is required.

JConsole: MBeans > javax.cache > CacheConfiguration, CacheStatistics

In the CacheStatistics, there is an "instant" cache node. And there is only one Operations "clear" available.

Conclusion: It seems we can't view the cache content through JMX.

## Remote JMX config

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9099 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1"
