# ForkJoinPool

## Console log

```
ForkJoinPool-1-worker-1/first start: [ 1, 10 ]
ForkJoinPool-1-worker-3/second start: [ 11, 130 ]
ForkJoinPool-1-worker-3/second start: [ 11, 70 ]
ForkJoinPool-1-worker-1/first start: [ 1, 5 ]
ForkJoinPool-1-worker-1/first start: [ 1, 3 ]
ForkJoinPool-1-worker-3/second start: [ 11, 40 ]
ForkJoinPool-1-worker-1/first result: 6
ForkJoinPool-1-worker-1/first start: [ 4, 5 ]
ForkJoinPool-1-worker-1/first result: 9
ForkJoinPool-1-worker-1/first result: 15
ForkJoinPool-1-worker-1/first start: [ 6, 10 ]
ForkJoinPool-1-worker-3/second start: [ 11, 25 ]
ForkJoinPool-1-worker-1/first start: [ 6, 8 ]
ForkJoinPool-1-worker-1/first result: 21
ForkJoinPool-1-worker-1/first start: [ 9, 10 ]
ForkJoinPool-1-worker-1/first result: 19
ForkJoinPool-1-worker-1/first result: 40
ForkJoinPool-1-worker-1/first result: 55
ForkJoinPool-1-worker-1/second start: [ 71, 130 ]
First Result: 55
ForkJoinPool-1-worker-3/second start: [ 11, 18 ]
ForkJoinPool-1-worker-1/second start: [ 71, 100 ]
ForkJoinPool-1-worker-3/second start: [ 11, 14 ]
ForkJoinPool-1-worker-1/second start: [ 71, 85 ]
ForkJoinPool-1-worker-3/second start: [ 11, 12 ]
ForkJoinPool-1-worker-3/second result: 23
ForkJoinPool-1-worker-3/second start: [ 13, 14 ]
ForkJoinPool-1-worker-3/second result: 27
ForkJoinPool-1-worker-3/second result: 50
ForkJoinPool-1-worker-3/second start: [ 15, 18 ]
ForkJoinPool-1-worker-1/second start: [ 71, 78 ]
ForkJoinPool-1-worker-3/second start: [ 15, 16 ]
ForkJoinPool-1-worker-3/second result: 31
ForkJoinPool-1-worker-3/second start: [ 17, 18 ]
ForkJoinPool-1-worker-3/second result: 35
ForkJoinPool-1-worker-3/second result: 66
ForkJoinPool-1-worker-3/second result: 116
ForkJoinPool-1-worker-3/second start: [ 19, 25 ]
ForkJoinPool-1-worker-1/second start: [ 71, 74 ]
ForkJoinPool-1-worker-3/second start: [ 19, 22 ]
ForkJoinPool-1-worker-1/second start: [ 71, 72 ]
ForkJoinPool-1-worker-1/second result: 143
ForkJoinPool-1-worker-1/second start: [ 73, 74 ]
ForkJoinPool-1-worker-1/second result: 147
ForkJoinPool-1-worker-1/second result: 290
ForkJoinPool-1-worker-1/second start: [ 75, 78 ]
ForkJoinPool-1-worker-3/second start: [ 19, 20 ]
ForkJoinPool-1-worker-3/second result: 39
ForkJoinPool-1-worker-3/second start: [ 21, 22 ]
ForkJoinPool-1-worker-3/second result: 43
ForkJoinPool-1-worker-3/second result: 82
ForkJoinPool-1-worker-3/second start: [ 23, 25 ]
ForkJoinPool-1-worker-3/second result: 72
ForkJoinPool-1-worker-3/second result: 154
ForkJoinPool-1-worker-3/second result: 270
ForkJoinPool-1-worker-3/second start: [ 26, 40 ]
ForkJoinPool-1-worker-1/second start: [ 75, 76 ]
ForkJoinPool-1-worker-1/second result: 151
ForkJoinPool-1-worker-1/second start: [ 77, 78 ]
ForkJoinPool-1-worker-1/second result: 155
ForkJoinPool-1-worker-1/second result: 306
ForkJoinPool-1-worker-1/second result: 596
ForkJoinPool-1-worker-1/second start: [ 79, 85 ]
ForkJoinPool-1-worker-3/second start: [ 26, 33 ]
ForkJoinPool-1-worker-1/second start: [ 79, 82 ]
ForkJoinPool-1-worker-3/second start: [ 26, 29 ]
ForkJoinPool-1-worker-1/second start: [ 79, 80 ]
ForkJoinPool-1-worker-1/second result: 159
ForkJoinPool-1-worker-1/second start: [ 81, 82 ]
ForkJoinPool-1-worker-1/second result: 163
ForkJoinPool-1-worker-1/second result: 322
ForkJoinPool-1-worker-1/second start: [ 83, 85 ]
ForkJoinPool-1-worker-1/second result: 252
ForkJoinPool-1-worker-1/second result: 574
ForkJoinPool-1-worker-1/second result: 1170
ForkJoinPool-1-worker-1/second start: [ 86, 100 ]
ForkJoinPool-1-worker-3/second start: [ 26, 27 ]
ForkJoinPool-1-worker-3/second result: 53
ForkJoinPool-1-worker-3/second start: [ 28, 29 ]
ForkJoinPool-1-worker-3/second result: 57
ForkJoinPool-1-worker-3/second result: 110
ForkJoinPool-1-worker-3/second start: [ 30, 33 ]
ForkJoinPool-1-worker-1/second start: [ 86, 93 ]
ForkJoinPool-1-worker-3/second start: [ 30, 31 ]
ForkJoinPool-1-worker-3/second result: 61
ForkJoinPool-1-worker-3/second start: [ 32, 33 ]
ForkJoinPool-1-worker-3/second result: 65
ForkJoinPool-1-worker-3/second result: 126
ForkJoinPool-1-worker-3/second result: 236
ForkJoinPool-1-worker-3/second start: [ 34, 40 ]
ForkJoinPool-1-worker-1/second start: [ 86, 89 ]
ForkJoinPool-1-worker-3/second start: [ 34, 37 ]
ForkJoinPool-1-worker-1/second start: [ 86, 87 ]
ForkJoinPool-1-worker-1/second result: 173
ForkJoinPool-1-worker-1/second start: [ 88, 89 ]
ForkJoinPool-1-worker-1/second result: 177
ForkJoinPool-1-worker-1/second result: 350
ForkJoinPool-1-worker-1/second start: [ 90, 93 ]
ForkJoinPool-1-worker-3/second start: [ 34, 35 ]
ForkJoinPool-1-worker-3/second result: 69
ForkJoinPool-1-worker-3/second start: [ 36, 37 ]
ForkJoinPool-1-worker-3/second result: 73
ForkJoinPool-1-worker-3/second result: 142
ForkJoinPool-1-worker-3/second start: [ 38, 40 ]
ForkJoinPool-1-worker-3/second result: 117
ForkJoinPool-1-worker-3/second result: 259
ForkJoinPool-1-worker-3/second result: 495
ForkJoinPool-1-worker-3/second result: 765
ForkJoinPool-1-worker-3/second start: [ 41, 70 ]
ForkJoinPool-1-worker-1/second start: [ 90, 91 ]
ForkJoinPool-1-worker-1/second result: 181
ForkJoinPool-1-worker-1/second start: [ 92, 93 ]
ForkJoinPool-1-worker-1/second result: 185
ForkJoinPool-1-worker-1/second result: 366
ForkJoinPool-1-worker-1/second result: 716
ForkJoinPool-1-worker-1/second start: [ 94, 100 ]
ForkJoinPool-1-worker-3/second start: [ 41, 55 ]
ForkJoinPool-1-worker-1/second start: [ 94, 97 ]
ForkJoinPool-1-worker-3/second start: [ 41, 48 ]
ForkJoinPool-1-worker-1/second start: [ 94, 95 ]
ForkJoinPool-1-worker-1/second result: 189
ForkJoinPool-1-worker-1/second start: [ 96, 97 ]
ForkJoinPool-1-worker-1/second result: 193
ForkJoinPool-1-worker-1/second result: 382
ForkJoinPool-1-worker-1/second start: [ 98, 100 ]
ForkJoinPool-1-worker-1/second result: 297
ForkJoinPool-1-worker-1/second result: 679
ForkJoinPool-1-worker-1/second result: 1395
ForkJoinPool-1-worker-1/second result: 2565
ForkJoinPool-1-worker-1/second start: [ 101, 130 ]
ForkJoinPool-1-worker-3/second start: [ 41, 44 ]
ForkJoinPool-1-worker-1/second start: [ 101, 115 ]
ForkJoinPool-1-worker-3/second start: [ 41, 42 ]
ForkJoinPool-1-worker-3/second result: 83
ForkJoinPool-1-worker-3/second start: [ 43, 44 ]
ForkJoinPool-1-worker-3/second result: 87
ForkJoinPool-1-worker-3/second result: 170
ForkJoinPool-1-worker-3/second start: [ 45, 48 ]
ForkJoinPool-1-worker-1/second start: [ 101, 108 ]
ForkJoinPool-1-worker-3/second start: [ 45, 46 ]
ForkJoinPool-1-worker-3/second result: 91
ForkJoinPool-1-worker-3/second start: [ 47, 48 ]
ForkJoinPool-1-worker-3/second result: 95
ForkJoinPool-1-worker-3/second result: 186
ForkJoinPool-1-worker-3/second result: 356
ForkJoinPool-1-worker-3/second start: [ 49, 55 ]
ForkJoinPool-1-worker-1/second start: [ 101, 104 ]
ForkJoinPool-1-worker-3/second start: [ 49, 52 ]
ForkJoinPool-1-worker-1/second start: [ 101, 102 ]
ForkJoinPool-1-worker-1/second result: 203
ForkJoinPool-1-worker-1/second start: [ 103, 104 ]
ForkJoinPool-1-worker-1/second result: 207
ForkJoinPool-1-worker-1/second result: 410
ForkJoinPool-1-worker-1/second start: [ 105, 108 ]
ForkJoinPool-1-worker-3/second start: [ 49, 50 ]
ForkJoinPool-1-worker-3/second result: 99
ForkJoinPool-1-worker-3/second start: [ 51, 52 ]
ForkJoinPool-1-worker-3/second result: 103
ForkJoinPool-1-worker-3/second result: 202
ForkJoinPool-1-worker-3/second start: [ 53, 55 ]
ForkJoinPool-1-worker-3/second result: 162
ForkJoinPool-1-worker-3/second result: 364
ForkJoinPool-1-worker-3/second result: 720
ForkJoinPool-1-worker-3/second start: [ 56, 70 ]
ForkJoinPool-1-worker-1/second start: [ 105, 106 ]
ForkJoinPool-1-worker-1/second result: 211
ForkJoinPool-1-worker-1/second start: [ 107, 108 ]
ForkJoinPool-1-worker-1/second result: 215
ForkJoinPool-1-worker-1/second result: 426
ForkJoinPool-1-worker-1/second result: 836
ForkJoinPool-1-worker-1/second start: [ 109, 115 ]
ForkJoinPool-1-worker-3/second start: [ 56, 63 ]
ForkJoinPool-1-worker-1/second start: [ 109, 112 ]
ForkJoinPool-1-worker-3/second start: [ 56, 59 ]
ForkJoinPool-1-worker-1/second start: [ 109, 110 ]
ForkJoinPool-1-worker-1/second result: 219
ForkJoinPool-1-worker-1/second start: [ 111, 112 ]
ForkJoinPool-1-worker-1/second result: 223
ForkJoinPool-1-worker-1/second result: 442
ForkJoinPool-1-worker-1/second start: [ 113, 115 ]
ForkJoinPool-1-worker-1/second result: 342
ForkJoinPool-1-worker-1/second result: 784
ForkJoinPool-1-worker-1/second result: 1620
ForkJoinPool-1-worker-1/second start: [ 116, 130 ]
ForkJoinPool-1-worker-3/second start: [ 56, 57 ]
ForkJoinPool-1-worker-3/second result: 113
ForkJoinPool-1-worker-3/second start: [ 58, 59 ]
ForkJoinPool-1-worker-3/second result: 117
ForkJoinPool-1-worker-3/second result: 230
ForkJoinPool-1-worker-3/second start: [ 60, 63 ]
ForkJoinPool-1-worker-1/second start: [ 116, 123 ]
ForkJoinPool-1-worker-3/second start: [ 60, 61 ]
ForkJoinPool-1-worker-3/second result: 121
ForkJoinPool-1-worker-3/second start: [ 62, 63 ]
ForkJoinPool-1-worker-3/second result: 125
ForkJoinPool-1-worker-3/second result: 246
ForkJoinPool-1-worker-3/second result: 476
ForkJoinPool-1-worker-3/second start: [ 64, 70 ]
ForkJoinPool-1-worker-1/second start: [ 116, 119 ]
ForkJoinPool-1-worker-3/second start: [ 64, 67 ]
ForkJoinPool-1-worker-1/second start: [ 116, 117 ]
ForkJoinPool-1-worker-1/second result: 233
ForkJoinPool-1-worker-1/second start: [ 118, 119 ]
ForkJoinPool-1-worker-1/second result: 237
ForkJoinPool-1-worker-1/second result: 470
ForkJoinPool-1-worker-1/second start: [ 120, 123 ]
ForkJoinPool-1-worker-3/second start: [ 64, 65 ]
ForkJoinPool-1-worker-3/second result: 129
ForkJoinPool-1-worker-3/second start: [ 66, 67 ]
ForkJoinPool-1-worker-3/second result: 133
ForkJoinPool-1-worker-3/second result: 262
ForkJoinPool-1-worker-3/second start: [ 68, 70 ]
ForkJoinPool-1-worker-3/second result: 207
ForkJoinPool-1-worker-3/second result: 469
ForkJoinPool-1-worker-3/second result: 945
ForkJoinPool-1-worker-3/second result: 1665
ForkJoinPool-1-worker-3/second result: 2430
ForkJoinPool-1-worker-3/second start: [ 124, 130 ]
ForkJoinPool-1-worker-1/second start: [ 120, 121 ]
ForkJoinPool-1-worker-1/second result: 241
ForkJoinPool-1-worker-1/second start: [ 122, 123 ]
ForkJoinPool-1-worker-1/second result: 245
ForkJoinPool-1-worker-1/second result: 486
ForkJoinPool-1-worker-1/second result: 956
ForkJoinPool-1-worker-5/second start: [ 128, 130 ]
ForkJoinPool-1-worker-3/second start: [ 124, 127 ]
ForkJoinPool-1-worker-5/second result: 387
ForkJoinPool-1-worker-5/second start: [ 126, 127 ]
ForkJoinPool-1-worker-3/second start: [ 124, 125 ]
ForkJoinPool-1-worker-3/second result: 249
ForkJoinPool-1-worker-5/second result: 253
ForkJoinPool-1-worker-3/second result: 502
ForkJoinPool-1-worker-3/second result: 889
ForkJoinPool-1-worker-1/second result: 1845
ForkJoinPool-1-worker-1/second result: 3465
ForkJoinPool-1-worker-1/second result: 6030
ForkJoinPool-1-worker-3/second result: 8460
Second Result: 8460
```

