# Thread Status Demo

## Run the application and dump the thread

java.lang.Thread.State: RUNNABLE


java.io.FileInputStream.readBytes -> java.lang.Thread.State: RUNNABLE

It's also worth noting that threads blocking in calls to native methods appear in the JVM as RUNNABLE, and hence are reported by VisualVM as Running (and as consuming 100% CPU).

java.io.FileInputStream.readBytes is shown as "S" in the "top -H -p <pid>" output.


Thread.sleep() -> java.lang.Thread.State: TIMED_WAITING (sleeping)

synchronized() -> java.lang.Thread.State: BLOCKED (on object monitor); - waiting to lock <0x00000007211117f8> (a java.lang.Object)

synchronized wait() -> java.lang.Thread.State: WAITING (on object monitor); - waiting on <0x0000000721112100> (a huaminglin.demo.concurrency.ThreadStatusDemo$WaitingThread)

Lock.lock() -> java.lang.Thread.State: WAITING (parking); - parking to wait for  <0x0000000721111ce8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

Condition.await() -> java.lang.Thread.State: WAITING (parking); - parking to wait for  <0x0000000721111e48> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)

Main thread which enters synchronized(): "- locked <0x00000007211117f8> (a java.lang.Object)"

Main thread which enters lock(): "Locked ownable synchronizers: - <0x0000000721111ce8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)"

```
"main" #1 prio=5 os_prio=0 cpu=72.41ms elapsed=8.68s tid=0x00007f7814017000 nid=0x6d02 waiting on condition  [0x00007f7819d84000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.9/Native Method)
        at huaminglin.demo.concurrency.ThreadStatusDemo.main(ThreadStatusDemo.java:119)
        - locked <0x00000007211117f8> (a java.lang.Object)

   Locked ownable synchronizers:
        - <0x0000000721111ce8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

"RunningThreadName01" #13 prio=5 os_prio=0 cpu=194804.46ms elapsed=194.86s tid=0x00007f53d82fe800 nid=0x5876 runnable  [0x00007f53a2885000]
   java.lang.Thread.State: RUNNABLE
        at huaminglin.demo.concurrency.ThreadStatusDemo$RunningThread.run(ThreadStatusDemo.java:20)

   Locked ownable synchronizers:
        - None

"SleepingThreadName01" #13 prio=5 os_prio=0 cpu=0.28ms elapsed=8.59s tid=0x00007f78142ce000 nid=0x6d23 waiting on condition  [0x00007f77e81a1000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@11.0.9/Native Method)
        at huaminglin.demo.concurrency.ThreadStatusDemo$SleepingThread.run(ThreadStatusDemo.java:20)

   Locked ownable synchronizers:
        - None

"WaitingThreadName01" #14 prio=5 os_prio=0 cpu=0.19ms elapsed=8.59s tid=0x00007f78142cf800 nid=0x6d24 in Object.wait()  [0x00007f77a7ffe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@11.0.9/Native Method)
        - waiting on <0x0000000721112100> (a huaminglin.demo.concurrency.ThreadStatusDemo$WaitingThread)
        at java.lang.Object.wait(java.base@11.0.9/Object.java:328)
        at huaminglin.demo.concurrency.ThreadStatusDemo$WaitingThread.run(ThreadStatusDemo.java:35)
        - waiting to re-lock in wait() <0x0000000721112100> (a huaminglin.demo.concurrency.ThreadStatusDemo$WaitingThread)

   Locked ownable synchronizers:
        - None

"LockThreadName01" #15 prio=5 os_prio=0 cpu=0.48ms elapsed=8.59s tid=0x00007f78142d1800 nid=0x6d25 waiting on condition  [0x00007f77a7efd000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000721111ce8> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.9/AbstractQueuedSynchronizer.java:917)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.9/AbstractQueuedSynchronizer.java:1240)
        at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.9/ReentrantLock.java:267)
        at huaminglin.demo.concurrency.ThreadStatusDemo$LockThread.run(ThreadStatusDemo.java:62)

   Locked ownable synchronizers:
        - None

"ConditionAwaitThreadName01" #16 prio=5 os_prio=0 cpu=0.26ms elapsed=8.59s tid=0x00007f78142d3000 nid=0x6d26 waiting on condition  [0x00007f77a7dfc000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x0000000721111e48> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(java.base@11.0.9/AbstractQueuedSynchronizer.java:2081)
        at huaminglin.demo.concurrency.ThreadStatusDemo$ConditionAwaitThread.run(ThreadStatusDemo.java:78)

   Locked ownable synchronizers:
        - None

"SynchronizedThreadName01" #17 prio=5 os_prio=0 cpu=0.45ms elapsed=8.59s tid=0x00007f78142d5000 nid=0x6d27 waiting for monitor entry  [0x00007f77a7cfb000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at huaminglin.demo.concurrency.ThreadStatusDemo$SynchronizedThread.run(ThreadStatusDemo.java:50)
        - waiting to lock <0x00000007211117f8> (a java.lang.Object)

   Locked ownable synchronizers:
        - None

"ReadInThreadName01" #14 prio=5 os_prio=0 cpu=0.83ms elapsed=22.41s tid=0x00007f6a7c2cf800 nid=0x3b18 runnable  [0x00007f6a51bf9000]
   java.lang.Thread.State: RUNNABLE
        at java.io.FileInputStream.readBytes(java.base@11.0.9/Native Method)
        at java.io.FileInputStream.read(java.base@11.0.9/FileInputStream.java:279)
        at java.io.BufferedInputStream.fill(java.base@11.0.9/BufferedInputStream.java:252)
        at java.io.BufferedInputStream.read(java.base@11.0.9/BufferedInputStream.java:271)
        - locked <0x00000007213057c0> (a java.io.BufferedInputStream)
        at huaminglin.demo.concurrency.ThreadStatusDemo$ReadInThread.run(ThreadStatusDemo.java:96)

   Locked ownable synchronizers:
        - None
```

