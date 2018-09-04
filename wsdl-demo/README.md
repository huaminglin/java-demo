## Run a http web server
mvn -Djetty.http.port=9999 jetty:run

http://127.0.0.1:9999/hello/helloProvider?wsdl
http://127.0.0.1:9999/hello/rest/


## com.sun.xml.fastinfoset</groupId>/FastInfoset/1.2.15
[WARNING] Failed startup of context o.e.j.m.p.JettyWebAppContext@b8e246c{/hello,file:///home/user1/workspace/java-demo/wsdl-demo/wsdl-server-demo/src/main/webapp/,UNAVAILABLE}{file:///home/user1/workspace/java-demo/wsdl-demo/wsdl-server-demo/src/main/webapp/}
com.sun.xml.ws.transport.http.servlet.WSServletException: WSSERVLET11: failed to parse runtime descriptor: java.lang.NoClassDefFoundError: com/sun/xml/fastinfoset/stax/StAXDocumentParser
    at com.sun.xml.ws.transport.http.servlet.WSServletContextListener.contextInitialized (WSServletContextListener.java:118)
    at org.eclipse.jetty.server.handler.ContextHandler.callContextInitialized (ContextHandler.java:953)
