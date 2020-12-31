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

## CustomLayout

mvn -Dlog4j.configurationFile=classpath:log4j2-custom.xml exec:java

## LogConfig Hierarchy

```
12:09:04.903 [main] INFO  a - a
INFO
12:09:04.905 [main] INFO  a.b.c - a.b.c
INFO
12:09:04.906 [main] INFO  a.b - a.b
INFO
Level changed.
INFO
DEBUG
DEBUG
```

Conclusion:

1) 'a.b' is always the parent of 'a.b.c', even when the 'a.b' is created after 'a.b.c'.

2) "Configurator.setLevel()" creates LoggerConfig for the target logger.

3) The new created LoggerConfig is available to the descendant of the logger if it has no LoggerConfig configured.


## LoggerConfig and Configurator.setLevel()

```
org.apache.logging.log4j.core.config.AbstractConfiguration

  private ConcurrentMap<String, LoggerConfig> loggerConfigs = new ConcurrentHashMap();

    public void start() {
        // Preserve the prior behavior of initializing during start if not initialized.
        if (getState().equals(State.INITIALIZING)) {
            initialize();
        }
        LOGGER.debug("Starting configuration {}", this);
        this.setStarting();
        if (watchManager.getIntervalSeconds() >= 0) {
            watchManager.start();
        }
        if (hasAsyncLoggers()) {
            asyncLoggerConfigDisruptor.start();
        }
        final Set<LoggerConfig> alreadyStarted = new HashSet<>();
        for (final LoggerConfig logger : loggerConfigs.values()) {
            logger.start();
            alreadyStarted.add(logger);
        }
        for (final Appender appender : appenders.values()) {
            appender.start();
        }
        if (!alreadyStarted.contains(root)) { // LOG4J2-392
            root.start(); // LOG4J2-336
        }
        super.start();
        LOGGER.debug("Started configuration {} OK.", this);
    }

    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        this.setStopping();
        super.stop(timeout, timeUnit, false);
        LOGGER.trace("Stopping {}...", this);

        // Stop the components that are closest to the application first:
        // 1. Notify all LoggerConfigs' ReliabilityStrategy that the configuration will be stopped.
        // 2. Stop the LoggerConfig objects (this may stop nested Filters)
        // 3. Stop the AsyncLoggerConfigDelegate. This shuts down the AsyncLoggerConfig Disruptor
        //    and waits until all events in the RingBuffer have been processed.
        // 4. Stop all AsyncAppenders. This shuts down the associated thread and
        //    waits until all events in the queue have been processed. (With optional timeout.)
        // 5. Notify all LoggerConfigs' ReliabilityStrategy that appenders will be stopped.
        //    This guarantees that any event received by a LoggerConfig before reconfiguration
        //    are passed on to the Appenders before the Appenders are stopped.
        // 6. Stop the remaining running Appenders. (It should now be safe to do so.)
        // 7. Notify all LoggerConfigs that their Appenders can be cleaned up.

    @Override
    public LoggerConfig getLoggerConfig(final String loggerName) {
        LoggerConfig loggerConfig = loggerConfigs.get(loggerName);
        if (loggerConfig != null) {
            return loggerConfig;
        }
        String substr = loggerName;
        while ((substr = NameUtil.getSubName(substr)) != null) {
            loggerConfig = loggerConfigs.get(substr);
            if (loggerConfig != null) {
                return loggerConfig;
            }
        }
        return root;
    }


org.apache.logging.log4j.core.Logger

    /**
     * This method is only used for 1.x compatibility. Returns the parent of this Logger. If it doesn't already exist
     * return a temporary Logger.
     *
     * @return The parent Logger.
     */
    public Logger getParent() {
        final LoggerConfig lc = privateConfig.loggerConfig.getName().equals(getName()) ? privateConfig.loggerConfig
                .getParent() : privateConfig.loggerConfig;
        if (lc == null) {
            return null;
        }
        final String lcName = lc.getName();
        final MessageFactory messageFactory = getMessageFactory();
        if (context.hasLogger(lcName, messageFactory)) {
            return context.getLogger(lcName, messageFactory);
        }
        return new Logger(context, lcName, messageFactory);
    }

    public synchronized void setLevel(final Level level) {
        if (level == getLevel()) {
            return;
        }
        Level actualLevel;
        if (level != null) {
            actualLevel = level;
        } else {
            final Logger parent = getParent();
            actualLevel = parent != null ? parent.getLevel() : privateConfig.loggerConfigLevel;
        }
        privateConfig = new PrivateConfig(privateConfig, actualLevel);
    }

    protected void updateConfiguration(final Configuration newConfig) {
        this.privateConfig = new PrivateConfig(newConfig, this);
    }
org.apache.logging.log4j.core.config.Configurator
    public static void setLevel(final String loggerName, final Level level) {
        final LoggerContext loggerContext = LoggerContext.getContext(false);
        if (Strings.isEmpty(loggerName)) {
            setRootLevel(level);
        } else {
            if (setLevel(loggerName, level, loggerContext.getConfiguration())) {
                loggerContext.updateLoggers();
            }
        }
    }

    private static boolean setLevel(final String loggerName, final Level level, final Configuration config) {
        boolean set;
        LoggerConfig loggerConfig = config.getLoggerConfig(loggerName);
        if (!loggerName.equals(loggerConfig.getName())) {
            // TODO Should additivity be inherited?
            loggerConfig = new LoggerConfig(loggerName, level, true);
            config.addLogger(loggerName, loggerConfig);
            loggerConfig.setLevel(level);
            set = true;
        } else {
            set = setLevel(loggerConfig, level);
        }
        return set;
    }

    private static boolean setLevel(final LoggerConfig loggerConfig, final Level level) {
        final boolean set = !loggerConfig.getLevel().equals(level);
        if (set) {
            loggerConfig.setLevel(level);
        }
        return set;
    }

org.apache.logging.log4j.core.LoggerContext
    /**
     * Causes all Loggers to be updated against the current Configuration.
     */
    public void updateLoggers() {
        updateLoggers(this.configuration);
    }

```

Conclusion:

1) "config.addLogger(loggerName, loggerConfig);", a new LoggerConfig is created and add to configuration if necessary.

2) Causes all Loggers to be updated against the current Configuration.

3) "loggerContext.updateLoggers()" create new privateConfig for all the loggers.

4) How the log4j2 logger hierarchy works:

AbstractConfiguration: LoggerConfig getLoggerConfig(final String loggerName)

while ((substr = NameUtil.getSubName(substr)) != null) { loggerConfig = loggerConfigs.get(substr); if (loggerConfig != null) { return loggerConfig; } }

## Lookup

mvn -Dlog4j.configurationFile=classpath:log4j2-lookup.xml exec:java