## java.util.concurrent.ForkJoinPool.signalWork

```
    /**
     * Tries to create or release a worker if too few are running.
     */
    final void signalWork() {
        for (;;) {
            long c; int sp; WorkQueue[] ws; int i; WorkQueue v;
            if ((c = ctl) >= 0L)                      // enough workers
                break;
            else if ((sp = (int)c) == 0) {            // no idle workers
                if ((c & ADD_WORKER) != 0L)           // too few workers
                    tryAddWorker(c);
                break;
            }
            else if ((ws = workQueues) == null)
                break;                                // unstarted/terminated
            else if (ws.length <= (i = sp & SMASK))
                break;                                // terminated
            else if ((v = ws[i]) == null)
                break;                                // terminating
            else {
                int np = sp & ~UNSIGNALLED;
                int vp = v.phase;
                long nc = (v.stackPred & SP_MASK) | (UC_MASK & (c + RC_UNIT));
                Thread vt = v.owner;
                if (sp == vp && CTL.compareAndSet(this, c, nc)) {
                    v.phase = np;
                    if (vt != null && v.source < 0)
                        LockSupport.unpark(vt);
                    break;
                }
            }
        }
    }
```

java.util.concurrent.ForkJoinPool.createWorker

```
    /**
     * Tries to construct and start one worker. Assumes that total
     * count has already been incremented as a reservation.  Invokes
     * deregisterWorker on any failure.
     *
     * @return true if successful
     */
    private boolean createWorker() {
        ForkJoinWorkerThreadFactory fac = factory;
        Throwable ex = null;
        ForkJoinWorkerThread wt = null;
        try {
            if (fac != null && (wt = fac.newThread(this)) != null) {
                wt.start();
                return true;
            }
        } catch (Throwable rex) {
            ex = rex;
        }
        deregisterWorker(wt, ex);
        return false;
    }
```

