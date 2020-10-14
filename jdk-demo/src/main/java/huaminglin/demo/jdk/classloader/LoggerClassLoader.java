package huaminglin.demo.jdk.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoggerClassLoader extends URLClassLoader {

  public LoggerClassLoader(URL url) {
    super(new URL[] {url}, ClassLoader.getSystemClassLoader());
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.println("LoggerClassLoader.loadClass: " + name);
    return super.loadClass(name);
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve)
      throws ClassNotFoundException {
    System.out.println("LoggerClassLoader.loadClass: " + name + ", " + resolve);
    return super.loadClass(name, resolve);
  }

  // public final Class<?> findLoadedClass(String name) {} // Can not override a final method.

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    System.out.println("LoggerClassLoader.findClass: " + name);
    return super.findClass(name);
  }

  // protected final void resolveClass(Class<?> c) {} // Can not override a final method.

  // protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError // Can not override a final method.

  public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
    String jarfilepath = System.getProperty("user.home")
        + "/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar";
    URL url = new File(jarfilepath).toURI().toURL();
    System.out.println(url);
    LoggerClassLoader classLoader = new LoggerClassLoader(url);
    Class<?> aClass = Class.forName("org.slf4j.LoggerFactory", true, classLoader);
  }
}
