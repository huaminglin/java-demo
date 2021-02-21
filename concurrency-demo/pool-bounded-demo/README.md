#

## java.util.concurrent.ThreadPoolExecutor.defaultHandler

```
    /**
     * The default rejected execution handler.
     */
    private static final RejectedExecutionHandler defaultHandler =
        new AbortPolicy();
```

java.util.concurrent.ThreadPoolExecutor.AbortPolicy
```
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@1218025c[Not completed, task = huaminglin.demo.concurrency.pool.bounded.BoundedDemo$Task@548c4f57] rejected from java.util.concurrent.ThreadPoolExecutor@816f27d[Running, pool size = 2, active threads = 2, queued tasks = 2, completed tasks = 0]
	at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2055)
	at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:825)
	at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1355)
	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:140)
	at huaminglin.demo.concurrency.pool.bounded.BoundedDemo.main(BoundedDemo.java:65)
```

## java.util.concurrent.ThreadPoolExecutor.DiscardPolicy

```
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task);
        execute(ftask);
        return ftask;
    }
```

DiscardPolicy doesn't work well with FutureTask. The RunnableFuture never runs, then RunnableFuture.get() blocks forever.

## java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy

```
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        }
```

Similar problem as DiscardPolicy. The oldest RunnableFuture never runs.

 ## java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy

 ```
demo-thread--1: Normal result
demo-thread--1: Normal result
demo-thread--2: Normal result
demo-thread--2: Normal result
main: Normal result
demo-thread--1: Normal result
main() exit
```