java.util.concurrent.ForkJoinPool.scan
```
    /**
     * Scans for and if found executes one or more top-level tasks from a queue.
     *
     * @return true if found an apparently non-empty queue, and
     * possibly ran task(s).
     */
    private boolean scan(WorkQueue w, int r) {
        WorkQueue[] ws; int n;
        if ((ws = workQueues) != null && (n = ws.length) > 0 && w != null) {
            for (int m = n - 1, j = r & m;;) {
                WorkQueue q; int b;
                if ((q = ws[j]) != null && q.top != (b = q.base)) {
                    int qid = q.id;
                    ForkJoinTask<?>[] a; int cap, k; ForkJoinTask<?> t;
                    if ((a = q.array) != null && (cap = a.length) > 0) {
                        t = (ForkJoinTask<?>)QA.getAcquire(a, k = (cap - 1) & b);
                        if (q.base == b++ && t != null &&
                            QA.compareAndSet(a, k, t, null)) {
                            q.base = b;
                            w.source = qid;
                            if (q.top - b > 0)
                                signalWork();
                            w.topLevelExec(t, q,  // random fairness bound
                                           r & ((n << TOP_BOUND_SHIFT) - 1));
                        }
                    }
                    return true;
                }
                else if (--n > 0)
                    j = (j + 1) & m;
                else
                    break;
            }
        }
        return false;
    }
```

