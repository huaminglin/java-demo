# CompletableFutureDemo

## java.util.concurrent.CompletableFuture

```
    volatile Object result;       // Either the result or boxed AltResult
    volatile Completion stack;    // Top of Treiber stack of dependent actions


    // VarHandle mechanics
    private static final VarHandle RESULT;
    private static final VarHandle STACK;
    private static final VarHandle NEXT;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            RESULT = l.findVarHandle(CompletableFuture.class, "result", Object.class);
            STACK = l.findVarHandle(CompletableFuture.class, "stack", Completion.class);
            NEXT = l.findVarHandle(Completion.class, "next", Completion.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }

        // Reduce the risk of rare disastrous classloading in first call to
        // LockSupport.park: https://bugs.openjdk.java.net/browse/JDK-8074773
        Class<?> ensureLoaded = LockSupport.class;
    }
```

## java.util.concurrent.CompletableFuture.supplyAsync(java.util.function.Supplier<U>)

```
    /**
     * Returns a new CompletableFuture that is asynchronously completed
     * by a task running in the {@link ForkJoinPool#commonPool()} with
     * the value obtained by calling the given Supplier.
     *
     * @param supplier a function returning the value to be used
     * to complete the returned CompletableFuture
     * @param <U> the function's return type
     * @return the new CompletableFuture
     */
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return asyncSupplyStage(ASYNC_POOL, supplier);
    }

    static <U> CompletableFuture<U> asyncSupplyStage(Executor e,
                                                     Supplier<U> f) {
        if (f == null) throw new NullPointerException();
        CompletableFuture<U> d = new CompletableFuture<U>();
        e.execute(new AsyncSupply<U>(d, f));
        return d;
    }

```

## java.util.concurrent.CompletableFuture.AsyncSupply

```

    static final class AsyncSupply<T> extends ForkJoinTask<Void>
        implements Runnable, AsynchronousCompletionTask {
        CompletableFuture<T> dep; Supplier<? extends T> fn;
        AsyncSupply(CompletableFuture<T> dep, Supplier<? extends T> fn) {
            this.dep = dep; this.fn = fn;
        }

        public final Void getRawResult() { return null; }
        public final void setRawResult(Void v) {}
        public final boolean exec() { run(); return false; }

        public void run() {
            CompletableFuture<T> d; Supplier<? extends T> f;
            if ((d = dep) != null && (f = fn) != null) {
                dep = null; fn = null;
                if (d.result == null) {
                    try {
                        d.completeValue(f.get());
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                d.postComplete();
            }
        }
    }
```

## java.util.concurrent.CompletableFuture.thenApplyAsync(java.util.function.Function<? super T,? extends U>)

```
    public <U> CompletableFuture<U> thenApplyAsync(
        Function<? super T,? extends U> fn) {
        return uniApplyStage(defaultExecutor(), fn);
    }

    private <V> CompletableFuture<V> uniApplyStage(
        Executor e, Function<? super T,? extends V> f) {
        if (f == null) throw new NullPointerException();
        Object r;
        if ((r = result) != null)
            return uniApplyNow(r, e, f);
        CompletableFuture<V> d = newIncompleteFuture();
        unipush(new UniApply<T,V>(e, d, this, f));
        return d;
```

## java.util.concurrent.CompletableFuture.UniApply

```
    static final class UniApply<T,V> extends UniCompletion<T,V> {
        Function<? super T,? extends V> fn;
        UniApply(Executor executor, CompletableFuture<V> dep,
                 CompletableFuture<T> src,
                 Function<? super T,? extends V> fn) {
            super(executor, dep, src); this.fn = fn;
        }
        final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d; CompletableFuture<T> a;
            Object r; Throwable x; Function<? super T,? extends V> f;
            if ((d = dep) == null || (f = fn) == null
                || (a = src) == null || (r = a.result) == null)
                return null;
            tryComplete: if (d.result == null) {
                if (r instanceof AltResult) {
                    if ((x = ((AltResult)r).ex) != null) {
                        d.completeThrowable(x, r);
                        break tryComplete;
                    }
                    r = null;
                }
                try {
                    if (mode <= 0 && !claim())
                        return null;
                    else {
                        @SuppressWarnings("unchecked") T t = (T) r;
                        d.completeValue(f.apply(t));
                    }
                } catch (Throwable ex) {
                    d.completeThrowable(ex);
                }
            }
            dep = null; src = null; fn = null;
            return d.postFire(a, mode);
        }
    }
```

## java.util.concurrent.CompletableFuture.thenAccept

```
    public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
        return uniAcceptStage(null, action);
    }

    private CompletableFuture<Void> uniAcceptStage(Executor e,
                                                   Consumer<? super T> f) {
        if (f == null) throw new NullPointerException();
        Object r;
        if ((r = result) != null)
            return uniAcceptNow(r, e, f);
        CompletableFuture<Void> d = newIncompleteFuture();
        unipush(new UniAccept<T>(e, d, this, f));
        return d;
    }
```

