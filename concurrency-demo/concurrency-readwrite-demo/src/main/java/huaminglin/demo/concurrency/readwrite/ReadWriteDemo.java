package huaminglin.demo.concurrency.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class ReadWriteDemo {

  public static void main(String[] args) throws Exception {
    int count = 2;
    final ReadWriteLock lock = new ReentrantReadWriteLock();
    for (int i = 0; i < count; i++){
      final int j = i;
      new Thread(() -> {
        System.out.println("Read Thread " + j + " run()");
        lock.readLock().lock();
        System.out.println("Read Thread " + j + " lock()");
        try {
          Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.readLock().unlock();
        }
        System.out.println("Read Thread " + j + " exit()");
      }).start();
    }
    try {
      Thread.currentThread().sleep(500);
      lock.writeLock().lock();
      System.out.println("main() lock()");

      new Thread(() -> {
        System.out.println("Last Write Thread run()");
        lock.writeLock().lock();
        System.out.println("Last Write Thread lock()");
        lock.writeLock().unlock();
        System.out.println("Last Write Thread exit()");
      }).start();

      new Thread(() -> {
        System.out.println("Last Read Thread run()");
        try {
          Thread.currentThread().sleep(100);
          lock.readLock().lock();
          System.out.println("Last Read Thread lock()");
          Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.readLock().unlock();
        }
        System.out.println("Last Read Thread exit()");
      }).start();

      new Thread(() -> {
        System.out.println("Last Read 02 Thread run()");
        try {
          Thread.currentThread().sleep(100);
          lock.readLock().lock();
          System.out.println("Last Read 02 Thread lock()");
          Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.readLock().unlock();
        }
        System.out.println("Last Read 02 Thread exit()");
      }).start();

      new Thread(() -> {
        System.out.println("Last Write 02 Thread run()");
        try {
          Thread.currentThread().sleep(200);
          lock.writeLock().lock();
          System.out.println("Last Write 02 Thread lock()");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.writeLock().unlock();
        }
        System.out.println("Last Write 02 Thread exit()");
      }).start();

      Thread.currentThread().sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.writeLock().unlock();
    }
    Thread.currentThread().sleep(3000);
    System.out.println("main() exit");

  }
}
