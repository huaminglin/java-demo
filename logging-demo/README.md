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

## CustomMessage

```
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.logging.CustomMessage.getFormattedMessage(CustomMessage.java:14)
	  at org.apache.logging.log4j.core.async.InternalAsyncUtil.makeMessageImmutable(InternalAsyncUtil.java:41)
	  at org.apache.logging.log4j.core.impl.MutableLogEvent.setMessage(MutableLogEvent.java:217)
	  at org.apache.logging.log4j.core.impl.ReusableLogEventFactory.createEvent(ReusableLogEventFactory.java:100)
	  at org.apache.logging.log4j.core.config.LoggerConfig.log(LoggerConfig.java:457)
	  at org.apache.logging.log4j.core.config.AwaitCompletionReliabilityStrategy.log(AwaitCompletionReliabilityStrategy.java:82)
	  at org.apache.logging.log4j.core.Logger.log(Logger.java:161)
	  at org.apache.logging.log4j.spi.AbstractLogger.tryLogMessage(AbstractLogger.java:2198)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageTrackRecursion(AbstractLogger.java:2152)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageSafely(AbstractLogger.java:2135)
	  at org.apache.logging.log4j.spi.AbstractLogger.logIfEnabled(AbstractLogger.java:1836)
	  at org.apache.logging.log4j.spi.AbstractLogger.error(AbstractLogger.java:710)
	  at huaminglin.demo.logging.LoggingDemo.main(LoggingDemo.java:12)
```

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.logging.CustomMessage.getFormattedMessage(CustomMessage.java:14)
	  at org.apache.logging.log4j.core.jackson.MessageSerializer.serialize(MessageSerializer.java:44)
	  at org.apache.logging.log4j.core.jackson.MessageSerializer.serialize(MessageSerializer.java:33)
	  at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
	  at com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.serializeAsField(SimpleBeanPropertyFilter.java:208)
	  at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFieldsFiltered(BeanSerializerBase.java:807)
	  at com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer.serialize(UnwrappingBeanSerializer.java:132)
	  at com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter.serializeAsField(UnwrappingBeanPropertyWriter.java:127)
	  at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:755)
	  at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
	  at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
	  at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
	  at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1516)
	  at com.fasterxml.jackson.databind.ObjectWriter._writeValueAndClose(ObjectWriter.java:1217)
	  at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:1059)
	  at org.apache.logging.log4j.core.layout.AbstractJacksonLayout.toSerializable(AbstractJacksonLayout.java:344)
	  at org.apache.logging.log4j.core.layout.JsonLayout.toSerializable(JsonLayout.java:291)
	  at org.apache.logging.log4j.core.layout.AbstractJacksonLayout.toSerializable(AbstractJacksonLayout.java:292)
	  at org.apache.logging.log4j.core.layout.JsonLayout.toSerializable(JsonLayout.java:68)
	  at org.apache.logging.log4j.core.layout.AbstractJacksonLayout.toSerializable(AbstractJacksonLayout.java:52)
	  at org.apache.logging.log4j.core.layout.AbstractStringLayout.toByteArray(AbstractStringLayout.java:308)
	  at org.apache.logging.log4j.core.layout.AbstractLayout.encode(AbstractLayout.java:210)
	  at org.apache.logging.log4j.core.layout.AbstractLayout.encode(AbstractLayout.java:37)
	  at org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender.directEncodeEvent(AbstractOutputStreamAppender.java:197)
	  at org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender.tryAppend(AbstractOutputStreamAppender.java:190)
	  at org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender.append(AbstractOutputStreamAppender.java:181)
	  at org.apache.logging.log4j.core.config.AppenderControl.tryCallAppender(AppenderControl.java:156)
	  at org.apache.logging.log4j.core.config.AppenderControl.callAppender0(AppenderControl.java:129)
	  at org.apache.logging.log4j.core.config.AppenderControl.callAppenderPreventRecursion(AppenderControl.java:120)
	  at org.apache.logging.log4j.core.config.AppenderControl.callAppender(AppenderControl.java:84)
	  at org.apache.logging.log4j.core.config.LoggerConfig.callAppenders(LoggerConfig.java:543)
	  at org.apache.logging.log4j.core.config.LoggerConfig.processLogEvent(LoggerConfig.java:502)
	  at org.apache.logging.log4j.core.config.LoggerConfig.log(LoggerConfig.java:485)
	  at org.apache.logging.log4j.core.config.LoggerConfig.log(LoggerConfig.java:460)
	  at org.apache.logging.log4j.core.config.AwaitCompletionReliabilityStrategy.log(AwaitCompletionReliabilityStrategy.java:82)
	  at org.apache.logging.log4j.core.Logger.log(Logger.java:161)
	  at org.apache.logging.log4j.spi.AbstractLogger.tryLogMessage(AbstractLogger.java:2198)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageTrackRecursion(AbstractLogger.java:2152)
	  at org.apache.logging.log4j.spi.AbstractLogger.logMessageSafely(AbstractLogger.java:2135)
	  at org.apache.logging.log4j.spi.AbstractLogger.logIfEnabled(AbstractLogger.java:1836)
	  at org.apache.logging.log4j.spi.AbstractLogger.error(AbstractLogger.java:710)
	  at huaminglin.demo.logging.LoggingDemo.main(LoggingDemo.java:12)
```

Note: CustomMessage.getFormattedMessage is called twice: One from InternalAsyncUtil and the other from JsonLayout.
