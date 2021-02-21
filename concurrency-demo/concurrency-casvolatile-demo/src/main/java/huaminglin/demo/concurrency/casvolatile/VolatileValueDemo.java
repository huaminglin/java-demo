package huaminglin.demo.concurrency.casvolatile;

public class VolatileValueDemo {

  static final int max = 5;
  static MyEntity normalEntity = new MyEntity();
  static volatile MyEntity volatileEntity = new MyEntity();

  static MyEntity getNormalEntity() {
    return normalEntity;
  }

  static MyEntity getVolatileEntity() {
    return volatileEntity;
  }

  public static void main(String args[]) throws InterruptedException {
    {
      Thread reader = new Thread(() -> {
        System.out.println("Reader01 run()");
        int localValue = getVolatileEntity().init_value;
        while (localValue < max) {
          if (getVolatileEntity().init_value != localValue) {
            System.out.printf("Reader01 got: [%d]\n", getVolatileEntity().init_value);
            localValue = getVolatileEntity().init_value;
          }
        }
        System.out.println("Reader01 exit()");
      }, "Reader01");
      reader.start();

      Thread updater = new Thread(() -> {
        System.out.println("Updater01 run()");
        int localValue = getVolatileEntity().init_value;
        while (localValue < max) {
          System.out.printf("Updater01: [%d]\n", ++localValue);
          getVolatileEntity().init_value = localValue;
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Updater01 exit()");
      }, "Updater01");
      updater.start();
      reader.join();
      updater.join();
    }

    System.out.println("\n\n\n\n");

    {
      Thread reader = new Thread(() -> {
        System.out.println("Reader02 run()");
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          // IO operation might cause current thread block, then the thread will read main memory when it resumes.
          // System.out.printf("Reader got: [%d]\n", getNormalEntity().init_value);
          localValue = getNormalEntity().init_value;
        }
        System.out.println("Reader02 exit()");
      }, "Reader02");
      reader.start();

      Thread updater = new Thread(() -> {
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          System.out.printf("Updater02: [%d]\n", ++localValue);
          getNormalEntity().init_value = localValue;
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Updater02 exit()");
      }, "Updater02");
      updater.start();
      reader.join();
      updater.join();
    }

    getNormalEntity().init_value = 0;
    System.out.println("\n\n\n\n"); // IO operation might cause current thread block then the above write syncs back to the main memory.
    {
      // It turns out that I have no idea why the "getNormalEntity().init_value != localValue" works differently when volatile keyword is involved.
      // If I try to change the code structure, it work differently.
      // 1) If Extract method demo(MyEntity entity), then the reader thread works well.
      Thread reader = new Thread(() -> {
        System.out.println("Reader03 run()");
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          if (getNormalEntity().init_value != localValue) {
            System.out.printf("Reader03 got: [%d]\n", getNormalEntity().init_value);
            localValue = getNormalEntity().init_value;
          }
        }
        System.out.println("Reader03 exit()");
      }, "Reader03");
      reader.start();

      Thread updater = new Thread(() -> {
        System.out.println("Updater03 run()");
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          System.out.printf("Updater03: [%d]\n", ++localValue);
          getNormalEntity().init_value = localValue;
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Updater03 exit()");
      }, "Updater03");
      updater.start();
      reader.join();
      updater.join();
    }
  }

}
