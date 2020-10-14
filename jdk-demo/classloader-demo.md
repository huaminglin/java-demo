# huaminglin.demo.jdk.classloader.LoggerClassLoader

## How URLClassLoader load classes?

```
LoggerClassLoader.loadClass: org.slf4j.LoggerFactory
LoggerClassLoader.loadClass: org.slf4j.LoggerFactory, false
LoggerClassLoader.findClass: org.slf4j.LoggerFactory
LoggerClassLoader.loadClass: java.lang.Object
LoggerClassLoader.loadClass: java.lang.Object, false
LoggerClassLoader.loadClass: org.slf4j.ILoggerFactory
LoggerClassLoader.loadClass: org.slf4j.ILoggerFactory, false
LoggerClassLoader.findClass: org.slf4j.ILoggerFactory
LoggerClassLoader.loadClass: java.lang.Throwable
LoggerClassLoader.loadClass: java.lang.Throwable, false
LoggerClassLoader.loadClass: java.lang.IllegalStateException
LoggerClassLoader.loadClass: java.lang.IllegalStateException, false
LoggerClassLoader.loadClass: java.lang.CharSequence
LoggerClassLoader.loadClass: java.lang.CharSequence, false
LoggerClassLoader.loadClass: java.io.IOException
LoggerClassLoader.loadClass: java.io.IOException, false
LoggerClassLoader.loadClass: java.util.Set
LoggerClassLoader.loadClass: java.util.Set, false
LoggerClassLoader.loadClass: java.lang.ClassLoader
LoggerClassLoader.loadClass: java.lang.ClassLoader, false
LoggerClassLoader.loadClass: java.lang.NoSuchFieldError
LoggerClassLoader.loadClass: java.lang.NoSuchFieldError, false
LoggerClassLoader.loadClass: java.lang.String
LoggerClassLoader.loadClass: java.lang.String, false
LoggerClassLoader.loadClass: org.slf4j.event.LoggingEvent
LoggerClassLoader.loadClass: org.slf4j.event.LoggingEvent, false
LoggerClassLoader.findClass: org.slf4j.event.LoggingEvent
LoggerClassLoader.loadClass: java.util.Collection
LoggerClassLoader.loadClass: java.util.Collection, false
LoggerClassLoader.loadClass: java.util.List
LoggerClassLoader.loadClass: java.util.List, false
LoggerClassLoader.loadClass: java.lang.Exception
LoggerClassLoader.loadClass: java.lang.Exception, false
LoggerClassLoader.loadClass: java.lang.NoSuchMethodError
LoggerClassLoader.loadClass: java.lang.NoSuchMethodError, false
LoggerClassLoader.loadClass: java.lang.NoClassDefFoundError
LoggerClassLoader.loadClass: java.lang.NoClassDefFoundError, false
LoggerClassLoader.loadClass: org.slf4j.helpers.SubstituteLoggerFactory
LoggerClassLoader.loadClass: org.slf4j.helpers.SubstituteLoggerFactory, false
LoggerClassLoader.findClass: org.slf4j.helpers.SubstituteLoggerFactory
LoggerClassLoader.loadClass: java.util.Queue
LoggerClassLoader.loadClass: java.util.Queue, false
LoggerClassLoader.loadClass: org.slf4j.Logger
LoggerClassLoader.loadClass: org.slf4j.Logger, false
LoggerClassLoader.findClass: org.slf4j.Logger
LoggerClassLoader.loadClass: java.util.Map
LoggerClassLoader.loadClass: java.util.Map, false
LoggerClassLoader.loadClass: java.util.HashMap
LoggerClassLoader.loadClass: java.util.HashMap, false
LoggerClassLoader.loadClass: java.util.concurrent.LinkedBlockingQueue
LoggerClassLoader.loadClass: java.util.concurrent.LinkedBlockingQueue, false
LoggerClassLoader.loadClass: org.slf4j.helpers.NOPLoggerFactory
LoggerClassLoader.loadClass: org.slf4j.helpers.NOPLoggerFactory, false
LoggerClassLoader.findClass: org.slf4j.helpers.NOPLoggerFactory
LoggerClassLoader.loadClass: org.slf4j.helpers.Util
LoggerClassLoader.loadClass: org.slf4j.helpers.Util, false
LoggerClassLoader.findClass: org.slf4j.helpers.Util
LoggerClassLoader.loadClass: java.lang.SecurityException
LoggerClassLoader.loadClass: java.lang.SecurityException, false
LoggerClassLoader.loadClass: java.lang.IllegalArgumentException
LoggerClassLoader.loadClass: java.lang.IllegalArgumentException, false
LoggerClassLoader.loadClass: java.lang.System
LoggerClassLoader.loadClass: java.lang.System, false
 ```

Note: From the above log, the loadClass is called even for "java.lang.String".

This is a chance to inject a hacked version of java.lang.String.
