package huaminglin.demo.jdk.jmx;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JMXDemo {

  public static void main(String[] args) throws Exception {
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    ObjectName name = new ObjectName("MyJMXPackage:type=MyJMXType");
    Calculator mbean = new Calculator();
    mbs.registerMBean(mbean, name);
    int seconds = Integer.parseInt(System.getProperty("sleep.seconds", "60"));
    System.out.println("Sleeping " + seconds + " seconds...");
    Thread.sleep(seconds * 1000);
  }
}
