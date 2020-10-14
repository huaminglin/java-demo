package huaminglin.demo.jdk.classloader;

import java.util.ArrayList;
import javax.management.ObjectName;

public class ClassLoaderInfo {

  public static void printClassLoaders() {
    System.out.println("ClassLoader.getSystemClassLoader(): "
        + ClassLoader.getSystemClassLoader());

    System.out.println("ClassLoader.getPlatformClassLoader(): "
        + ClassLoader.getPlatformClassLoader());

    System.out.println("Thread.currentThread().getContextClassLoader(): "
        + Thread.currentThread().getContextClassLoader());


    System.out.println("Classloader of this class: "
        + LoggerClassLoader.class.getClassLoader());


    System.out.println("Classloader of ArrayList: "
        + ArrayList.class.getClassLoader());

    System.out.println("Classloader of javax.management.ObjectName: "
        + ObjectName.class.getClassLoader());
  }
  public static void main(String[] args) throws ClassNotFoundException {
    printClassLoaders();
  }
  /*
ClassLoader.getSystemClassLoader(): jdk.internal.loader.ClassLoaders$AppClassLoader@42a57993
ClassLoader.getPlatformClassLoader(): jdk.internal.loader.ClassLoaders$PlatformClassLoader@6ce253f1
Thread.currentThread().getContextClassLoader(): jdk.internal.loader.ClassLoaders$AppClassLoader@42a57993
Classloader of this class: jdk.internal.loader.ClassLoaders$AppClassLoader@42a57993
Classloader of ArrayList: null
Classloader of javax.management.ObjectName: null
   */
}
