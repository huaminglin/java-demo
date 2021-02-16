package huaminglin.demo.concurrency.casvolatile;

public class VolatileValueDemo {

  final static int max = 5;
  static MyEntity normalEntity = new MyEntity();
  static volatile MyEntity volatileEntity = new MyEntity();

  static MyEntity getNormalEntity() {
    return normalEntity;
  }

  static MyEntity getVolatileEntity() {
    return volatileEntity;
  }

  public static void main(String args[]) throws InterruptedException {
//    {
//      Thread reader = new Thread(() -> {
//        int localValue = getVolatileEntity().init_value;
//        while (localValue < max) {
//          if (getVolatileEntity().init_value != localValue) {
//            System.out.printf("Reader got: [%d]\n", getVolatileEntity().init_value);
//            localValue = getVolatileEntity().init_value;
//          }
//        }
//        System.out.println("Reader exit()");
//      }, "Reader");
//      reader.start();
//
//      Thread updater = new Thread(() -> {
//        int localValue = getVolatileEntity().init_value;
//        while (localValue < max) {
//          System.out.printf("Updater: [%d]\n", ++localValue);
//          getVolatileEntity().init_value = localValue;
//          try {
//            Thread.sleep(100);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//        System.out.println("Updater exit()");
//      }, "Updater");
//      updater.start();
//      reader.join();
//      updater.join();
//    }
//
//    System.out.println("\n\n\n\n");
//    {
//      Thread reader = new Thread(() -> {
//        int localValue = getNormalEntity().init_value;
//        while (localValue < max) {
////          if (getNormalEntity().init_value != localValue) {// If this statement is commentted out, the latest init_value can be read
//            System.out.printf("Reader got: [%d]\n", getNormalEntity().init_value);
//            localValue = getNormalEntity().init_value;
////          }
//        }
//        System.out.println("Reader exit()");
//      }, "Reader");
//      reader.start();
//
//      Thread updater = new Thread(() -> {
//        int localValue = getNormalEntity().init_value;
//        while (localValue < max) {
//          System.out.printf("Updater: [%d]\n", ++localValue);
//          getNormalEntity().init_value = localValue;
//          try {
//            Thread.sleep(10);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//        System.out.println("Updater exit()");
//      }, "Updater");
//      updater.start();
//      reader.join();
//      updater.join();
//    }
//
//    System.out.println("\n\n\n\n");
//    getNormalEntity().init_value = 0;
    {
      // It turns out that I have no idea why the "getNormalEntity().init_value != localValue" works differently when volatile keyword is involved.
      // If I try to change the code structure, it work differently.
      // 1) If Extract method demo(MyEntity entity), then the reader thread works well.
      Thread reader = new Thread(() -> {
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          if (getNormalEntity().init_value != localValue) {// If this statement is commentted out, the latest init_value can be read
          System.out.printf("Reader got: [%d]\n", getNormalEntity().init_value);
          localValue = getNormalEntity().init_value;
          }
        }
        System.out.println("Reader exit()");
      }, "Reader");
      reader.start();

      Thread updater = new Thread(() -> {
        int localValue = getNormalEntity().init_value;
        while (localValue < max) {
          System.out.printf("Updater: [%d]\n", ++localValue);
          getNormalEntity().init_value = localValue;
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Updater exit()");
      }, "Updater");
      updater.start();
      reader.join();
      updater.join();
    }
  }

}
