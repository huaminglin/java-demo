package huaminglin.demo.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class LockDeadLockDemo {
  static final Lock lockA = new ReentrantLock();
  static final Lock lockB = new ReentrantLock();

  static class LockAThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("LockAThread.run()");
        lockA.lock();
        Thread.sleep(6 *  1000);
        lockB.lock();
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
        lockB.lock();
        Thread.sleep(6 *  1000);
        lockA.lock();
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
