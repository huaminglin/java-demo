package huaminglin.demo.concurrency.pool.fixed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class FixedCachedDemo {
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
      return "Normal result";
    }
  }
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<>();
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(false)));
    futures.add(executorService.submit(new Task(true)));
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
  }
}
