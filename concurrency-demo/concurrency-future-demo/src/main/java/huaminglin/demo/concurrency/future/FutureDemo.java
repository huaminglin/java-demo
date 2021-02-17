package huaminglin.demo.concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class FutureDemo {
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
    {
      Task task = new Task(false);
      FutureTask futureTask = new FutureTask(task);

      Thread thread1 = new Thread(futureTask);
      thread1.setName("taskThread1");
      thread1.start();

      try {
        String result = (String) futureTask.get(5, TimeUnit.SECONDS);
        System.out.println(result);
        thread1.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        futureTask.cancel(true);
        e.printStackTrace();
      } catch (CancellationException e) {
        e.printStackTrace();
      }
    }
    {
      Task task = new Task(true);
      FutureTask futureTask = new FutureTask(task);

      Thread thread1 = new Thread(futureTask);
      thread1.setName("taskThread2");
      thread1.start();
      try {
        String result = (String) futureTask.get(5, TimeUnit.SECONDS);
        System.out.println(result);
        thread1.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        futureTask.cancel(true);
        e.printStackTrace();
      } catch (CancellationException e) {
        e.printStackTrace();
      }
    }
  }
}
