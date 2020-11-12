# Dead lock demo

## SynchronizedDeadLockDemo

```
Found one Java-level deadlock:
=============================
"SynchronizedAThreadName01":
  waiting to lock monitor 0x00007f1f98007180 (object 0x000000072110da88, a java.lang.Object),
  which is held by "SynchronizedBThreadName01"
"SynchronizedBThreadName01":
  waiting to lock monitor 0x00007f1f98009180 (object 0x000000072110da78, a java.lang.Object),
  which is held by "SynchronizedAThreadName01"

Java stack information for the threads listed above:
===================================================
"SynchronizedAThreadName01":
        at huaminglin.demo.concurrency.DeadLockDemo$SynchronizedAThread.run(DeadLockDemo.java:16)
        - waiting to lock <0x000000072110da88> (a java.lang.Object)
        - locked <0x000000072110da78> (a java.lang.Object)
"SynchronizedBThreadName01":
        at huaminglin.demo.concurrency.DeadLockDemo$SynchronizedBThread.run(DeadLockDemo.java:36)
        - waiting to lock <0x000000072110da78> (a java.lang.Object)
        - locked <0x000000072110da88> (a java.lang.Object)

Found 1 deadlock.
```

Note: No exception is thrown on code level.


## LockDeadLockDemo

```
Found one Java-level deadlock:
=============================
"LockAThreadName01":
  waiting for ownable synchronizer 0x000000072110dd98, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "LockBThreadName01"
"LockBThreadName01":
  waiting for ownable synchronizer 0x000000072110dd68, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
  which is held by "LockAThreadName01"

Java stack information for the threads listed above:
===================================================
"LockAThreadName01":
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000072110dd98> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.9/AbstractQueuedSynchronizer.java:917)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.9/AbstractQueuedSynchronizer.java:1240)
        at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.9/ReentrantLock.java:267)
        at huaminglin.demo.concurrency.LockDeadLockDemo$LockAThread.run(LockDeadLockDemo.java:18)
"LockBThreadName01":
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000072110dd68> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.9/AbstractQueuedSynchronizer.java:917)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.9/AbstractQueuedSynchronizer.java:1240)
        at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.9/ReentrantLock.java:267)
        at huaminglin.demo.concurrency.LockDeadLockDemo$LockBThread.run(LockDeadLockDemo.java:36)

Found 1 deadlock.
```