## Check threads of a process with ps command

ps -o s,tid,comm,stat,%cpu,%mem -T -p 23087

```
ps -o s,tid,comm,stat,%cpu,%mem -T -p 23087
S     TID COMMAND         STAT %CPU %MEM
S   23087 java            Sl    0.0  0.6
S   23089 java            Sl    0.0  0.6
S   23095 GC Thread#0     Sl    0.0  0.6
S   23096 G1 Main Marker  Sl    0.0  0.6
S   23097 G1 Conc#0       Sl    0.0  0.6
S   23100 G1 Refine#0     Sl    0.0  0.6
S   23101 G1 Young RemSet Sl    0.0  0.6
S   23104 VM Thread       Sl    0.0  0.6
S   23107 Reference Handl Sl    0.0  0.6
S   23109 Finalizer       Sl    0.0  0.6
S   23110 Signal Dispatch Sl    0.0  0.6
S   23111 C2 CompilerThre Sl    0.1  0.6
S   23112 C1 CompilerThre Sl    0.0  0.6
S   23113 Sweeper thread  Sl    0.0  0.6
S   23117 Common-Cleaner  Sl    0.0  0.6
S   23120 Monitor Ctrl-Br Sl    0.0  0.6
S   23121 Service Thread  Sl    0.0  0.6
S   23122 VM Periodic Tas Sl    0.0  0.6
R   23123 RunningThreadNa Rl    100  0.6
S   23124 SleepingThreadN Sl    0.0  0.6
S   23125 ReadInThreadNam Sl    0.0  0.6
S   23126 WaitingThreadNa Sl    0.0  0.6
S   23127 LockThreadName0 Sl    0.0  0.6
S   23128 ConditionAwaitT Sl    0.0  0.6
S   23129 SynchronizedThr Sl    0.0  0.6
S   23146 Attach Listener Sl    0.0  0.6
S   23262 RMI TCP Accept- Sl    0.0  0.6
S   23264 RMI TCP Connect Sl    0.1  0.6
S   23265 RMI Scheduler(0 Sl    0.0  0.6
S   23266 JMX server conn Sl    0.0  0.6
S   23272 RMI TCP Connect Sl    0.1  0.6
S   23302 GC Thread#1     Sl    0.0  0.6
S   23303 GC Thread#2     Sl    0.0  0.6
S   23304 GC Thread#3     Sl    0.0  0.6
S   23305 GC Thread#4     Sl    0.0  0.6
S   23306 GC Thread#5     Sl    0.0  0.6
S   23307 GC Thread#6     Sl    0.0  0.6
S   23308 GC Thread#7     Sl    0.0  0.6
```


