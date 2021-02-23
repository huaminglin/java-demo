package huaminglin.demo.jvm.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public final class JolDemo {
  static volatile Object consumer;

  private static void jolApi() {
    System.out.println(VM.current().details());

    System.out.println("\nhuaminglin.demo.jvm.jol.SimpleInt");
    System.out.println(ClassLayout.parseClass(SimpleInt.class).toPrintable());

    System.out.println("\nhuaminglin.demo.jvm.jol.SimpleInt");
    SimpleInt instance = new SimpleInt();
    System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    System.out.println("\nObject address: " + VM.current().addressOf(instance));

//    System.out.println("\n@Contended");
//    System.out.println(ClassLayout.parseClass(Isolated.class).toPrintable());

    System.out.println("\nboolean[]");
    boolean[] booleans = new boolean[3];
    System.out.println(ClassLayout.parseInstance(booleans).toPrintable());
  }

  private static void identityHashCode() {
    System.out.println("\nidentityHashCode()");
    Object instance = new Object();
    System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    System.out.println("\nThe identity hash code is " + System.identityHashCode(instance));
    System.out.println(ClassLayout.parseInstance(instance).toPrintable());
  }

  private static void jolAndGc(Object instance) {
    System.out.println("\nGc");
    long lastAddr = VM.current().addressOf(instance);
    ClassLayout layout = ClassLayout.parseInstance(instance);
    System.out.println(layout.toPrintable());
    System.out.println(System.identityHashCode(instance));
    System.out.println(layout.toPrintable());

    int count = 0;
    for (int i = 0; i < 10_000; i++) {
      long currentAddr = VM.current().addressOf(instance);
      if (currentAddr != lastAddr) {
        count++;
        System.out.println("Address changed: " + count);
        System.out.println(layout.toPrintable());
      }

      for (int j = 0; j < 10_000; j++) {
        consumer = new Object();
      }

      lastAddr = currentAddr;
    }
  }

  private static void biasedLock(Object lock) {
    System.out.println("\nsynchronized (lock) biased:");
    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    synchronized (lock) {
      System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
  }

  private static void lightLock(Object lock) {
    System.out.println("\nsynchronized (lock) light:");
    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    final Thread thread1 = new Thread(() -> {
      synchronized (lock) {
        System.out.println(Thread.currentThread().getName() + " in");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread1.setName("Thread01");
    final Thread thread2 = new Thread(() -> {
      synchronized (lock) {
        System.out.println(Thread.currentThread().getName() + " in");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread2.setName("Thread02");
    try {
      // Run thread1, thread1 exit, then run thread2, thread2 exit.
      // In this way, light lock is involved.
      thread1.start();
      thread1.join();
      thread2.start();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("synchronized() exit");
    System.out.println(ClassLayout.parseInstance(lock).toPrintable());

    synchronized (lock) {
      System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
  }


  private static void heavyLock(Object lock) {
    System.out.println("\nsynchronized (lock) heavy:");
    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    final Thread thread1 = new Thread(() -> {
      synchronized (lock) {
        System.out.println(Thread.currentThread().getName() + " in");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread1.setName("Thread01");
    final Thread thread2 = new Thread(() -> {
      synchronized (lock) {
        System.out.println(Thread.currentThread().getName() + " in");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread2.setName("Thread02");
    try {
      // Run the two threads at same time
      // One thread acquires lock successfully, another thread will spin seveval times then switch to heavy lock (park the thread).
      thread1.start();
      thread2.start();
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Object instance = new Object();
//    jolApi();
//    identityHashCode();
//    jolAndGc(instance);
//    biasedLock(instance);
//    lightLock(instance);
    heavyLock(instance);
  }
}
