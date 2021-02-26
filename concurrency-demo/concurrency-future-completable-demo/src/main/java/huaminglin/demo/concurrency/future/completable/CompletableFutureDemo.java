package huaminglin.demo.concurrency.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public final class CompletableFutureDemo {

  private static Double asyncTask01() {
    System.out.println("asyncTask01()");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("asyncTask01() exit");
    return 1.0;
  }

  private static Double asyncTask02(double previous) {
    System.out.println("asyncTask02()");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("asyncTask02() exit");
    return previous + 2.0;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo::asyncTask01);
    final CompletableFuture<Double> async = cf
        .thenApplyAsync(CompletableFutureDemo::asyncTask02);
    async.thenAccept((result) -> {
      System.out.println("Result: " + result);
    });
    async.exceptionally((e) -> {
      e.printStackTrace();
      return null;
    });
    final Double aDouble = async.get();
    System.out.println(aDouble);
  }
}
