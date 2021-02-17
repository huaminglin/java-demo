package huaminglin.demo.concurrency.pool.cached;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public final class PoolCachedDemo {
  static class Task implements Callable<String> {

    @Override
    public String call() {
      try {
        for (int i = 0; i < 10; i++) {
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        return "Interrupted result";
      }
      return "Normal result";
    }
  }

  public static void submit(ExecutorService executorService, Callable<String> task) {
    final Future<String> future = executorService.submit(task);
    try {
      String result = future.get();
      System.out.println(result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void execute(ExecutorService executorService, Runnable command) {
    executorService.execute(command);
    if (command instanceof Future) {
      try {
        Future<String> future = (Future<String>) command;
        String result = future.get();
        System.out.println(result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    Task task = new Task();// task is stateless. It ca be reused.
    ExecutorService executorService = Executors.newCachedThreadPool();

    submit(executorService, task);

    FutureTask futureTask = new FutureTask(task);
    // The first task is executed as firstTask in "boolean addWorker(Runnable firstTask, boolean core)".
    execute(executorService, futureTask);

    // The second task is executed through java.util.concurrent.SynchronousQueue.TransferStack.
    executorService.shutdown();
  }
}
