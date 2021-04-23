package huaminglin.demo.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class LockInterruptDemo {
  static final Lock lockA = new ReentrantLock();

  static class LockAThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("LockAThread.run()");
        lockA.lock();
        System.out.println("LockAThread.run() lockA");
        Thread.sleep(6 *  1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("LockAThread.run() exit");
    }
  }


  static class LockBThread extends Thread {

    @Override
    public void run() {
      System.out.println("LockBThread.run()");
      try {
        lockA.lockInterruptibly();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("LockBThread.run() LockA");
      System.out.println("LockBThread.run() exit");
    }
  }


  public static void main(String[] args) throws InterruptedException {
    {
      Thread thread = new LockAThread();
      thread.setName("LockAThreadName01");
      thread.start();
    }
    Thread.sleep(500);
    {
      Thread thread = new LockBThread();
      thread.setName("LockBThreadName01");
      thread.start();
      Thread.sleep(500);
      thread.interrupt();
    }
    Thread.sleep(60 * 60 * 1000);
  }
}
