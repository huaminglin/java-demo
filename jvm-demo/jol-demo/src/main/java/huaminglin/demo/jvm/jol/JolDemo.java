package huaminglin.demo.jvm.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public final class JolDemo {
  static volatile Object consumer;

  private static void jolAndGc() {
    Object instance = new Object();
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

  public static void main(String[] args) {
    System.out.println(VM.current().details());

    System.out.println("\nhuaminglin.demo.jvm.jol.SimpleInt");
    System.out.println(ClassLayout.parseClass(SimpleInt.class).toPrintable());

    System.out.println("\nhuaminglin.demo.jvm.jol.SimpleInt");
    SimpleInt instance = new SimpleInt();
    System.out.println(ClassLayout.parseInstance(instance).toPrintable());

    System.out.println("\nThe identity hash code is " + System.identityHashCode(instance));
    System.out.println(ClassLayout.parseInstance(instance).toPrintable());

    System.out.println("\nsynchronized (lock):");
    Object lock = new SimpleInt();
    synchronized (lock) {
      System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
    System.out.println("\nObject address: " + VM.current().addressOf(lock));


    System.out.println("\nhuaminglin.demo.jvm.jol.Isolated");
    System.out.println(ClassLayout.parseClass(Isolated.class).toPrintable());

    System.out.println("\nboolean[]");
    boolean[] booleans = new boolean[3];
    System.out.println(ClassLayout.parseInstance(booleans).toPrintable());

    System.out.println("\nGc");
    jolAndGc();
  }
}
