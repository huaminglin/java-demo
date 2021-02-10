package huaminglin.demo.concurrency.latch;

import java.util.concurrent.CountDownLatch;

public final class CountDownLatchDemo {

  public static void main(String[] args) {
    int count = 3;
    CountDownLatch latch = new CountDownLatch(count);
    for (int i = 0; i < count; i++){
      final int j = i;
      new Thread(() -> {
        System.out.println("Thread " + j + " run()");
        try {
          latch.countDown();
          latch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread " + j + " exit()");
      }).start();
    }
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("main() exit");
  }
}
