package huaminglin.demo.concurrency.pool.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public final class ForkJoinPoolDemo {

  public static void main(String[] args) throws InterruptedException {
    ForkJoinPool forkJoinPool = new ForkJoinPool(2,
    new SafeForkJoinWorkerThreadFactory(),
    null,
    false,
    2,
    4,
    2,
    null,
    60,
    TimeUnit.SECONDS);

    Thread thread1 = new Thread(() -> {
      CountTaskTmp task = new CountTaskTmp("first", 1, 4);
      Integer r = forkJoinPool.invoke(task);
      System.out.println("First Result: " + r);
    });
    thread1.setName("thread1");
    thread1.start();

    Thread thread2 = new Thread(() -> {
      CountTaskTmp task = new CountTaskTmp("second", 11, 14);
      final ForkJoinTask<Integer> task1 = forkJoinPool.submit(task);
      int r = 0;
      try {
        r = task1.get();
      } catch (InterruptedException e) {
        System.out.println("Fail to call get()");
        e.printStackTrace();
      } catch (ExecutionException e) {
        System.out.println("Fail to call get()");
        e.printStackTrace();
      }
      System.out.println("Second Result: " + r);
    });
    thread2.setName("thread2");
    thread2.start();

    thread1.join();
    thread2.join();
  }
}
