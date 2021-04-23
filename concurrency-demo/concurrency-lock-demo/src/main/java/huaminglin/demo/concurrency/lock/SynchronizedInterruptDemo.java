package huaminglin.demo.concurrency.lock;

public final class SynchronizedInterruptDemo {
  static final Object synchronizedA = new Object();

  static class SynchronizedAThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("SynchronizedAThread.run()");
        synchronized(synchronizedA) {
          Thread.sleep(6 *  1000);
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
      System.out.println("SynchronizedBThread.run()");
        synchronized (synchronizedA) {
        System.out.println("SynchronizedBThread.run() synchronizedA");
      }
      System.out.println("SynchronizedAThread.run() exit");
    }
  }


  public static void main(String[] args) throws InterruptedException {
    {
      Thread thread = new SynchronizedAThread();
      thread.setName("SynchronizedAThreadName01");
      thread.start();
    }
    {
      Thread.sleep(1000);
    }
    {
      Thread thread = new SynchronizedBThread();
      thread.setName("SynchronizedBThreadName01");
      thread.start();
      Thread.sleep(500);
      thread.interrupt();
    }

    try {
      Thread.sleep(60 * 60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
