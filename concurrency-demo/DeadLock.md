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

## ReadLockDeadLockDemo: Lock the write lock first (The lock is registered in "Locked ownable synchronizers")

```
"LockAThreadName01" #13 prio=5 os_prio=0 cpu=2.42ms elapsed=66.16s tid=0x00007f0a402fc800 nid=0x354a waiting on condition  [0x00007f0a15085000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000071283d1c8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1009)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1324)
        at java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:738)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockAThread.run(ReadLockDeadLockDemo.java:18)

   Locked ownable synchronizers:
        - <0x000000071283cfb8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)

"LockBThreadName01" #14 prio=5 os_prio=0 cpu=1.25ms elapsed=66.16s tid=0x00007f0a402fe800 nid=0x354b waiting on condition  [0x00007f0a14f84000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000071283cfb8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1009)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1324)
        at java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:738)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockBThread.run(ReadLockDeadLockDemo.java:36)

   Locked ownable synchronizers:
        - <0x000000071283d1c8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)

Found one Java-level deadlock:
=============================
"LockAThreadName01":
  waiting for ownable synchronizer 0x000000071283d1c8, (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync),
  which is held by "LockBThreadName01"
"LockBThreadName01":
  waiting for ownable synchronizer 0x000000071283cfb8, (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync),
  which is held by "LockAThreadName01"

Java stack information for the threads listed above:
===================================================
"LockAThreadName01":
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000071283d1c8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1009)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1324)
        at java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:738)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockAThread.run(ReadLockDeadLockDemo.java:18)
"LockBThreadName01":
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000071283cfb8> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1009)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireShared(java.base@11.0.9/AbstractQueuedSynchronizer.java:1324)
        at java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:738)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockBThread.run(ReadLockDeadLockDemo.java:36)

Found 1 deadlock.
```

Note: Since JVM tracks write lock, if read lock is blocked by write lock and dead lock happens, JVM will detect it.

## ReadLockDeadLockDemo: Lock the read lock first (The lock is not registered in "Locked ownable synchronizers")

```
"LockAThreadName01" #13 prio=5 os_prio=0 cpu=1.93ms elapsed=47.11s tid=0x00007f9ed8302800 nid=0x3734 waiting on condition  [0x00007f9e83bf9000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000072110e688> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.9/AbstractQueuedSynchronizer.java:917)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.9/AbstractQueuedSynchronizer.java:1240)
        at java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:959)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockAThread.run(ReadLockDeadLockDemo.java:18)

   Locked ownable synchronizers:
        - None

"LockBThreadName01" #14 prio=5 os_prio=0 cpu=0.63ms elapsed=47.11s tid=0x00007f9ed8304000 nid=0x3735 waiting on condition  [0x00007f9e83af8000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@11.0.9/Native Method)
        - parking to wait for  <0x000000072110e448> (a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@11.0.9/LockSupport.java:194)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.9/AbstractQueuedSynchronizer.java:885)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.9/AbstractQueuedSynchronizer.java:917)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.9/AbstractQueuedSynchronizer.java:1240)
        at java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lock(java.base@11.0.9/ReentrantReadWriteLock.java:959)
        at huaminglin.demo.concurrency.ReadLockDeadLockDemo$LockBThread.run(ReadLockDeadLockDemo.java:36)

   Locked ownable synchronizers:
        - None
```

Note: JVM doesn't track allocated read locks. So if a read lock is allocated, the later write lock will be blocked. Then a dead lock can happens, but JVM will not detected.
