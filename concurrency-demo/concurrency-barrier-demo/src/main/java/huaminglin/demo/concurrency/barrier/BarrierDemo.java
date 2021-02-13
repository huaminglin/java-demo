package huaminglin.demo.concurrency.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class BarrierDemo {

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    int count = 3;
    CyclicBarrier barrier = new CyclicBarrier(count);
    for (int i = 0; i < count; i++){
      final int j = i;
      new Thread(() -> {
        System.out.println("Thread " + j + " run()");
        try {
          Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          try {
            barrier.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (BrokenBarrierException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Thread " + j + " exit()");
      }).start();
    }
    try {
      Thread.currentThread().sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      barrier.await();
    }
    System.out.println("main() exit");
  }
}