java.util.concurrent.ForkJoinPool.runWorker
```

    /**
     * Top-level runloop for workers, called by ForkJoinWorkerThread.run.
     * See above for explanation.
     */
    final void runWorker(WorkQueue w) {
        int r = (w.id ^ ThreadLocalRandom.nextSecondarySeed()) | FIFO; // rng
        w.array = new ForkJoinTask<?>[INITIAL_QUEUE_CAPACITY]; // initialize
        for (;;) {
            int phase;
            if (scan(w, r)) {                     // scan until apparently empty
                r ^= r << 13; r ^= r >>> 17; r ^= r << 5; // move (xorshift)
            }
            else if ((phase = w.phase) >= 0) {    // enqueue, then rescan
                long np = (w.phase = (phase + SS_SEQ) | UNSIGNALLED) & SP_MASK;
                long c, nc;
                do {
                    w.stackPred = (int)(c = ctl);
                    nc = ((c - RC_UNIT) & UC_MASK) | np;
                } while (!CTL.weakCompareAndSet(this, c, nc));
            }
            else {                                // already queued
                int pred = w.stackPred;
                Thread.interrupted();             // clear before park
                w.source = DORMANT;               // enable signal
                long c = ctl;
                int md = mode, rc = (md & SMASK) + (int)(c >> RC_SHIFT);
                if (md < 0)                       // terminating
                    break;
                else if (rc <= 0 && (md & SHUTDOWN) != 0 &&
                         tryTerminate(false, false))
                    break;                        // quiescent shutdown
                else if (rc <= 0 && pred != 0 && phase == (int)c) {
                    long nc = (UC_MASK & (c - TC_UNIT)) | (SP_MASK & pred);
                    long d = keepAlive + System.currentTimeMillis();
                    LockSupport.parkUntil(this, d);
                    if (ctl == c &&               // drop on timeout if all idle
                        d - System.currentTimeMillis() <= TIMEOUT_SLOP &&
                        CTL.compareAndSet(this, c, nc)) {
                        w.phase = QUIET;
                        break;
                    }
                }
                else if (w.phase < 0)
                    LockSupport.park(this);       // OK if spuriously woken
                w.source = 0;                     // disable signal
            }
        }
    }
```

java.util.concurrent.ForkJoinWorkerThread.run
```
    /**
     * This method is required to be public, but should never be
     * called explicitly. It performs the main run loop to execute
     * {@link ForkJoinTask}s.
     */
    public void run() {
        if (workQueue.array == null) { // only run once
            Throwable exception = null;
            try {
                onStart();
                pool.runWorker(workQueue);
            } catch (Throwable ex) {
                exception = ex;
            } finally {
                try {
                    onTermination(exception);
                } catch (Throwable ex) {
                    if (exception == null)
                        exception = ex;
                } finally {
                    pool.deregisterWorker(this, exception);
                }
            }
        }
    }
```

## java.util.concurrent.ForkJoinWorkerThread

```
    /*
     * ForkJoinWorkerThreads are managed by ForkJoinPools and perform
     * ForkJoinTasks. For explanation, see the internal documentation
     * of class ForkJoinPool.
     *
     * This class just maintains links to its pool and WorkQueue.  The
     * pool field is set immediately upon construction, but the
     * workQueue field is not set until a call to registerWorker
     * completes. This leads to a visibility race, that is tolerated
     * by requiring that the workQueue field is only accessed by the
     * owning thread.
     *
     * Support for (non-public) subclass InnocuousForkJoinWorkerThread
     * requires that we break quite a lot of encapsulation (via helper
     * methods in ThreadLocalRandom) both here and in the subclass to
     * access and set Thread fields.
     */

    final ForkJoinPool pool;                // the pool this thread works in
    final ForkJoinPool.WorkQueue workQueue; // work-stealing mechanics
```

## Instance fields of java.util.concurrent.ForkJoinPool

