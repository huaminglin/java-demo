# Demo query encoding

## mvn spring-boot:run

mvn spring-boot:run

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.tomcat.basedir=tomcat -Dserver.tomcat.accesslog.enabled=true"

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


## curl --proxy http://127.0.0.1:6080 "http://10.0.0.211:8080/?name=a+b"

hello: a b

## curl --proxy http://127.0.0.1:6080 "http://10.0.0.211:8080/?name=a%20b"

hello: a b

## curl "http://10.0.0.211:8080/?name=a b"

<!doctype html><html lang="en"><head><title>HTTP Status 400 – Bad Request</title><style type="text/css">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP Status 400 – Bad Request</h1></body></html>

java.lang.IllegalArgumentException: Invalid character found in the HTTP protocol
        at org.apache.coyote.http11.Http11InputBuffer.parseRequestLine(Http11InputBuffer.java:553) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:260) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1594) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.33.jar:9.0.33]
        at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]

encoding-query-plus-demo/tomcat/logs/access_log.2020-05-01.log
10.0.0.211 - - [01/May/2020:16:32:33 +0800] "GET /?name=a null" 400 435

Note:
"GET http://10.0.0.211:8080/?name=a%20b HTTP/1.1" is what a normal request line looks like.
" " is used as a separator.
And the third element is expected to be the protocol.
if (!HttpParser.isHttpProtocol(chr)) { throw new IllegalArgumentException(sm.getString("iib.invalidHttpProtocol")); }

## Thread Dump on how a spring controller is called

"http-nio-8080-exec-4@5463" daemon prio=5 tid=0x19 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.encoding.query.plus.HelloController.welcome(HelloController.java:15)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)
	  at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)
	  at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)
	  at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:879)
	  at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:793)
	  at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	  at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)
	  at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)
	  at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	  at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
	  at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	  at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)
	  at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	  at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	  at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)
	  at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	  at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)
	  at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)
	  at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	  at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	  at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
	  at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:373)
	  at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
	  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)
	  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1594)
	  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	  - locked <0x177b> (a org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper)
	  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	  at java.lang.Thread.run(Thread.java:834)


## The "+" in query string is always decoded by Tomcat

org.apache.catalina.connector.RequestFacade#getParameter
org.apache.catalina.connector.Request#parseParameters
org.apache.tomcat.util.http.Parameters#handleQueryParameters
org.apache.tomcat.util.buf.MessageBytes#duplicate
org.apache.tomcat.util.http.Parameters#processParameters(byte[], int, int, java.nio.charset.Charset)
case '%': case '+': decodeName = true; decodeValue = true;
org.apache.tomcat.util.http.Parameters#urlDecode
org.apache.tomcat.util.buf.UDecoder
int idx= ByteChunk.findByte( buff, start, end, (byte) '%' );
if( query ) { idx2= ByteChunk.findByte( buff, start, (idx >= 0 ? idx : end), (byte) '+' ); }
if( buff[ j ] == '+' && query) { buff[idx]= (byte)' ' ; }

