# web socket demo

## Run the application

1) mvn spring-boot:run


2) java -jar target/spring-boot-websocket-demo-1.0-SNAPSHOT.jar

3) customized docker image

mvn dockerfile:build
docker run -p 8080:8080 --rm huaminglin/spring-boot-websocket-demo:1.0-SNAPSHOT

4) Java docker image

sudo docker run -p 8080:8080 --name websocketdemo --rm -v $PWD/target/spring-boot-websocket-demo-1.0-SNAPSHOT.jar:/app.jar --entrypoint=/usr/bin/java openjdk:8-jdk-alpine -Djava.security.egd=file:/dev/./urandom -jar /app.jar

## Connect

```
GET /courier HTTP/1.1
Host: 127.0.0.1:8080
Connection: Upgrade
Pragma: no-cache
Cache-Control: no-cache
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36
Upgrade: websocket
Origin: http://127.0.0.1:8080
Sec-WebSocket-Version: 13
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
Sec-WebSocket-Key: dzc9SEQs0/gDDMDaiUQnCQ==
Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits

HTTP/1.1 101 
Upgrade: websocket
Connection: upgrade
Sec-WebSocket-Accept: aIJaIoX/qhSmXtkyq908aLQrmuE=
Sec-WebSocket-Extensions: permessage-deflate;client_max_window_bits=15
Date: Wed, 29 Jul 2020 14:25:15 GMT
```

## tcpdump

tcpdump -n -r websocketdemo.pcap net 172.17.0.0/24 -w websocketdemo-2.pcap

tcpdump -n -r websocketdemo.pcap

Conclusion:

There is only one TCP handshake package.

The TCP connect sends the http request, and then upgrade to websocket connection.


## Thread dump: MySocketTextHandler processes request

MySocketTextHandler is the first place to look into the system.

```
"http-nio-8080-exec-3@5671" daemon prio=5 tid=0x18 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.spring.websocket.MySocketTextHandler.handleTextMessage(SocketTextHandler.java:23)
	  at org.springframework.web.socket.handler.AbstractWebSocketHandler.handleMessage(AbstractWebSocketHandler.java:43)
	  at org.springframework.web.socket.handler.WebSocketHandlerDecorator.handleMessage(WebSocketHandlerDecorator.java:75)
	  at org.springframework.web.socket.handler.LoggingWebSocketHandlerDecorator.handleMessage(LoggingWebSocketHandlerDecorator.java:56)
	  at org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator.handleMessage(ExceptionWebSocketHandlerDecorator.java:58)
	  at org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter.handleTextMessage(StandardWebSocketHandlerAdapter.java:113)
	  at org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter.access$000(StandardWebSocketHandlerAdapter.java:42)
	  at org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter$3.onMessage(StandardWebSocketHandlerAdapter.java:84)
	  at org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter$3.onMessage(StandardWebSocketHandlerAdapter.java:81)
	  at org.apache.tomcat.websocket.WsFrameBase.sendMessageText(WsFrameBase.java:395)
	  at org.apache.tomcat.websocket.server.WsFrameServer.sendMessageText(WsFrameServer.java:119)
	  at org.apache.tomcat.websocket.WsFrameBase.processDataText(WsFrameBase.java:495)
	  at org.apache.tomcat.websocket.WsFrameBase.processData(WsFrameBase.java:294)
	  at org.apache.tomcat.websocket.WsFrameBase.processInputBuffer(WsFrameBase.java:133)
	  at org.apache.tomcat.websocket.server.WsFrameServer.onDataAvailable(WsFrameServer.java:82)
	  at org.apache.tomcat.websocket.server.WsFrameServer.doOnDataAvailable(WsFrameServer.java:171)
	  at org.apache.tomcat.websocket.server.WsFrameServer.notifyDataAvailable(WsFrameServer.java:151)
	  at org.apache.tomcat.websocket.server.WsHttpUpgradeHandler.upgradeDispatch(WsHttpUpgradeHandler.java:148)
	  at org.apache.coyote.http11.upgrade.UpgradeProcessorInternal.dispatch(UpgradeProcessorInternal.java:54)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:53)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x1db5> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)
```

org.apache.coyote.http11.upgrade.UpgradeProcessorInternal

org.apache.tomcat.websocket.server.WsFrameServer

org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter


