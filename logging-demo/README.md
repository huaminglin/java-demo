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
