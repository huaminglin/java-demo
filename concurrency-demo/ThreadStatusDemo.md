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

"ServerSocketThreadName01" #16 prio=5 os_prio=0 cpu=0.73ms elapsed=13.05s tid=0x00007fb2682ea000 nid=0x5dab runnable  [0x00007fb23ddfc000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(java.base@11.0.9.1/Native Method)
        at java.net.AbstractPlainSocketImpl.accept(java.base@11.0.9.1/AbstractPlainSocketImpl.java:458)
        at java.net.ServerSocket.implAccept(java.base@11.0.9.1/ServerSocket.java:565)
        at java.net.ServerSocket.accept(java.base@11.0.9.1/ServerSocket.java:533)
        at huaminglin.demo.concurrency.ThreadStatusDemo$ServerSocketThread.run(ThreadStatusDemo.java:125)

   Locked ownable synchronizers:
        - None

"ClientSocketThreadName01" #17 prio=5 os_prio=0 cpu=6.05ms elapsed=13.72s tid=0x00007f023833c000 nid=0x5f16 runnable  [0x00007f01f2b80000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(java.base@11.0.9.1/Native Method)
        at java.net.SocketInputStream.socketRead(java.base@11.0.9.1/SocketInputStream.java:115)
        at java.net.SocketInputStream.read(java.base@11.0.9.1/SocketInputStream.java:168)
        at java.net.SocketInputStream.read(java.base@11.0.9.1/SocketInputStream.java:140)
        at java.net.SocketInputStream.read(java.base@11.0.9.1/SocketInputStream.java:200)
        at huaminglin.demo.concurrency.ThreadStatusDemo$ClientSocketThread.run(ThreadStatusDemo.java:141)

   Locked ownable synchronizers:
        - None

"ServerSocketChannelThreadName01" #18 prio=5 os_prio=0 cpu=23.88ms elapsed=82.54s tid=0x00007f390032d800 nid=0x72b9 runnable  [0x00007f388bdfc000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@11.0.9.1/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@11.0.9.1/EPollSelectorImpl.java:120)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@11.0.9.1/SelectorImpl.java:124)
        - locked <0x0000000720f13250> (a sun.nio.ch.Util$2)
        - locked <0x0000000720f12ec8> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@11.0.9.1/SelectorImpl.java:141)
        at huaminglin.demo.concurrency.ThreadStatusDemo$ServerSocketChannelThread.run(ThreadStatusDemo.java:180)

   Locked ownable synchronizers:
        - None

```

## Check threads of a process with ps command

ps -o s,tid,comm,stat,%cpu,%mem -T -p 30552

```
S     TID COMMAND         STAT %CPU %MEM
S   30552 java            Sl    0.0  0.2
S   30557 java            Sl    0.3  0.2
S   30560 GC Thread#0     Sl    0.0  0.2
S   30561 G1 Main Marker  Sl    0.0  0.2
S   30562 G1 Conc#0       Sl    0.0  0.2
S   30564 G1 Refine#0     Sl    0.0  0.2
S   30565 G1 Young RemSet Sl    0.0  0.2
S   30571 VM Thread       Sl    0.0  0.2
S   30572 Reference Handl Sl    0.0  0.2
S   30574 Finalizer       Sl    0.0  0.2
S   30575 Signal Dispatch Sl    0.0  0.2
S   30576 C2 CompilerThre Sl    0.1  0.2
S   30577 C1 CompilerThre Sl    0.3  0.2
S   30578 Sweeper thread  Sl    0.0  0.2
S   30582 Common-Cleaner  Sl    0.0  0.2
S   30585 Monitor Ctrl-Br Sl    0.0  0.2
S   30586 Service Thread  Sl    0.0  0.2
S   30587 VM Periodic Tas Sl    0.0  0.2
R   30588 RunningThreadNa Rl    101  0.2
S   30589 SleepingThreadN Sl    0.0  0.2
S   30590 ReadInThreadNam Sl    0.0  0.2
S   30591 ServerSocketThr Sl    0.0  0.2
S   30592 ClientSocketThr Sl    0.0  0.2
S   30593 ServerSocketCha Sl    0.0  0.2
S   30594 WaitingThreadNa Sl    0.0  0.2
S   30595 LockThreadName0 Sl    0.0  0.2
S   30596 ConditionAwaitT Sl    0.0  0.2
S   30597 SynchronizedThr Sl    0.0  0.2
S   30614 Attach Listener Sl    0.0  0.2


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

## nc 127.0.0.1 9999
