package huaminglin.demo.concurrency.pool.scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class ScheduledDemo {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
    List<ScheduledFuture> results = new ArrayList<>();
    {
      final ScheduledFuture<?> future = service.schedule(() -> {
        System.out.println("Delayed 6 run()");
        return 6;
      }, 6, TimeUnit.SECONDS);
      results.add(future);
    }
    {
      final ScheduledFuture<?> future = service.schedule(() -> {
        System.out.println("Delayed 3 run()");
        return 3;
      }, 3, TimeUnit.SECONDS);
      results.add(future);
    }
    {
      final ScheduledFuture<?> future = service.schedule(() -> {
        System.out.println("Delayed 8 run()");
        return 8;
      }, 8, TimeUnit.SECONDS);
      results.add(future);
    }
    for (ScheduledFuture future: results) {
      final Object result = future.get();
      System.out.println(result);
    }
    service.shutdown();
  }
}