## Thread dump: Register MySocketTextHandler instance

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.spring.websocket.WebSocketConfig.registerWebSocketHandlers(WebSocketConfig.java:14)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration.registerWebSocketHandlers(DelegatingWebSocketConfiguration.java:51)
	  at org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport.initHandlerRegistry(WebSocketConfigurationSupport.java:55)
	  at org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport.webSocketHandlerMapping(WebSocketConfigurationSupport.java:43)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$13ec0c01.CGLIB$webSocketHandlerMapping$2(<generated>:-1)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$13ec0c01$$FastClassBySpringCGLIB$$6d82b4f.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:244)
	  at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:363)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$13ec0c01.webSocketHandlerMapping(<generated>:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SiWebSocketHandlerMappingmpleInstantiationStrategy.java:154)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:622)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:456)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1321)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1160)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$152.35534346.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  - locked <0x19eb> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:849)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
	  - locked <0x19ec> (a java.lang.Object)
	  at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:142)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:775)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:316)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1260)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1248)
	  at huaminglin.demo.spring.websocket.SpringWebSocketDemo.main(SpringWebSocketDemo.java:10)
```

org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration

@EnableWebSocket configures class DelegatingWebSocketConfiguration.

DelegatingWebSocketConfiguration invokes all WebSocketConfigurer instances.


## Thread dump: Where is MySocketTextHandler mapping stored?

org.springframework.web.socket.server.support.WebSocketHandlerMapping

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.web.socket.server.support.WebSocketHandlerMapping.<init>(WebSocketHandlerMapping.java:35)
	  at org.springframework.web.socket.config.annotation.ServletWebSocketHandlerRegistry.getHandlerMapping(ServletWebSocketHandlerRegistry.java:125)
	  at org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport.webSocketHandlerMapping(WebSocketConfigurationSupport.java:49)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$b30afae9.CGLIB$webSocketHandlerMapping$2(<generated>:-1)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$b30afae9$$FastClassBySpringCGLIB$$8e7d450c.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:244)
	  at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:363)
	  at org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration$$EnhancerBySpringCGLIB$$b30afae9.webSocketHandlerMapping(<generated>:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:622)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:456)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1321)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1160)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$151.1201466784.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  - locked <0x1495> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:849)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
	  - locked <0x1496> (a java.lang.Object)
	  at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:142)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:775)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:316)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1260)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1248)
	  at huaminglin.demo.spring.websocket.SpringWebSocketDemo.main(SpringWebSocketDemo.java:10)
```

```
0 = {SimpleUrlHandlerMapping@7511}
1 = {RequestMappingHandlerMapping@7512}
2 = {WebSocketHandlerMapping@7513}
3 = {BeanNameUrlHandlerMapping@7514}
4 = {WelcomePageHandlerMapping@7515}
5 = {SimpleUrlHandlerMapping@7516}

WebSocketHandlerMapping
handlerMap = {LinkedHashMap@7518}  size = 1
 "/courier" -> {WebSocketHttpRequestHandler@7534}
```

## Thread dump: connect (Locate MySocketTextHandler and register it to the tomcat container)

Track StandardWebSocketHandlerAdapter construction to check when our MySocketTextHandler is located for web socket request

```
"http-nio-8080-exec-2@6993" daemon prio=5 tid=0x17 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter.<init>(StandardWebSocketHandlerAdapter.java:52)
	  at org.springframework.web.socket.server.standard.AbstractStandardUpgradeStrategy.upgrade(AbstractStandardUpgradeStrategy.java:129)
	  at org.springframework.web.socket.server.support.AbstractHandshakeHandler.doHandshake(AbstractHandshakeHandler.java:290)
	  at org.springframework.web.socket.server.support.WebSocketHttpRequestHandler.handleRequest(WebSocketHttpRequestHandler.java:167)
	  at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:53)
	  at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	  at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	  at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	  at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	  at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)
	  at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	  at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)
	  at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	  at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	  at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	  at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	  at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x1c89> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)
```

org.springframework.web.servlet.DispatcherServlet.doDispatch

```
"http-nio-8080-exec-2@6992" daemon prio=5 tid=0x17 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.web.servlet.DispatcherServlet.getHandlerAdapter(DispatcherServlet.java:1266)
	  at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1021)
	  at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	  at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	  at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	  at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)
	  at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	  at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)
	  at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	  at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	  at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	  at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	  at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x1c99> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)
```


How does the Spring WebSocketHandlerMapping inject into Tomcat container behavior?

TomcatRequestUpgradeStrategy calls WsServerContainer.

