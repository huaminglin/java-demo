package huaminglin.demo.jdk.concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileValueDemo {

  final static int max = 5;
  static MyEntity entity = new MyEntity();
  static volatile MyEntity volatileEntity = new MyEntity();

  static MyEntity getEntity() {
    return volatileEntity;
//    return entity;
  }

  public static void main(String args[]) {
    Thread reader = new Thread(() -> {
      int localValue = getEntity().init_value;
      while (localValue < max) {
        if (getEntity().init_value != localValue) {
          System.out.printf("The init_value is update ot [%d]\n", getEntity().init_value);
          localValue = getEntity().init_value;
        }
      }
      System.out.println("Reader exit()");
    }, "Reader");
    reader.start();

    Thread updater = new Thread(() -> {
      int localValue = getEntity().init_value;
      while (localValue < max) {
        System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
        getEntity().init_value = localValue;
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Updater exit()");
    }, "Updater");
    updater.start();
  }

}
