# Memory leak demo

## Start the program

export MAVEN_OPTS="-Xms128m -Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/oom.dump"

mvn exec:java

## Check memory usage

jps -lvm

jmap -histo:live 15377 > /tmp/15377.jmap

Check "huaminglin.demo.tool.memory.leak.MemoryLeakDemo$1".
 
This is the anonymous class created in MemoryLeakDemo: org.apache.http.concurrent.FutureCallback.
 
The  FutureCallback instance is referenced by HttpUriRequest. HttpUriRequest is referenced by the cache list. 
 
And FutureCallback instances also reference a lot other objects. Check 15377-2.jmap. It has 18125 FutureCallback. 

18125 occurs 15 times in 15377-2.jmap. This means each FutureCallback instance has 14 related objects.
 
This is kind of API misuse, and it leads to memory leak. HttpUriRequest should not be cached.

## Check oom.dump

`
GET http://127.0.0.1:8080/?a=21218 HTTP/1.1->java.net.ConnectException: Connection refused
java.lang.OutOfMemoryError: Java heap space
Dumping heap to /tmp/oom.dump ...
Heap dump file created [212714560 bytes in 1.049 secs]
[WARNING] 
[WARNING] 
java.lang.OutOfMemoryError: Java heap space

    at java.util.Collections$UnmodifiableCollection.iterator    at java.lang.Integer.toString (Collections.java:1041)
Exception in thread "RMI TCP Connection(idle)" java.lang.OutOfMemoryError: Java heap space
     (Integer.java:440)
    atat org.apache.http.impl.nio.reactor.BaseIOReactor.validate org.apache.http.HttpHost.toHostString (HttpHost.java:288)
     (BaseIOReactor.java:212)at
    at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute org.apache.http.protocol.RequestTargetHost.process (AbstractIOReactor.java:280) (RequestTargetHost.java:92)
    at org.apache.http.impl.nio.reactor.BaseIOReactor.execute
    at org.apache.http.protocol.ImmutableHttpProcessor.process (BaseIOReactor.java:104)
    at (ImmutableHttpProcessor.java:133) org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run
    at org.apache.http.impl.nio.client.MainClientExec.prepareRequest (AbstractMultiworkerIOReactor.java:588)
     (MainClientExec.java:520)at
     java.lang.Thread.runat org.apache.http.impl.nio.client.MainClientExec.prepare (Thread.java:834)
 (MainClientExec.java:146)
    at org.apache.http.impl.nio.client.DefaultClientExchangeHandlerImpl.start (DefaultClientExchangeHandlerImpl.java:128)
    at org.apache.http.impl.nio.client.InternalHttpAsyncClient.execute (InternalHttpAsyncClient.java:141)
    at org.apache.http.impl.nio.client.CloseableHttpAsyncClient.execute (CloseableHttpAsyncClient.java:75)
    at org.apache.http.impl.nio.client.CloseableHttpAsyncClient.execute (CloseableHttpAsyncClient.java:108)
    at org.apache.http.impl.nio.client.CloseableHttpAsyncClient.execute (CloseableHttpAsyncClient.java:92)
    at huaminglin.demo.tool.memory.leak.MemoryLeakDemo.start (MemoryLeakDemo.java:31)
    at huaminglin.demo.tool.memory.leak.MemoryLeakDemo.main (MemoryLeakDemo.java:54)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:566)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run (ExecJavaMojo.java:282)
    at java.lang.Thread.run (Thread.java:834)
Apr 22, 2020 12:51:17 AM org.apache.http.impl.nio.client.InternalHttpAsyncClient run
SEVERE: I/O reactor terminated abnormally
org.apache.http.nio.reactor.IOReactorException: I/O dispatch worker terminated abnormally
	at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor.execute(AbstractMultiworkerIOReactor.java:356)
	at org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager.execute(PoolingNHttpClientConnectionManager.java:221)
	at org.apache.http.impl.nio.client.CloseableHttpAsyncClientBase$1.run(CloseableHttpAsyncClientBase.java:64)
	at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.Collections$UnmodifiableCollection.iterator(Collections.java:1041)
	at org.apache.http.impl.nio.reactor.BaseIOReactor.validate(BaseIOReactor.java:212)
	at org.apache.http.impl.nio.reactor.AbstractIOReactor.execute(AbstractIOReactor.java:280)
	at org.apache.http.impl.nio.reactor.BaseIOReactor.execute(BaseIOReactor.java:104)
	at org.apache.http.impl.nio.reactor.AbstractMultiworkerIOReactor$Worker.run(AbstractMultiworkerIOReactor.java:588)
	... 1 more
`

## 参考文章

记一次 JAVA 的内存泄露分析
https://github.com/jasonGeng88/blog/blob/master/201710/java-analysis.md
