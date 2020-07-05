# Demo Java Language Features

## Expose a customized standard JMX bean
export MY_HOST_IP=<my host ip>
mvn -P jmxbean exec:exec
Nov 06, 2018 6:09:11 AM ConnectorBootstrap startRemoteConnectorServer
CONFIG: JMX Connector ready at: service:jmx:rmi:///jndi/rmi://<my host ip>:9999/jmxrmi

## Then open JConsole to view the JMX bean in the running process.

## Run a customized JMX client
export TARGET_HOST=<my host ip>
mvn -P jmxclient exec:java

        Domain = JMImplementation
        Domain = MyJMXPackage
        Domain = com.sun.management
        Domain = java.lang
        Domain = java.nio
        Domain = java.util.logging

MBeanServer default domain = DefaultDomain

MBean count = 23

Query MBeanServer MBeans:
        ObjectName = JMImplementation:type=MBeanServerDelegate
        ObjectName = MyJMXPackage:type=MyJMXType
        ObjectName = com.sun.management:type=DiagnosticCommand
        ObjectName = com.sun.management:type=HotSpotDiagnostic
        ObjectName = java.lang:type=ClassLoading
        ObjectName = java.lang:type=Compilation
        ObjectName = java.lang:type=GarbageCollector,name=PS MarkSweep
        ObjectName = java.lang:type=GarbageCollector,name=PS Scavenge
        ObjectName = java.lang:type=Memory
        ObjectName = java.lang:type=MemoryManager,name=CodeCacheManager
        ObjectName = java.lang:type=MemoryManager,name=Metaspace Manager
        ObjectName = java.lang:type=MemoryPool,name=Code Cache
        ObjectName = java.lang:type=MemoryPool,name=Compressed Class Space
        ObjectName = java.lang:type=MemoryPool,name=Metaspace
        ObjectName = java.lang:type=MemoryPool,name=PS Eden Space
        ObjectName = java.lang:type=MemoryPool,name=PS Old Gen
        ObjectName = java.lang:type=MemoryPool,name=PS Survivor Space
        ObjectName = java.lang:type=OperatingSystem
        ObjectName = java.lang:type=Runtime
        ObjectName = java.lang:type=Threading
        ObjectName = java.nio:type=BufferPool,name=direct
        ObjectName = java.nio:type=BufferPool,name=mapped
        ObjectName = java.util.logging:type=Logging

## DateTimeDemo
mvn exec:java -Dexec.mainClass="huaminglin.demo.jdk.datetime.DateTimeDemo"

## AnnotationDemo
mvn exec:java -Dexec.mainClass="huaminglin.demo.jdk.annotation.AnnotationDemo"
mvn exec:java -Dexec.mainClass="huaminglin.demo.jdk.datetime.DateTimeDemo"

huaminglin.demo.jdk.annotation.$Proxy32.<clinit> {
      huaminglin.demo.jdk.annotation.Transactional.<clinit> {
      }
}
Note: The annotation interface class is loaded with a proxy class.

## HotSpotDiagnostic.getDiagnosticOptions()
java -cp ./target/classes huaminglin.demo.jdk.mbean.DiagnosticOptionsPrinter
HeapDumpBeforeFullGC = false
HeapDumpAfterFullGC = false
HeapDumpOnOutOfMemoryError = false
HeapDumpPath =
CMSAbortablePrecleanWaitMillis = 100
CMSWaitDuration = 2000
CMSTriggerInterval = -1
PrintGC = false
PrintGCDetails = false
PrintGCDateStamps = false
PrintGCTimeStamps = false
PrintGCID = false
PrintClassHistogramBeforeFullGC = false
PrintClassHistogramAfterFullGC = false
PrintClassHistogram = false
MinHeapFreeRatio = 0
MaxHeapFreeRatio = 100
PrintConcurrentLocks = false