```
    // Instance fields

    volatile long stealCount;            // collects worker nsteals
    final long keepAlive;                // milliseconds before dropping if idle
    int indexSeed;                       // next worker index
    final int bounds;                    // min, max threads packed as shorts
    volatile int mode;                   // parallelism, runstate, queue mode
    WorkQueue[] workQueues;              // main registry
    final String workerNamePrefix;       // for worker thread string; sync lock
    final ForkJoinWorkerThreadFactory factory;
    final UncaughtExceptionHandler ueh;  // per-worker UEH
    final Predicate<? super ForkJoinPool> saturate;

    @jdk.internal.vm.annotation.Contended("fjpctl") // segregate
    volatile long ctl;                   // main pool control
```

## java.util.concurrent.ForkJoinTask.fork

```
    /**
     * Arranges to asynchronously execute this task in the pool the
     * current task is running in, if applicable, or using the {@link
     * ForkJoinPool#commonPool()} if not {@link #inForkJoinPool}.  While
     * it is not necessarily enforced, it is a usage error to fork a
     * task more than once unless it has completed and been
     * reinitialized.  Subsequent modifications to the state of this
     * task or any data it operates on are not necessarily
     * consistently observable by any thread other than the one
     * executing it unless preceded by a call to {@link #join} or
     * related methods, or a call to {@link #isDone} returning {@code
     * true}.
     *
     * @return {@code this}, to simplify usage
     */
    public final ForkJoinTask<V> fork() {
        Thread t;
        if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread)
            ((ForkJoinWorkerThread)t).workQueue.push(this);
        else
            ForkJoinPool.common.externalPush(this);
        return this;
    }

    /**
     * Returns the result of the computation when it
     * {@linkplain #isDone is done}.
     * This method differs from {@link #get()} in that abnormal
     * completion results in {@code RuntimeException} or {@code Error},
     * not {@code ExecutionException}, and that interrupts of the
     * calling thread do <em>not</em> cause the method to abruptly
     * return by throwing {@code InterruptedException}.
     *
     * @return the computed result
     */
    public final V join() {
        int s;
        if (((s = doJoin()) & ABNORMAL) != 0)
            reportException(s);
        return getRawResult();
    }

    /**
     * Implementation for join, get, quietlyJoin. Directly handles
     * only cases of already-completed, external wait, and
     * unfork+exec.  Others are relayed to ForkJoinPool.awaitJoin.
     *
     * @return status upon completion
     */
    private int doJoin() {
        int s; Thread t; ForkJoinWorkerThread wt; ForkJoinPool.WorkQueue w;
        return (s = status) < 0 ? s :
            ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
            (w = (wt = (ForkJoinWorkerThread)t).workQueue).
            tryUnpush(this) && (s = doExec()) < 0 ? s :
            wt.pool.awaitJoin(w, this, 0L) :
            externalAwaitDone();
    }
```
(java.util.concurrent.ForkJoinTask<?>, java.util.concurrent.ForkJoinTask<?>)

```

    /**
     * Forks the given tasks, returning when {@code isDone} holds for
     * each task or an (unchecked) exception is encountered, in which
     * case the exception is rethrown. If more than one task
     * encounters an exception, then this method throws any one of
     * these exceptions. If any task encounters an exception, the
     * other may be cancelled. However, the execution status of
     * individual tasks is not guaranteed upon exceptional return. The
     * status of each task may be obtained using {@link
     * #getException()} and related methods to check if they have been
     * cancelled, completed normally or exceptionally, or left
     * unprocessed.
     *
     * @param t1 the first task
     * @param t2 the second task
     * @throws NullPointerException if any task is null
     */
    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
        int s1, s2;
        t2.fork();
        if (((s1 = t1.doInvoke()) & ABNORMAL) != 0)
            t1.reportException(s1);
        if (((s2 = t2.doJoin()) & ABNORMAL) != 0)
            t2.reportException(s2);
    }

    /**
     * Forks the given tasks, returning when {@code isDone} holds for
     * each task or an (unchecked) exception is encountered, in which
     * case the exception is rethrown. If more than one task
     * encounters an exception, then this method throws any one of
     * these exceptions. If any task encounters an exception, others
     * may be cancelled. However, the execution status of individual
     * tasks is not guaranteed upon exceptional return. The status of
     * each task may be obtained using {@link #getException()} and
     * related methods to check if they have been cancelled, completed
     * normally or exceptionally, or left unprocessed.
     *
     * @param tasks the tasks
     * @throws NullPointerException if any task is null
     */
    public static void invokeAll(ForkJoinTask<?>... tasks) {
        Throwable ex = null;
        int last = tasks.length - 1;
        for (int i = last; i >= 0; --i) {
            ForkJoinTask<?> t = tasks[i];
            if (t == null) {
                if (ex == null)
                    ex = new NullPointerException();
            }
            else if (i != 0)
                t.fork();
            else if ((t.doInvoke() & ABNORMAL) != 0 && ex == null)
                ex = t.getException();
        }
        for (int i = 1; i <= last; ++i) {
            ForkJoinTask<?> t = tasks[i];
            if (t != null) {
                if (ex != null)
                    t.cancel(false);
                else if ((t.doJoin() & ABNORMAL) != 0)
                    ex = t.getException();
            }
        }
        if (ex != null)
            rethrow(ex);
    }

    /**
     * Forks all tasks in the specified collection, returning when
     * {@code isDone} holds for each task or an (unchecked) exception
     * is encountered, in which case the exception is rethrown. If
     * more than one task encounters an exception, then this method
     * throws any one of these exceptions. If any task encounters an
     * exception, others may be cancelled. However, the execution
     * status of individual tasks is not guaranteed upon exceptional
     * return. The status of each task may be obtained using {@link
     * #getException()} and related methods to check if they have been
     * cancelled, completed normally or exceptionally, or left
     * unprocessed.
     *
     * @param tasks the collection of tasks
     * @param <T> the type of the values returned from the tasks
     * @return the tasks argument, to simplify usage
     * @throws NullPointerException if tasks or any element are null
     */
    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks) {
        if (!(tasks instanceof RandomAccess) || !(tasks instanceof List<?>)) {
            invokeAll(tasks.toArray(new ForkJoinTask<?>[0]));
            return tasks;
        }
        @SuppressWarnings("unchecked")
        List<? extends ForkJoinTask<?>> ts =
            (List<? extends ForkJoinTask<?>>) tasks;
        Throwable ex = null;
        int last = ts.size() - 1;
        for (int i = last; i >= 0; --i) {
            ForkJoinTask<?> t = ts.get(i);
            if (t == null) {
                if (ex == null)
                    ex = new NullPointerException();
            }
            else if (i != 0)
                t.fork();
            else if ((t.doInvoke() & ABNORMAL) != 0 && ex == null)
                ex = t.getException();
        }
        for (int i = 1; i <= last; ++i) {
            ForkJoinTask<?> t = ts.get(i);
            if (t != null) {
                if (ex != null)
                    t.cancel(false);
                else if ((t.doJoin() & ABNORMAL) != 0)
                    ex = t.getException();
            }
        }
        if (ex != null)
            rethrow(ex);
        return tasks;
    }
```

