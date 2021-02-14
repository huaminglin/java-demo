# ReadWriteLock Demo

## writeLock() is blocked by readLock()

```
Read Thread 0 run()
Read Thread 1 run()
Read Thread 0 lock()
Read Thread 1 lock()
Read Thread 0 exit()
main() lock()
Read Thread 1 exit()
Last Write Thread run()
Last Read Thread run()
Last Read 02 Thread run()
Last Write 02 Thread run()
Last Write Thread lock()
Last Write Thread exit()
Last Read 02 Thread lock()
Last Read Thread lock()
Last Read 02 Thread exit()
Last Read Thread exit()
Last Write 02 Thread lock()
Last Write 02 Thread exit()
main() exit
```

Note: "Read Thread 0 lock()" -> "Read Thread 1 lock()" -> "Read Thread 0 exit()";
Fom this log, we know the reads run at the same times.

Note: "Read Thread 0 lock()" -> "Read Thread 1 lock()" -> "main() lock()";
From this log, we know writeLock() is blocked by readLock().

Note: "Last Write Thread lock()" -> "Last Read Thread lock()" -> "Last Write 02 Thread lock()";
From this log, we know that the read and write request are put in the same sync queue.

Note: "Last Write Thread lock()" -> "Last Read 02 Thread lock()" -> "Last Read Thread lock()" -> "Last Read 02 Thread exit()" -> "Last Read Thread exit()"; 
From this log, we know that the write unlock awake the following two read requests.

## Java doc

```
Although the basic operation of a read-write lock is straight-forward, there are many policy decisions that an implementation must make, which may affect the effectiveness of the read-write lock in a given application.

Examples of these policies include:

Determining whether to grant the read lock or the write lock, when both readers and writers are waiting, at the time that a writer releases the write lock. 
Writer preference is common, as writes are expected to be short and infrequent.
Reader preference is less common as it can lead to lengthy delays for a write if the readers are frequent and long-lived as expected.
Fair, or "in-order" implementations are also possible.

Determining whether readers that request the read lock while a reader is active and a writer is waiting, are granted the read lock.
Preference to the reader can delay the writer indefinitely, while preference to the writer can reduce the potential for concurrency.

Determining whether the locks are reentrant: can a thread with the write lock reacquire it? Can it acquire a read lock while holding the write lock? Is the read lock itself reentrant?

Can the write lock be downgraded to a read lock without allowing an intervening writer? Can a read lock be upgraded to a write lock, in preference to other waiting readers or writers?

You should consider all of these things when evaluating the suitability of a given implementation for your application.
```

## java.util.concurrent.locks.AbstractQueuedSynchronizer.setHeadAndPropagate

```
    /**
     * Sets head of queue, and checks if successor may be waiting
     * in shared mode, if so propagating if either propagate > 0 or
     * PROPAGATE status was set.
     *
     * @param node the node
     * @param propagate the return value from a tryAcquireShared
     */
    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head; // Record old head for check below
        setHead(node);
        /*
         * Try to signal next queued node if:
         *   Propagation was indicated by caller,
         *     or was recorded (as h.waitStatus either before
         *     or after setHead) by a previous operation
         *     (note: this uses sign-check of waitStatus because
         *      PROPAGATE status may transition to SIGNAL.)
         * and
         *   The next node is waiting in shared mode,
         *     or we don't know, because it appears null
         *
         * The conservatism in both of these checks may cause
         * unnecessary wake-ups, but only when there are multiple
         * racing acquires/releases, so most need signals now or soon
         * anyway.
         */
        if (propagate > 0 || h == null || h.waitStatus < 0 ||
            (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }

    /**
     * Acquires in shared uninterruptible mode.
     * @param arg the acquire argument
     */
    private void doAcquireShared(int arg) {
        final Node node = addWaiter(Node.SHARED);
        boolean interrupted = false;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head) {
                    int r = tryAcquireShared(arg);
                    if (r >= 0) {
                        setHeadAndPropagate(node, r);
                        p.next = null; // help GC
                        return;
                    }
                }
                if (shouldParkAfterFailedAcquire(p, node))
                    interrupted |= parkAndCheckInterrupt();
            }
        } catch (Throwable t) {
            cancelAcquire(node);
            throw t;
        } finally {
            if (interrupted)
                selfInterrupt();
        }
    }
```

In this way, when a read request acquires lock, it unlock the following read request as well.

The result of "tryAcquireShared()" is used as propagate flag.
