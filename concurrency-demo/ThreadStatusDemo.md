# Thread Status Demo

## Run the application and dump the thread

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
```
