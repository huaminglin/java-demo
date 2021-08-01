# How Spring Boot setup logging

## LoggingApplicationListener

org.springframework.boot.context.logging.LoggingApplicationListener

org.springframework.boot.logging.LoggingSystem

org.springframework.boot.logging.logback.LogbackLoggingSystem

org.springframework.boot.logging.logback.DefaultLogbackConfiguration

```
org.springframework.boot.logging.logback.LogbackLoggingSystem
LogbackLoggingSystem: String[] getStandardConfigLocations()
return new String[] { "logback-test.groovy", "logback-test.xml", "logback.groovy", "logback.xml" };

spring-boot-2.4.4.jar!/org/springframework/boot/logging/logback/defaults.xml
It seems there is no logback.xml in the classpath.

org.springframework.boot.logging.java.JavaLoggingSystem
return new String[] { "logging.properties" };

org.springframework.boot.logging.log4j2.Log4J2LoggingSystem
log4j2.properties; log4j2.yaml; log4j2.yml; log4j2.json; log4j2.jsn; log4j2.xml
```
