# How Spring Boot setup log4j2

## mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dorg.springframework.boot.logging.LoggingSystem=org.springframework.boot.logging.log4j2.Log4J2LoggingSystem"

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/home/myname/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/myname/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.13.3/log4j-slf4j-impl-2.13.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
Exception in thread "main" java.lang.ClassCastException: class org.apache.logging.slf4j.SLF4JLoggerContext cannot be cast to class org.apache.logging.log4j.core.LoggerContext (org.apache.logging.slf4j.SLF4JLoggerContext and org.apache.logging.log4j.core.LoggerContext are in unnamed module of loader 'app')
	at org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.getLoggerContext(Log4J2LoggingSystem.java:305)
	at org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.beforeInitialize(Log4J2LoggingSystem.java:145)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationStartingEvent(LoggingApplicationListener.java:232)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEvent(LoggingApplicationListener.java:213)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:127)
	at org.springframework.boot.context.event.EventPublishingRunListener.starting(EventPublishingRunListener.java:74)
	at org.springframework.boot.SpringApplicationRunListeners.starting(SpringApplicationRunListeners.java:47)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:305)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1237)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226)
	at huaminglin.demo.spring.boot.logging.SpringBootLog4j2Demo.main(SpringBootLog4j2Demo.java:12)
```

Conclusion: We have to resolve the SLF4J multiple bindings conflict.

## spring-boot-starter-log4j2-2.4.4.pom vs. spring-boot-starter-logging-2.4.4.pom

```
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
```

Note: spring-boot-starter-logging imports logback implementation.

## Use Spring Actuator to change logger level at runtime

curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "TRACE"}' http://localhost:8080/actuator/loggers/huaminglin.demo.spring.boot.logging.LoggingController
