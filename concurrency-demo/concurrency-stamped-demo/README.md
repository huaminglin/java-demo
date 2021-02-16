# StampedLock Demo

## Console log

```
Thread tryOptimisticRead run()
Thread tryOptimisticRead tryOptimisticRead(): 256
Thread writeLock run()
Thread writeLock writeLock(): 384
Thread writeLock exit()
tryOptimisticRead fails to validate when there is a write operation finished
Thread tryOptimisticRead exit()
Thread tryOptimisticRead 02 run()
Thread tryOptimisticRead 02 tryOptimisticRead(): 512
Thread writeLock 02 run()
Thread 02 writeLock writeLock(): 640
Thread tryOptimisticRead 02 tryOptimisticRead fails to validate when there is a write operation ongoing
Thread tryOptimisticRead 02 exit()
Thread writeLock 03 run()
Thread tryOptimisticRead 03 run()
Thread tryOptimisticRead 03 tryOptimisticRead(): 0
Thread tryOptimisticRead 03 tryOptimisticRead fails to call tryOptimisticRead().
Thread tryOptimisticRead 03 exit()
Thread 02 writeLock exit()
Thread 03 writeLock writeLock(): 896
Thread 03 writeLock stamp changed.
Thread 03 writeLock exit()
Thread 0401 writeLock  run()
Thread 0401 writeLock writeLock(): 1152
Thread 0402 writeLock  run()
Thread 0401 writeLock exit()
Thread 0402 writeLock writeLock(): 1408
Thread 0402 writeLock exit()
main() exit.
```

Note: "Thread tryOptimisticRead 03 tryOptimisticRead(): 0";
When there are write locks, tryOptimisticRead() return 0.

Note: "Thread 03 writeLock stamp changed.";
Before "writeLock()", we can "tryOptimisticRead()" and do some preparation;
In this way, we have a change to reduce the scope of race condition.

## jodd.cache.AbstractCacheMap.lock

```
	private final StampedLock lock = new StampedLock();


	@Override
	public V get(final K key) {
		long stamp = lock.readLock();

		try {
			CacheObject<K,V> co = cacheMap.get(key);
			if (co == null) {
				missCount++;
				return null;
			}
			if (co.isExpired()) {
				final long newStamp = lock.tryConvertToWriteLock(stamp);

				if (newStamp != 0L) {
					stamp = newStamp;
					// lock is upgraded to write lock
				}
				else {
					// manually upgrade lock to write lock
					lock.unlockRead(stamp);
					stamp = lock.writeLock();
				}

				CacheObject<K,V> removedCo = cacheMap.remove(key);
				if (removedCo != null) {
					onRemove(removedCo.key, removedCo.cachedObject);
				}

				missCount++;
				return null;
			}

			hitCount++;
			return co.getObject();
		}
		finally {
			lock.unlock(stamp);
		}
	}
```

Note: Try to find StampedLock usage, but only finds jodd.cache.AbstractCacheMap.

And no "tryOptimisticRead()" sample is found.

## tryOptimisticRead()

The search engine result shows "tryOptimisticRead()" is used to protect the read/write of a pair of variables.
This prevent us from using the old value of variable A to work with the new value of variable B.
Step 1: call "long stamp = stampedLock.tryOptimisticRead();" and read variable A and variable B.
Step 2: validate "stampedLock.validate(stamp)" and use variable A and variable B.

Write operation: Call "stampedLock.writeLock()"; update variable A and/or variable B; stampedLock.unlockWrite(stamp).


## Some public APIs

```
long tryConvertToOptimisticRead(long stamp) 
long tryConvertToReadLock(long stamp)
long tryConvertToWriteLock(long stamp)
long tryOptimisticRead()
long tryReadLock()
long tryReadLock(long time, TimeUnit unit)
long tryWriteLock()
long tryWriteLock(long time, TimeUnit unit)
boolean validate(long stamp)

long readLock()
long readLockInterruptibly() throws InterruptedException 
long writeLock() 
long writeLockInterruptibly()

static boolean isLockStamp(long stamp) 
static boolean isOptimisticReadStamp(long stamp)
static boolean isReadLockStamp(long stamp)
static boolean isWriteLockStamp(long stamp)
```

java.util.concurrent.locks.StampedLock.WNode
```
    /** Wait nodes */
    static final class WNode {
        volatile WNode prev;
        volatile WNode next;
        volatile WNode cowait;    // list of linked readers
        volatile Thread thread;   // non-null while possibly parked
        volatile int status;      // 0, WAITING, or CANCELLED
        final int mode;           // RMODE or WMODE
        WNode(int m, WNode p) { mode = m; prev = p; }
    }
```
