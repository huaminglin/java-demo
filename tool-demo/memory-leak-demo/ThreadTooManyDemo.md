# Demo OOM caused by too many threads created

## Start the program with default thread stack size

export MAVEN_OPTS="-Xms128m -Xmx128m"

mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooManyDemo -Dexec.args="101"

`
10403
10404
10405
10406

[3.111s][warning][os,thread] Failed to start thread - pthread_create failed (EAGAIN) for attributes: stacksize: 1024k, guardsize: 0k, detached.
[WARNING] 
java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
    at java.lang.Thread.start0 (Native Method)
    at java.lang.Thread.start (Thread.java:803)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.runThread (ThreadDemo.java:16)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.main (ThreadDemo.java:27)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:566)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run (ExecJavaMojo.java:282)
    at java.lang.Thread.run (Thread.java:834)
`

## Start the program with larger thread stack size

myname@myname-MS-7850:~/workspace/java-demo/tool-demo/memory-leak-demo$ free
              total        used        free      shared  buff/cache   available
Mem:       15569140     2479292    10091912      678472     2997936    12087748
Swap:        999420           0      999420


export MAVEN_OPTS="-Xms128m -Xmx128m -Xss2048k"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooManyDemo -Dexec.args="100"


10364
10365
10366
[120.765s][warning][os,thread] Failed to start thread - pthread_create failed (EAGAIN) for attributes: stacksize: 2048k, guardsize: 0k, detached.
[WARNING] 
java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
    at java.lang.Thread.start0 (Native Method)
    at java.lang.Thread.start (Thread.java:803)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.runThread (ThreadTooManyDemo.java:16)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.main (ThreadTooManyDemo.java:27)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:566)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run (ExecJavaMojo.java:282)
    at java.lang.Thread.run (Thread.java:834)

myname@myname-MS-7850:~/workspace/java-demo/tool-demo/memory-leak-demo$ free
              total        used        free      shared  buff/cache   available
Mem:       15569140     2881644     9356876      665840     3330620    11686260
Swap:        999420           0      999420

Conclusion: 
The thread stack doesn't consume heap memory. 
"thread stack size" is the max value, not allocated on startup.
java.lang.OutOfMemoryError is triggered by other resource limit.
One possibility is that you have reached your user limit for the number of open files.


## ulimit

ulimit -a
core file size          (blocks, -c) 0
data seg size           (kbytes, -d) unlimited
scheduling priority             (-e) 0
file size               (blocks, -f) unlimited
pending signals                 (-i) 60529
max locked memory       (kbytes, -l) 16384
max memory size         (kbytes, -m) unlimited
open files                      (-n) 1024
pipe size            (512 bytes, -p) 8
POSIX message queues     (bytes, -q) 819200
real-time priority              (-r) 0
stack size              (kbytes, -s) 8192
cpu time               (seconds, -t) unlimited
max user processes              (-u) 60529
virtual memory          (kbytes, -v) unlimited
file locks                      (-x) unlimited


cat /proc/21713/limits
Limit                     Soft Limit           Hard Limit           Units     
Max cpu time              unlimited            unlimited            seconds   
Max file size             unlimited            unlimited            bytes     
Max data size             unlimited            unlimited            bytes     
Max stack size            8388608              unlimited            bytes     
Max core file size        0                    unlimited            bytes     
Max resident set          unlimited            unlimited            bytes     
Max processes             60529                60529                processes 
Max open files            4096                 4096                 files     
Max locked memory         16777216             16777216             bytes     
Max address space         unlimited            unlimited            bytes     
Max file locks            unlimited            unlimited            locks     
Max pending signals       60529                60529                signals   
Max msgqueue size         819200               819200               bytes     
Max nice priority         0                    0                    
Max realtime priority     0                    0                    
Max realtime timeout      unlimited            unlimited            us        

ls -1 /proc/21713/fd | wc -l

Conclusion: Still don't know which resource limit trigger java.lang.OutOfMemoryError.

## ulimit and run

ulimit -t unlimited
ulimit -f unlimited
ulimit -m unlimited
ulimit -u unlimited
bash: ulimit: max user processes: cannot modify limit: Operation not permitted
ulimit -n 8192
bash: ulimit: open files: cannot modify limit: Operation not permitted
ulimit -s unlimited
ulimit -v unlimited


export MAVEN_OPTS="-Xms128m -Xmx128m"

mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooManyDemo -Dexec.args="20000 10"


9861
[105.507s][warning][os,thread] Failed to start thread - pthread_create failed (EAGAIN) for attributes: stacksize: 1024k, guardsize: 0k, detached.
[WARNING] 
java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
    at java.lang.Thread.start0 (Native Method)
    at java.lang.Thread.start (Thread.java:803)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.runThread (ThreadTooManyDemo.java:16)
    at huaminglin.demo.tool.memory.thread.ThreadTooManyDemo.main (ThreadTooManyDemo.java:30)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:566)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run (ExecJavaMojo.java:282)
    at java.lang.Thread.run (Thread.java:834)

ulimit -a
core file size          (blocks, -c) 0
data seg size           (kbytes, -d) unlimited
scheduling priority             (-e) 0
file size               (blocks, -f) unlimited
pending signals                 (-i) 60529
max locked memory       (kbytes, -l) 16384
max memory size         (kbytes, -m) unlimited
open files                      (-n) 1024
pipe size            (512 bytes, -p) 8
POSIX message queues     (bytes, -q) 819200
real-time priority              (-r) 0
stack size              (kbytes, -s) unlimited
cpu time               (seconds, -t) unlimited
max user processes              (-u) 60529
virtual memory          (kbytes, -v) unlimited
file locks                      (-x) unlimited

Conclusion: Still don't know which resource limit trigger java.lang.OutOfMemoryError.


