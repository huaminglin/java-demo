A normal war app can stop the whole tomcat server

## ParallelWebappClassLoader

http://127.0.0.1:8080/classloader/info

```
this.getClass().getClassLoader():

ParallelWebappClassLoader
  context: classloader
  delegate: false
----------> Parent Classloader:
java.net.URLClassLoader@6fc6f14e

Name: null
file:/usr/local/tomcat/webapps/classloader/WEB-INF/classes/

java.net.URLClassLoader@6fc6f14e
Name: null
file:/usr/local/tomcat/lib/
file:/usr/local/tomcat/lib/tomcat-util.jar
file:/usr/local/tomcat/lib/annotations-api.jar
file:/usr/local/tomcat/lib/tomcat-util-scan.jar
file:/usr/local/tomcat/lib/jasper-el.jar
file:/usr/local/tomcat/lib/catalina-ssi.jar
file:/usr/local/tomcat/lib/tomcat-i18n-cs.jar
file:/usr/local/tomcat/lib/tomcat-jni.jar
file:/usr/local/tomcat/lib/websocket-api.jar
file:/usr/local/tomcat/lib/catalina-ant.jar
file:/usr/local/tomcat/lib/tomcat-jdbc.jar
file:/usr/local/tomcat/lib/catalina.jar
file:/usr/local/tomcat/lib/tomcat-i18n-pt-BR.jar
file:/usr/local/tomcat/lib/servlet-api.jar
file:/usr/local/tomcat/lib/tomcat-i18n-fr.jar
file:/usr/local/tomcat/lib/catalina-ha.jar
file:/usr/local/tomcat/lib/tomcat-dbcp.jar
file:/usr/local/tomcat/lib/tomcat-api.jar
file:/usr/local/tomcat/lib/tomcat-i18n-ko.jar
file:/usr/local/tomcat/lib/tomcat-i18n-de.jar
file:/usr/local/tomcat/lib/tomcat-i18n-zh-CN.jar
file:/usr/local/tomcat/lib/catalina-tribes.jar
file:/usr/local/tomcat/lib/tomcat-websocket.jar
file:/usr/local/tomcat/lib/jasper.jar
file:/usr/local/tomcat/lib/jsp-api.jar
file:/usr/local/tomcat/lib/tomcat-coyote.jar
file:/usr/local/tomcat/lib/tomcat-i18n-ru.jar
file:/usr/local/tomcat/lib/ecj-4.15.jar
file:/usr/local/tomcat/lib/tomcat-i18n-es.jar
file:/usr/local/tomcat/lib/tomcat-i18n-ja.jar
file:/usr/local/tomcat/lib/jaspic-api.jar
file:/usr/local/tomcat/lib/el-api.jar
file:/usr/local/tomcat/lib/catalina-storeconfig.jar

jdk.internal.loader.ClassLoaders$AppClassLoader@277050dc

jdk.internal.loader.ClassLoaders$PlatformClassLoader@340da44c
```

## http://127.0.0.1:8080/classloader/stop

