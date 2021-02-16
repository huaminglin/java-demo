package huaminglin.demo.concurrency.casatomic;

import java.util.concurrent.atomic.AtomicLong;

public final class CasAtomicDemo {

  public static void main(String[] args) {
    AtomicLong atomicLong = new AtomicLong(1);
    {
      new Thread(() -> {
        final boolean b = atomicLong.compareAndSet(1, 2);
        System.out.println(b);
      }).start();
    }
    {
      new Thread(() -> {
        final boolean b = atomicLong.compareAndSet(1, 2);
        System.out.println(b);
      }).start();
    }
  }
}
