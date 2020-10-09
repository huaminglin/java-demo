package huaminglin.demo.jdk.concurrency;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import sun.misc.Unsafe;

public class UnsafeParkDemo {

  private static Unsafe getUnsafe() throws Exception {
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    return (Unsafe) f.get(null);
  }

  public static void main(String[] args) throws Exception {
    Unsafe unsafe = getUnsafe();
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread park!");
        unsafe.park(false, 0);
        System.out.println("Thread  resume");
      }
    });
    thread.start();
    TimeUnit.SECONDS.sleep(2);
    System.out.println("Main thread unpark");
    unsafe.unpark(thread);
    TimeUnit.SECONDS.sleep(2);
  }
}
