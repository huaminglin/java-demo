package huaminglin.demo.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public final class SemaphoreDemo {

  public static void main(String[] args) {
    int count = 3;
    Semaphore semaphore = new Semaphore(count);
    for (int i = 0; i < count; i++){
      final int j = i;
      new Thread(() -> {
        System.out.println("Thread " + j + " run()");
        try {
          semaphore.acquire();
          Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          semaphore.release();
        }
        System.out.println("Thread " + j + " exit()");
      }).start();
    }
    try {
      Thread.currentThread().sleep(100);
      semaphore.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
    System.out.println("main() exit");
  }
}
