package huaminglin.demo.concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadLockDeadLockDemo {
  static final ReadWriteLock lockA = new ReentrantReadWriteLock();
  static final ReadWriteLock lockB = new ReentrantReadWriteLock();

  static class LockAThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("LockAThread.run()");
        lockA.readLock().lock();
        Thread.sleep(6 *  1000);
        lockB.writeLock().lock();
        System.out.println("LockAThread.run() lockB");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("LockAThread.run() exit");
    }
  }


  static class LockBThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("LockBThread.run()");
        lockB.readLock().lock();
        Thread.sleep(6 *  1000);
        lockA.writeLock().lock();
        System.out.println("LockBThread.run() LockA");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("LockBThread.run() exit");
    }
  }


  public static void main(String[] args) {
    {
      Thread thread = new LockAThread();
      thread.setName("LockAThreadName01");
      thread.start();
    }
    {
      Thread thread = new LockBThread();
      thread.setName("LockBThreadName01");
      thread.start();
    }

    try {
      Thread.sleep(60 * 60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