## 

```

    // Constructors

    /**
     * Creates a {@code ForkJoinPool} with parallelism equal to {@link
     * java.lang.Runtime#availableProcessors}, using defaults for all
     * other parameters (see {@link #ForkJoinPool(int,
     * ForkJoinWorkerThreadFactory, UncaughtExceptionHandler, boolean,
     * int, int, int, Predicate, long, TimeUnit)}).
     *
     * @throws SecurityException if a security manager exists and
     *         the caller is not permitted to modify threads
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")}
     */
    public ForkJoinPool() {
        this(Math.min(MAX_CAP, Runtime.getRuntime().availableProcessors()),
             defaultForkJoinWorkerThreadFactory, null, false,
             0, MAX_CAP, 1, null, DEFAULT_KEEPALIVE, TimeUnit.MILLISECONDS);
    }

    /**
     * Creates a {@code ForkJoinPool} with the indicated parallelism
     * level, using defaults for all other parameters (see {@link
     * #ForkJoinPool(int, ForkJoinWorkerThreadFactory,
     * UncaughtExceptionHandler, boolean, int, int, int, Predicate,
     * long, TimeUnit)}).
     *
     * @param parallelism the parallelism level
     * @throws IllegalArgumentException if parallelism less than or
     *         equal to zero, or greater than implementation limit
     * @throws SecurityException if a security manager exists and
     *         the caller is not permitted to modify threads
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")}
     */
    public ForkJoinPool(int parallelism) {
        this(parallelism, defaultForkJoinWorkerThreadFactory, null, false,
             0, MAX_CAP, 1, null, DEFAULT_KEEPALIVE, TimeUnit.MILLISECONDS);
    }

    /**
     * Creates a {@code ForkJoinPool} with the given parameters (using
     * defaults for others -- see {@link #ForkJoinPool(int,
     * ForkJoinWorkerThreadFactory, UncaughtExceptionHandler, boolean,
     * int, int, int, Predicate, long, TimeUnit)}).
     *
     * @param parallelism the parallelism level. For default value,
     * use {@link java.lang.Runtime#availableProcessors}.
     * @param factory the factory for creating new threads. For default value,
     * use {@link #defaultForkJoinWorkerThreadFactory}.
     * @param handler the handler for internal worker threads that
     * terminate due to unrecoverable errors encountered while executing
     * tasks. For default value, use {@code null}.
     * @param asyncMode if true,
     * establishes local first-in-first-out scheduling mode for forked
     * tasks that are never joined. This mode may be more appropriate
     * than default locally stack-based mode in applications in which
     * worker threads only process event-style asynchronous tasks.
     * For default value, use {@code false}.
     * @throws IllegalArgumentException if parallelism less than or
     *         equal to zero, or greater than implementation limit
     * @throws NullPointerException if the factory is null
     * @throws SecurityException if a security manager exists and
     *         the caller is not permitted to modify threads
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")}
     */
    public ForkJoinPool(int parallelism,
                        ForkJoinWorkerThreadFactory factory,
                        UncaughtExceptionHandler handler,
                        boolean asyncMode) {
        this(parallelism, factory, handler, asyncMode,
             0, MAX_CAP, 1, null, DEFAULT_KEEPALIVE, TimeUnit.MILLISECONDS);
    }

    /**
     * Creates a {@code ForkJoinPool} with the given parameters.
     *
     * @param parallelism the parallelism level. For default value,
     * use {@link java.lang.Runtime#availableProcessors}.
     *
     * @param factory the factory for creating new threads. For
     * default value, use {@link #defaultForkJoinWorkerThreadFactory}.
     *
     * @param handler the handler for internal worker threads that
     * terminate due to unrecoverable errors encountered while
     * executing tasks. For default value, use {@code null}.
     *
     * @param asyncMode if true, establishes local first-in-first-out
     * scheduling mode for forked tasks that are never joined. This
     * mode may be more appropriate than default locally stack-based
     * mode in applications in which worker threads only process
     * event-style asynchronous tasks.  For default value, use {@code
     * false}.
     *
     * @param corePoolSize the number of threads to keep in the pool
     * (unless timed out after an elapsed keep-alive). Normally (and
     * by default) this is the same value as the parallelism level,
     * but may be set to a larger value to reduce dynamic overhead if
     * tasks regularly block. Using a smaller value (for example
     * {@code 0}) has the same effect as the default.
     *
     * @param maximumPoolSize the maximum number of threads allowed.
     * When the maximum is reached, attempts to replace blocked
     * threads fail.  (However, because creation and termination of
     * different threads may overlap, and may be managed by the given
     * thread factory, this value may be transiently exceeded.)  To
     * arrange the same value as is used by default for the common
     * pool, use {@code 256} plus the {@code parallelism} level. (By
     * default, the common pool allows a maximum of 256 spare
     * threads.)  Using a value (for example {@code
     * Integer.MAX_VALUE}) larger than the implementation's total
     * thread limit has the same effect as using this limit (which is
     * the default).
     *
     * @param minimumRunnable the minimum allowed number of core
     * threads not blocked by a join or {@link ManagedBlocker}.  To
     * ensure progress, when too few unblocked threads exist and
     * unexecuted tasks may exist, new threads are constructed, up to
     * the given maximumPoolSize.  For the default value, use {@code
     * 1}, that ensures liveness.  A larger value might improve
     * throughput in the presence of blocked activities, but might
     * not, due to increased overhead.  A value of zero may be
     * acceptable when submitted tasks cannot have dependencies
     * requiring additional threads.
     *
     * @param saturate if non-null, a predicate invoked upon attempts
     * to create more than the maximum total allowed threads.  By
     * default, when a thread is about to block on a join or {@link
     * ManagedBlocker}, but cannot be replaced because the
     * maximumPoolSize would be exceeded, a {@link
     * RejectedExecutionException} is thrown.  But if this predicate
     * returns {@code true}, then no exception is thrown, so the pool
     * continues to operate with fewer than the target number of
     * runnable threads, which might not ensure progress.
     *
     * @param keepAliveTime the elapsed time since last use before
     * a thread is terminated (and then later replaced if needed).
     * For the default value, use {@code 60, TimeUnit.SECONDS}.
     *
     * @param unit the time unit for the {@code keepAliveTime} argument
     *
     * @throws IllegalArgumentException if parallelism is less than or
     *         equal to zero, or is greater than implementation limit,
     *         or if maximumPoolSize is less than parallelism,
     *         of if the keepAliveTime is less than or equal to zero.
     * @throws NullPointerException if the factory is null
     * @throws SecurityException if a security manager exists and
     *         the caller is not permitted to modify threads
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")}
     * @since 9
     */
    public ForkJoinPool(int parallelism,
                        ForkJoinWorkerThreadFactory factory,
                        UncaughtExceptionHandler handler,
                        boolean asyncMode,
                        int corePoolSize,
                        int maximumPoolSize,
                        int minimumRunnable,
                        Predicate<? super ForkJoinPool> saturate,
                        long keepAliveTime,
                        TimeUnit unit) {
        // check, encode, pack parameters
        if (parallelism <= 0 || parallelism > MAX_CAP ||
            maximumPoolSize < parallelism || keepAliveTime <= 0L)
            throw new IllegalArgumentException();
        if (factory == null)
            throw new NullPointerException();
        long ms = Math.max(unit.toMillis(keepAliveTime), TIMEOUT_SLOP);

        int corep = Math.min(Math.max(corePoolSize, parallelism), MAX_CAP);
        long c = ((((long)(-corep)       << TC_SHIFT) & TC_MASK) |
                  (((long)(-parallelism) << RC_SHIFT) & RC_MASK));
        int m = parallelism | (asyncMode ? FIFO : 0);
        int maxSpares = Math.min(maximumPoolSize, MAX_CAP) - parallelism;
        int minAvail = Math.min(Math.max(minimumRunnable, 0), MAX_CAP);
        int b = ((minAvail - parallelism) & SMASK) | (maxSpares << SWIDTH);
        int n = (parallelism > 1) ? parallelism - 1 : 1; // at least 2 slots
        n |= n >>> 1; n |= n >>> 2; n |= n >>> 4; n |= n >>> 8; n |= n >>> 16;
        n = (n + 1) << 1; // power of two, including space for submission queues

        this.workerNamePrefix = "ForkJoinPool-" + nextPoolId() + "-worker-";
        this.workQueues = new WorkQueue[n];
        this.factory = factory;
        this.ueh = handler;
        this.saturate = saturate;
        this.keepAlive = ms;
        this.bounds = b;
        this.mode = m;
        this.ctl = c;
        checkPermission();
    }


    /**
     * Returns the common pool instance. This pool is statically
     * constructed; its run state is unaffected by attempts to {@link
     * #shutdown} or {@link #shutdownNow}. However this pool and any
     * ongoing processing are automatically terminated upon program
     * {@link System#exit}.  Any program that relies on asynchronous
     * task processing to complete before program termination should
     * invoke {@code commonPool().}{@link #awaitQuiescence awaitQuiescence},
     * before exit.
     *
     * @return the common pool instance
     * @since 1.8
     */
    public static ForkJoinPool commonPool() {
        // assert common != null : "static init error";
        return common;
    }
```