```
sudo docker logs -f docker_tomcat_1
NOTE: Picked up JDK_JAVA_OPTIONS:  --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
12-Oct-2020 16:50:40.929 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version name:   Apache Tomcat/9.0.38
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server built:          Sep 10 2020 08:20:30 UTC
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version number: 9.0.38.0
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Name:               Linux
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Version:            5.4.0-48-generic
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Architecture:          amd64
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Java Home:             /opt/java/openjdk
12-Oct-2020 16:50:40.932 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Version:           11.0.8+10
12-Oct-2020 16:50:40.933 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Vendor:            AdoptOpenJDK
12-Oct-2020 16:50:40.933 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_BASE:         /usr/local/tomcat
12-Oct-2020 16:50:40.933 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_HOME:         /usr/local/tomcat
12-Oct-2020 16:50:40.940 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.lang=ALL-UNNAMED
12-Oct-2020 16:50:40.940 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.io=ALL-UNNAMED
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.config.file=/usr/local/tomcat/conf/logging.properties
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djdk.tls.ephemeralDHKeySize=2048
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.protocol.handler.pkgs=org.apache.catalina.webresources
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dorg.apache.catalina.security.SecurityListener.UMASK=0027
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dignore.endorsed.dirs=
12-Oct-2020 16:50:40.941 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.base=/usr/local/tomcat
12-Oct-2020 16:50:40.942 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.home=/usr/local/tomcat
12-Oct-2020 16:50:40.942 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.io.tmpdir=/usr/local/tomcat/temp
12-Oct-2020 16:50:40.944 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent Loaded Apache Tomcat Native library [1.2.25] using APR version [1.6.3].
12-Oct-2020 16:50:40.944 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent APR capabilities: IPv6 [true], sendfile [true], accept filters [false], random [true].
12-Oct-2020 16:50:40.944 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent APR/OpenSSL configuration: useAprConnector [false], useOpenSSL [true]
12-Oct-2020 16:50:40.946 INFO [main] org.apache.catalina.core.AprLifecycleListener.initializeSSL OpenSSL successfully initialized [OpenSSL 1.1.1  11 Sep 2018]
12-Oct-2020 16:50:41.252 INFO [main] org.apache.coyote.AbstractProtocol.init Initializing ProtocolHandler ["http-nio-8080"]
12-Oct-2020 16:50:41.275 INFO [main] org.apache.catalina.startup.Catalina.load Server initialization in [488] milliseconds
12-Oct-2020 16:50:41.311 INFO [main] org.apache.catalina.core.StandardService.startInternal Starting service [Catalina]
12-Oct-2020 16:50:41.311 INFO [main] org.apache.catalina.core.StandardEngine.startInternal Starting Servlet engine: [Apache Tomcat/9.0.38]
12-Oct-2020 16:50:41.322 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/usr/local/tomcat/webapps/classloader.war]
12-Oct-2020 16:50:41.575 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/usr/local/tomcat/webapps/classloader.war] has finished in [253] ms
12-Oct-2020 16:50:41.579 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
12-Oct-2020 16:50:41.589 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [314] milliseconds
daemon.stopServer() from huaminglin.demo.servlet.classloader.StopServlet
12-Oct-2020 16:51:53.216 INFO [http-nio-8080-exec-4] org.apache.coyote.AbstractProtocol.pause Pausing ProtocolHandler ["http-nio-8080"]
12-Oct-2020 16:51:53.223 INFO [http-nio-8080-exec-4] org.apache.catalina.core.StandardService.stopInternal Stopping service [Catalina]
12-Oct-2020 16:51:53.226 INFO [http-nio-8080-exec-4] org.apache.catalina.core.StandardWrapper.unload Waiting for [1] instance(s) to be deallocated for Servlet [huaminglin.demo.servlet.classloader.StopServlet]
12-Oct-2020 16:51:54.228 INFO [http-nio-8080-exec-4] org.apache.catalina.core.StandardWrapper.unload Waiting for [1] instance(s) to be deallocated for Servlet [huaminglin.demo.servlet.classloader.StopServlet]
12-Oct-2020 16:51:55.230 INFO [http-nio-8080-exec-4] org.apache.catalina.core.StandardWrapper.unload Waiting for [1] instance(s) to be deallocated for Servlet [huaminglin.demo.servlet.classloader.StopServlet]
12-Oct-2020 16:51:55.347 INFO [http-nio-8080-exec-4] org.apache.coyote.AbstractProtocol.stop Stopping ProtocolHandler ["http-nio-8080"]
12-Oct-2020 16:51:55.375 WARNING [http-nio-8080-exec-4] org.apache.tomcat.util.net.AbstractEndpoint.shutdownExecutor The executor associated with thread pool [http-nio-8080] has not fully shutdown. Some application threads may still be running.
12-Oct-2020 16:51:55.376 INFO [http-nio-8080-exec-4] org.apache.coyote.AbstractProtocol.destroy Destroying ProtocolHandler ["http-nio-8080"]
12-Oct-2020 16:51:55.380 SEVERE [http-nio-8080-exec-4] org.apache.coyote.http11.Http11Processor.service Error processing request
	java.lang.NullPointerException
		at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:412)
		at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)
		at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
		at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)
		at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590)
		at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
		at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
		at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
		at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
		at java.base/java.lang.Thread.run(Thread.java:834)
12-Oct-2020 16:51:55.381 WARNING [http-nio-8080-exec-4] org.apache.catalina.connector.CoyoteAdapter.log Exception while attempting to add an entry to the access log
	java.lang.NullPointerException
		at org.apache.catalina.connector.CoyoteAdapter.log(CoyoteAdapter.java:490)
		at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:404)
		at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
		at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)
		at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590)
		at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
		at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
		at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
		at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
		at java.base/java.lang.Thread.run(Thread.java:834)

```

We can find "daemon.stopServer() from huaminglin.demo.servlet.classloader.StopServlet" in the log.
