package huaminglin.demo.jamon;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import com.jamonapi.jmx.JmxUtils;

public class JamonDemo {

  public static void main(String[] args) throws InterruptedException {
    Monitor monitor1 = MonitorFactory.start("label1");
    Thread.currentThread().sleep(2000);
    Monitor monitor2 = MonitorFactory.start("label1");
    Thread.currentThread().sleep(2000);
    monitor2.stop();
    monitor1.stop();

    System.out.println(monitor1 == monitor2);
    System.out.println(monitor1.hashCode() + ":" + monitor1);
    System.out.println(monitor2.hashCode() + ":" + monitor2);

    JmxUtils.registerMbeans();
    int seconds = Integer.parseInt(System.getProperty("sleep.seconds"));
    Thread.currentThread().sleep(seconds * 1000);
  }
}