## throw new RejectedExecutionException("Thread limit exceeded replacing blocked worker")

```
java.util.concurrent.ExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: Thread limit exceeded replacing blocked worker
	at java.base/java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1006)
	at huaminglin.demo.concurrency.pool.fork.ForkJoinPoolDemo.lambda$main$1(ForkJoinPoolDemo.java:35)
	at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: Thread limit exceeded replacing blocked worker
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
	at java.base/java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:600)
	... 3 more
Caused by: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: Thread limit exceeded replacing blocked worker
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
	at java.base/java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:600)
	at java.base/java.util.concurrent.ForkJoinTask.reportException(ForkJoinTask.java:678)
	at java.base/java.util.concurrent.ForkJoinTask.join(ForkJoinTask.java:722)
	at huaminglin.demo.concurrency.pool.fork.CountTaskTmp.compute(CountTaskTmp.java:39)
	at huaminglin.demo.concurrency.pool.fork.CountTaskTmp.compute(CountTaskTmp.java:5)
	at java.base/java.util.concurrent.RecursiveTask.exec(RecursiveTask.java:94)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1020)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1656)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1594)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
Caused by: java.util.concurrent.RejectedExecutionException: java.util.concurrent.RejectedExecutionException: Thread limit exceeded replacing blocked worker
	... 15 more
Caused by: java.util.concurrent.RejectedExecutionException: Thread limit exceeded replacing blocked worker
	at java.base/java.util.concurrent.ForkJoinPool.tryCompensate(ForkJoinPool.java:1575)
	at java.base/java.util.concurrent.ForkJoinPool.awaitJoin(ForkJoinPool.java:1729)
	at java.base/java.util.concurrent.ForkJoinTask.doJoin(ForkJoinTask.java:397)
	at java.base/java.util.concurrent.ForkJoinTask.join(ForkJoinTask.java:721)
	... 8 more
```

