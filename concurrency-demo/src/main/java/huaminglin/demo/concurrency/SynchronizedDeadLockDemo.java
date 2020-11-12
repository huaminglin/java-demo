package huaminglin.demo.concurrency;

public class SynchronizedDeadLockDemo {
  static final Object synchronizedA = new Object();
  static final Object synchronizedB = new Object();

  static class SynchronizedAThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("SynchronizedAThread.run()");
        synchronized(synchronizedA) {
          Thread.sleep(6 *  1000);
          synchronized (synchronizedB) {
            System.out.println("SynchronizedAThread.run() synchronizedB");
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("SynchronizedAThread.run() exit");
    }
  }


  static class SynchronizedBThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("SynchronizedBThread.run()");
        synchronized(synchronizedB) {
          Thread.sleep(6 *  1000);
          synchronized (synchronizedA) {
            System.out.println("SynchronizedBThread.run() synchronizedA");
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("SynchronizedAThread.run() exit");
    }
  }


  public static void main(String[] args) {
    {
      Thread thread = new SynchronizedAThread();
      thread.setName("SynchronizedAThreadName01");
      thread.start();
    }
    {
      Thread thread = new SynchronizedBThread();
      thread.setName("SynchronizedBThreadName01");
      thread.start();
    }

    try {
      Thread.sleep(60 * 60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