```
"http-nio-8080-exec-2@5670" daemon prio=5 tid=0x17 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.apache.tomcat.websocket.server.UpgradeUtil.doUpgrade(UpgradeUtil.java:183)
	  at org.apache.tomcat.websocket.server.WsServerContainer.doUpgrade(WsServerContainer.java:294)
	  at org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy.upgradeInternal(TomcatRequestUpgradeStrategy.java:72)
	  at org.springframework.web.socket.server.standard.AbstractStandardUpgradeStrategy.upgrade(AbstractStandardUpgradeStrategy.java:136)
	  at org.springframework.web.socket.server.support.AbstractHandshakeHandler.doHandshake(AbstractHandshakeHandler.java:290)
	  at org.springframework.web.socket.server.support.WebSocketHttpRequestHandler.handleRequest(WebSocketHttpRequestHandler.java:167)
	  at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:53)
	  at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	  at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	  at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	  at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	  at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)
	  at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	  at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)
	  at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	  at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	  at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	  at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	  at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x1e0a> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)
```

## Thread dump: WsHttpUpgradeHandler

WsHttpUpgradeHandler.preInit

```
"http-nio-8080-exec-2@5668" daemon prio=5 tid=0x17 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.apache.tomcat.websocket.server.WsHttpUpgradeHandler.preInit(WsHttpUpgradeHandler.java:88)
	  at org.apache.tomcat.websocket.server.UpgradeUtil.doUpgrade(UpgradeUtil.java:236)
	  at org.apache.tomcat.websocket.server.WsServerContainer.doUpgrade(WsServerContainer.java:294)
	  at org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy.upgradeInternal(TomcatRequestUpgradeStrategy.java:72)
	  at org.springframework.web.socket.server.standard.AbstractStandardUpgradeStrategy.upgrade(AbstractStandardUpgradeStrategy.java:136)
	  at org.springframework.web.socket.server.support.AbstractHandshakeHandler.doHandshake(AbstractHandshakeHandler.java:290)
	  at org.springframework.web.socket.server.support.WebSocketHttpRequestHandler.handleRequest(WebSocketHttpRequestHandler.java:167)
	  at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:53)
	  at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	  at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	  at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	  at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	  at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:92)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)
	  at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	  at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)
	  at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	  at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	  at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	  at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	  at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x1d78> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)
```

WsHttpUpgradeHandler.init

```
init:102, WsHttpUpgradeHandler (org.apache.tomcat.websocket.server)
process:880, AbstractProtocol$ConnectionHandler (org.apache.coyote)
doRun:1415, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)
runWorker:1128, ThreadPoolExecutor (java.util.concurrent)
run:628, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:834, Thread (java.lang)
```

Use "Copy Stack" of Intellij Debugger View; Thread Dump doesn't work for unknown reason.

org.apache.coyote.AbstractProtocol.ConnectionHandler.process
                    state = processor.process(wrapper, status);
                    if (state == SocketState.UPGRADING) {
                        UpgradeToken upgradeToken = processor.getUpgradeToken();
                            HttpUpgradeHandler httpUpgradeHandler = upgradeToken.getHttpUpgradeHandler();
                            // Release the Http11 processor to be re-used
                            release(processor);
                            // Create the upgrade processor
                            processor = getProtocol().createUpgradeProcessor(wrapper, upgradeToken);
                            // Associate with the processor with the connection
                            connections.put(socket, processor);
                            httpUpgradeHandler.init((WebConnection) processor);


"state = processor.process(wrapper, status);" creates UpgradeToken and WsHttpUpgradeHandler.preInit;
After upgrade, it calls WsHttpUpgradeHandler.init(), and associate processor with websocket.

Question: What actually does "processor = getProtocol().createUpgradeProcessor(wrapper, upgradeToken);" do?
Does it associate websocket connection and the MySocketTextHandler?
Answer:
```
  org.apache.coyote.http11.AbstractHttp11Protocol.createUpgradeProcessor
  HttpUpgradeHandler httpUpgradeHandler = upgradeToken.getHttpUpgradeHandler();
  WsHttpUpgradeHandler: private ServerEndpointConfig serverEndpointConfig;
  WsPerSessionServerEndpointConfig: private final ServerEndpointConfig perEndpointConfig
  ServerEndpointRegistration: private final String path; private final Endpoint endpoint;
  In this way, a websocket connection can find StandardWebSocketHandlerAdapter (this is an endpoint).
```

## Save org.apache.coyote.UpgradeToken to Http11Processor

org.apache.catalina.connector.Request.upgrade
UpgradeToken upgradeToken = new UpgradeToken(handler, getContext(), instanceManager);
coyoteRequest.action(ActionCode.UPGRADE, upgradeToken);

org.apache.coyote.Request.action
hook.action(actionCode, param);
private volatile ActionHook hook -> org.apache.coyote.http11.Http11Processor

org.apache.coyote.AbstractProcessor.action

org.apache.coyote.http11.Http11Processor
private UpgradeToken upgradeToken = null;
this.upgradeToken = upgradeToken;