top -H -p <pid> > /tmp/1.txt; cat /tmp/1.txt

```
Threads:  31 total,   1 running,  30 sleeping,   0 stopped,   0 zombie
%Cpu(s): 16.0 us,  0.3 sy,  0.0 ni, 81.7 id,  0.0 wa,  0.0 hi,  2.0 si,  0.0 st
MiB Mem :  15204.2 total,   4834.1 free,   4189.0 used,   6181.1 buff/cache
MiB Swap:    976.0 total,    976.0 free,      0.0 used.   9685.7 avail Mem 

    PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND                                                                                                                                                                                                    
  22646 user01    20   0 7647220  86784  27984 R  99.9   0.6   0:47.66 RunningThreadNa                                                                                                                                                                                            
  22631 user01    20   0 7647220  86784  27984 S  25.6   0.6   0:00.55 C2 CompilerThre                                                                                                                                                                                            
  22610 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 java                                                                                                                                                                                                       
  22613 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.06 java                                                                                                                                                                                                       
  22616 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 GC Thread#0                                                                                                                                                                                                
  22619 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 G1 Main Marker                                                                                                                                                                                             
  22620 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 G1 Conc#0                                                                                                                                                                                                  
  22621 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 G1 Refine#0                                                                                                                                                                                                
  22622 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 G1 Young RemSet                                                                                                                                                                                            
  22626 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 VM Thread                                                                                                                                                                                                  
  22627 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Reference Handl                                                                                                                                                                                            
  22628 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Finalizer                                                                                                                                                                                                  
  22630 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Signal Dispatch                                                                                                                                                                                            
  22633 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.28 C1 CompilerThre                                                                                                                                                                                            
  22636 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Sweeper thread                                                                                                                                                                                             
  22640 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Common-Cleaner                                                                                                                                                                                             
  22643 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Monitor Ctrl-Br                                                                                                                                                                                            
  22644 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 Service Thread                                                                                                                                                                                             
  22645 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 VM Periodic Tas                                                                                                                                                                                            
  22647 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 SleepingThreadN                                                                                                                                                                                            
  22648 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 ReadInThreadNam                                                                                                                                                                                            
  22649 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 WaitingThreadNa                                                                                                                                                                                            
  22650 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 LockThreadName0                                                                                                                                                                                            
  22651 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 ConditionAwaitT                                                                                                                                                                                            
  22652 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 SynchronizedThr                                                                                                                                                                                            
  22671 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.13 Attach Listener                                                                                                                                                                                            
  22694 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 RMI TCP Accept-                                                                                                                                                                                            
  22696 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.11 RMI TCP Connect                                                                                                                                                                                            
  22697 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 RMI Scheduler(0                                                                                                                                                                                            
  22698 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.00 JMX server conn                                                                                                                                                                                            
  22704 user01    20   0 7647220  86784  27984 S   0.0   0.6   0:00.04 RMI TCP Connect
```

It seems the COMMAND column shows thread name.

"Threads:  31 total,   1 running,  30 sleeping,   0 stopped,   0 zombie"

So threads are grouped into 4: running, sleeping, stopped, zombie.

java.lang.Thread.State: RUNNABLE can be shown as "sleeping" in the perspective of kernel.