## java.util.concurrent.CompletableFuture.exceptionally

```
    /**
     * Returns a new CompletableFuture that is completed when this
     * CompletableFuture completes, with the result of the given
     * function of the exception triggering this CompletableFuture's
     * completion when it completes exceptionally; otherwise, if this
     * CompletableFuture completes normally, then the returned
     * CompletableFuture also completes normally with the same value.
     * Note: More flexible versions of this functionality are
     * available using methods {@code whenComplete} and {@code handle}.
     *
     * @param fn the function to use to compute the value of the
     * returned CompletableFuture if this CompletableFuture completed
     * exceptionally
     * @return the new CompletableFuture
     */
    public CompletableFuture<T> exceptionally(
        Function<Throwable, ? extends T> fn) {
        return uniExceptionallyStage(fn);
    }
```

## java.util.concurrent.CompletableFuture.get()

```
    /**
     * Waits if necessary for this future to complete, and then
     * returns its result.
     *
     * @return the result value
     * @throws CancellationException if this future was cancelled
     * @throws ExecutionException if this future completed exceptionally
     * @throws InterruptedException if the current thread was interrupted
     * while waiting
     */
    @SuppressWarnings("unchecked")
    public T get() throws InterruptedException, ExecutionException {
        Object r;
        if ((r = result) == null)
            r = waitingGet(true);
        return (T) reportGet(r);
    }


    /**
     * Returns raw result after waiting, or null if interruptible and
     * interrupted.
     */
    private Object waitingGet(boolean interruptible) {
        Signaller q = null;
        boolean queued = false;
        Object r;
        while ((r = result) == null) {
            if (q == null) {
                q = new Signaller(interruptible, 0L, 0L);
                if (Thread.currentThread() instanceof ForkJoinWorkerThread)
                    ForkJoinPool.helpAsyncBlocker(defaultExecutor(), q);
            }
            else if (!queued)
                queued = tryPushStack(q);
            else {
                try {
                    ForkJoinPool.managedBlock(q);
                } catch (InterruptedException ie) { // currently cannot happen
                    q.interrupted = true;
                }
                if (q.interrupted && interruptible)
                    break;
            }
        }
        if (q != null && queued) {
            q.thread = null;
            if (!interruptible && q.interrupted)
                Thread.currentThread().interrupt();
            if (r == null)
                cleanStack();
        }
        if (r != null || (r = result) != null)
            postComplete();
        return r;
    }

    /**
     * Reports result using Future.get conventions.
     */
    private static Object reportGet(Object r)
        throws InterruptedException, ExecutionException {
        if (r == null) // by convention below, null means interrupted
            throw new InterruptedException();
        if (r instanceof AltResult) {
            Throwable x, cause;
            if ((x = ((AltResult)r).ex) == null)
                return null;
            if (x instanceof CancellationException)
                throw (CancellationException)x;
            if ((x instanceof CompletionException) &&
                (cause = x.getCause()) != null)
                x = cause;
            throw new ExecutionException(x);
        }
        return r;
    }
```


## java.util.concurrent.CompletableFuture.UniCompletion

```
    /** A Completion with a source, dependent, and executor. */
    @SuppressWarnings("serial")
    abstract static class UniCompletion<T,V> extends Completion {
        Executor executor;                 // executor to use (null if none)
        CompletableFuture<V> dep;          // the dependent to complete
        CompletableFuture<T> src;          // source for action

        UniCompletion(Executor executor, CompletableFuture<V> dep,
                      CompletableFuture<T> src) {
            this.executor = executor; this.dep = dep; this.src = src;
        }

        /**
         * Returns true if action can be run. Call only when known to
         * be triggerable. Uses FJ tag bit to ensure that only one
         * thread claims ownership.  If async, starts as task -- a
         * later call to tryFire will run action.
         */
        final boolean claim() {
            Executor e = executor;
            if (compareAndSetForkJoinTaskTag((short)0, (short)1)) {
                if (e == null)
                    return true;
                executor = null; // disable
                e.execute(this);
            }
            return false;
        }

        final boolean isLive() { return dep != null; }
    }
```

java.util.concurrent.ForkJoinTask.compareAndSetForkJoinTaskTag

```
    /**
     * Atomically conditionally sets the tag value for this task.
     * Among other applications, tags can be used as visit markers
     * in tasks operating on graphs, as in methods that check: {@code
     * if (task.compareAndSetForkJoinTaskTag((short)0, (short)1))}
     * before processing, otherwise exiting because the node has
     * already been visited.
     *
     * @param expect the expected tag value
     * @param update the new tag value
     * @return {@code true} if successful; i.e., the current value was
     * equal to {@code expect} and was changed to {@code update}.
     * @since 1.8
     */
    public final boolean compareAndSetForkJoinTaskTag(short expect, short update) {
        for (int s;;) {
            if ((short)(s = status) != expect)
                return false;
            if (STATUS.weakCompareAndSet(this, s,
                                         (s & ~SMASK) | (update & SMASK)))
                return true;
        }
    }
```
