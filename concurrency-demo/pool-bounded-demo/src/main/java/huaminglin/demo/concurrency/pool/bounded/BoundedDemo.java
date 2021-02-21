package huaminglin.demo.concurrency.pool.bounded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class BoundedDemo {
  static class ThreadFactoryImpl implements ThreadFactory {

    private final String namePrefix;
    private final AtomicLong count;

    ThreadFactoryImpl(final String namePrefix) {
      this.namePrefix = namePrefix;
      this.count = new AtomicLong();
    }

    @Override
    public Thread newThread(final Runnable target) {
      return new Thread(target, this.namePrefix + '-' + this.count.incrementAndGet());
    }

  }

  static class Task implements Callable<String> {
    private final boolean exception;

    Task(boolean exception) {
      this.exception = exception;
    }

    @Override
    public String call() {
      try {
        for (int i = 0; i < 10; i++) {
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        return "Interrupted result";
      }
      if (exception) {
        throw new RuntimeException("call() exception");
      }
      return Thread.currentThread().getName() + ": Normal result";
    }
  }
  public static void main(String[] args) {
//    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
//    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
//    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
    RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 1,
        TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(2), new ThreadFactoryImpl("demo-thread-"), handler);
    List<Future<String>> futures = new ArrayList<>();
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    for (Future<String> future : futures) {
      try {
        String s = future.get();
        System.out.println(s);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    executorService.shutdown();
    System.out.println("main() exit");
  }
}