```

    /**
     * Tries to decrement counts (sometimes implicitly) and possibly
     * arrange for a compensating worker in preparation for blocking:
     * If not all core workers yet exist, creates one, else if any are
     * unreleased (possibly including caller) releases one, else if
     * fewer than the minimum allowed number of workers running,
     * checks to see that they are all active, and if so creates an
     * extra worker unless over maximum limit and policy is to
     * saturate.  Most of these steps can fail due to interference, in
     * which case 0 is returned so caller will retry. A negative
     * return value indicates that the caller doesn't need to
     * re-adjust counts when later unblocked.
     *
     * @return 1: block then adjust, -1: block without adjust, 0 : retry
     */
    private int tryCompensate(WorkQueue w) {
        int t, n, sp;
        long c = ctl;
        WorkQueue[] ws = workQueues;
        if ((t = (short)(c >>> TC_SHIFT)) >= 0) {
            if (ws == null || (n = ws.length) <= 0 || w == null)
                return 0;                        // disabled
            else if ((sp = (int)c) != 0) {       // replace or release
                WorkQueue v = ws[sp & (n - 1)];
                int wp = w.phase;
                long uc = UC_MASK & ((wp < 0) ? c + RC_UNIT : c);
                int np = sp & ~UNSIGNALLED;
                if (v != null) {
                    int vp = v.phase;
                    Thread vt = v.owner;
                    long nc = ((long)v.stackPred & SP_MASK) | uc;
                    if (vp == sp && CTL.compareAndSet(this, c, nc)) {
                        v.phase = np;
                        if (vt != null && v.source < 0)
                            LockSupport.unpark(vt);
                        return (wp < 0) ? -1 : 1;
                    }
                }
                return 0;
            }
            else if ((int)(c >> RC_SHIFT) -      // reduce parallelism
                     (short)(bounds & SMASK) > 0) {
                long nc = ((RC_MASK & (c - RC_UNIT)) | (~RC_MASK & c));
                return CTL.compareAndSet(this, c, nc) ? 1 : 0;
            }
            else {                               // validate
                int md = mode, pc = md & SMASK, tc = pc + t, bc = 0;
                boolean unstable = false;
                for (int i = 1; i < n; i += 2) {
                    WorkQueue q; Thread wt; Thread.State ts;
                    if ((q = ws[i]) != null) {
                        if (q.source == 0) {
                            unstable = true;
                            break;
                        }
                        else {
                            --tc;
                            if ((wt = q.owner) != null &&
                                ((ts = wt.getState()) == Thread.State.BLOCKED ||
                                 ts == Thread.State.WAITING))
                                ++bc;            // worker is blocking
                        }
                    }
                }
                if (unstable || tc != 0 || ctl != c)
                    return 0;                    // inconsistent
                else if (t + pc >= MAX_CAP || t >= (bounds >>> SWIDTH)) {
                    Predicate<? super ForkJoinPool> sat;
                    if ((sat = saturate) != null && sat.test(this))
                        return -1;
                    else if (bc < pc) {          // lagging
                        Thread.yield();          // for retry spins
                        return 0;
                    }
                    else
                        throw new RejectedExecutionException(
                            "Thread limit exceeded replacing blocked worker");
                }
            }
        }

        long nc = ((c + TC_UNIT) & TC_MASK) | (c & ~TC_MASK); // expand pool
        return CTL.compareAndSet(this, c, nc) && createWorker() ? 1 : 0;
    }
```

predicate<? super forkjoinpool> saturate
if we end up in a situation when there's an attempt made to spawn more threads in order to satisfy the minimumrunnable constraint, but it gets blocked by the maximumpoolsize , by default, rejectedexecutionexception("thread limit exceeded replacing blocked worker") is thrown.
but now, we can provide a predicate that gets fired once such situation occurs and, eventually, allow thread pool saturation by ignoring the minimumrunnable value.
it's good to see that we have a choice now. 


int minimumRunnable
this is the first huge improvement that gives us an opportunity to ensure that there's at least n usable threads in the pool.
usable threads are those that aren't blocked by a join() or a managedblocker instance.
when a number of free unblocked threads go below the provided value, new threads get spawned if the maximumpoolsize allows it.
setting the minimumrunnable to a larger value might ensure better throughput in the presence of blocking tasks for the cost of the increased overhead (remember to make sure that gains are bigger than costs).

Conclusion: All the threads are joining, minimumRunnable breaks. If a task takes too much time to complete, it's dangerous. If all the threads  are joining, the whole ForkJoinPool is blocked.
