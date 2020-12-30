# Logging demo

## When MyLogItem.toString is called

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.logging.MyLogItem.toString(MyLogItem.java:20)
	  at org.apache.logging.log4j.message.ParameterFormatter.tryObjectToString(ParameterFormatter.java:607)
	  at org.apache.logging.log4j.message.ParameterFormatter.recursiveDeepToString(ParameterFormatter.java:458)
	  at org.apache.logging.log4j.message.ParameterFormatter.formatMessage2(ParameterFormatter.java:189)
	  at org.apache.logging.log4j.message.ReusableParameterizedMessage.formatTo(ReusableParameterizedMessage.java:329)
	  at org.apache.logging.log4j.core.impl.MutableLogEvent.setMessage(MutableLogEvent.java:212)
	  at org.apache.logging.log4j.core.impl.ReusableLogEventFactory.createEvent(ReusableLogEventFactory.java:100)
	  at org.apache.logging.log4j.core.config.LoggerConfig.log(LoggerConfig.java:457)
	  at org.apache.logging.log4j.core.config.AwaitCompletionReliabilityStrategy.log(AwaitCompletionReliabilityStrategy.java:82)
	  at org.apache.logging.log4j.core.Logger.log(Logger.java:161)
	  at org.apache.logging.log4j.spi.AbstractLogger.tryLogMessage(AbstractLogger.java:2198)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageTrackRecursion(AbstractLogger.java:2152)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageSafely(AbstractLogger.java:2135)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessage(AbstractLogger.java:2028)
	  at org.apache.logging.log4j.spi.AbstractLogger.logIfEnabled(AbstractLogger.java:1899)
	  at org.apache.logging.slf4j.Log4jLogger.error(Log4jLogger.java:304)
	  at huaminglin.demo.logging.LoggingDemo.main(LoggingDemo.java:11)

```
## mvn -Dlog4j.configurationFile=classpath:log4j2-json.xml exec:java

org.apache.logging.log4j.core.layout.JsonLayout

```
Exception in thread "main" java.lang.NoClassDefFoundError: com/fasterxml/jackson/databind/ser/FilterProvider
	at org.apache.logging.log4j.core.layout.JsonLayout.<init>(JsonLayout.java:158)
	at org.apache.logging.log4j.core.layout.JsonLayout.<init>(JsonLayout.java:69)
	at org.apache.logging.log4j.core.layout.JsonLayout$Builder.build(JsonLayout.java:102)
	at org.apache.logging.log4j.core.layout.JsonLayout$Builder.build(JsonLayout.java:77)
	at org.apache.logging.log4j.core.config.plugins.util.PluginBuilder.build(PluginBuilder.java:122)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createPluginObject(AbstractConfiguration.java:1002)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createConfiguration(AbstractConfiguration.java:942)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createConfiguration(AbstractConfiguration.java:934)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createConfiguration(AbstractConfiguration.java:934)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.doConfigure(AbstractConfiguration.java:552)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.initialize(AbstractConfiguration.java:241)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.start(AbstractConfiguration.java:288)
	at org.apache.logging.log4j.core.LoggerContext.setConfiguration(LoggerContext.java:622)
	at org.apache.logging.log4j.core.LoggerContext.reconfigure(LoggerContext.java:695)
	at org.apache.logging.log4j.core.LoggerContext.reconfigure(LoggerContext.java:712)
	at org.apache.logging.log4j.core.LoggerContext.start(LoggerContext.java:267)
	at org.apache.logging.log4j.core.impl.Log4jContextFactory.getContext(Log4jContextFactory.java:155)
	at org.apache.logging.log4j.core.impl.Log4jContextFactory.getContext(Log4jContextFactory.java:47)
	at org.apache.logging.log4j.LogManager.getContext(LogManager.java:194)
	at org.apache.logging.log4j.LogManager.getLogger(LogManager.java:602)
	at huaminglin.demo.logging.LoggingDemo.main(LoggingDemo.java:9)
Caused by: java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.ser.FilterProvider
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:581)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)
	... 21 more
```

com.fasterxml.jackson.core/jackson-databind is required.

## JSON message sample

```
{
  "instant" : {
    "epochSecond" : 1609315322,
    "nanoOfSecond" : 914315000
  },
  "thread" : "huaminglin.demo.logging.LoggingDemo.main()",
  "level" : "ERROR",
  "loggerName" : "huaminglin.demo.logging.LoggingDemo",
  "message" : "MyLogItem{value='myitem'}",
  "endOfBatch" : false,
  "loggerFqcn" : "org.apache.logging.log4j.spi.AbstractLogger",
  "threadId" : 14,
  "threadPriority" : 5,
  "myCustomField" : "myCustomValue"
}
```
