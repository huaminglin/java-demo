package huaminglin.demo.concurrency.stamped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public final class StampedLockDemo {

  public static void main(String[] args) throws InterruptedException {
    List<Thread> threads = new ArrayList<>();
    StampedLock stampedLock = new StampedLock();
    {
      Thread thread = new Thread(() -> {
        System.out.println("Thread tryOptimisticRead run()");
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println("Thread tryOptimisticRead tryOptimisticRead(): " + stamp);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (!stampedLock.validate(stamp)) {
          System.out.println("tryOptimisticRead fails to validate when there is a write operation finished");
        }
        System.out.println("Thread tryOptimisticRead exit()");
      });
      threads.add(thread);
      thread.start();
    }
    {
      Thread thread = new Thread(() -> {
        System.out.println("Thread writeLock run()");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        long stamp = stampedLock.writeLock();
        System.out.println("Thread writeLock writeLock(): " + stamp);
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          stampedLock.unlockWrite(stamp);
        }
        System.out.println("Thread writeLock exit()");
      });
      threads.add(thread);
      thread.start();
    }

    {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread tryOptimisticRead 02 run()");
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println("Thread tryOptimisticRead 02 tryOptimisticRead(): " + stamp);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (!stampedLock.validate(stamp)) {
          System.out.println("Thread tryOptimisticRead 02 tryOptimisticRead fails to validate when there is a write operation ongoing");
        }
        System.out.println("Thread tryOptimisticRead 02 exit()");
      });
      threads.add(thread);
      thread.start();
    }
    {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(2100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread writeLock 02 run()");
        long stamp = stampedLock.writeLock();
        System.out.println("Thread 02 writeLock writeLock(): " + stamp);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          stampedLock.unlockWrite(stamp);
        }
        System.out.println("Thread 02 writeLock exit()");
      });
      threads.add(thread);
      thread.start();
    }

    {
      Thread thread = new Thread(() -> {
        long stamp = stampedLock.tryOptimisticRead();
        try {
          Thread.sleep(3900);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread writeLock 03 run()");
        long wstamp = stampedLock.writeLock();
        System.out.println("Thread 03 writeLock writeLock(): " + wstamp);
        if (wstamp != stamp) {
          System.out.println("Thread 03 writeLock stamp changed.");
        }
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          stampedLock.unlockWrite(wstamp);
        }
        System.out.println("Thread 03 writeLock exit()");
      });
      threads.add(thread);
      thread.start();
    }
    {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread tryOptimisticRead 03 run()");
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println("Thread tryOptimisticRead 03 tryOptimisticRead(): " + stamp);
        if (stamp <= 0) {
          System.out.println("Thread tryOptimisticRead 03 tryOptimisticRead fails to call tryOptimisticRead().");
        }
        System.out.println("Thread tryOptimisticRead 03 exit()");
      });
      threads.add(thread);
      thread.start();
    }


    {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread 0401 writeLock  run()");
        long stamp = stampedLock.writeLock();
        System.out.println("Thread 0401 writeLock writeLock(): " + stamp);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          stampedLock.unlockWrite(stamp);
        }
        System.out.println("Thread 0401 writeLock exit()");
      });
      threads.add(thread);
      thread.start();
    }
    {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread 0402 writeLock  run()");
        long stamp = stampedLock.writeLock();
        System.out.println("Thread 0402 writeLock writeLock(): " + stamp);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          stampedLock.unlockWrite(stamp);
        }
        System.out.println("Thread 0402 writeLock exit()");
      });
      threads.add(thread);
      thread.start();
    }

    for(Thread thread : threads) {
      thread.join();
    }
    System.out.println("main() exit.");
  }
}
