package huaminglin.demo.jdk.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaxPackageClassLoader extends URLClassLoader {

  public JavaxPackageClassLoader(URL url) {
    super(new URL[] {url}, ClassLoader.getSystemClassLoader());
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.println("JavaxPackageClassLoader.loadClass: " + name);
    return super.loadClass(name);
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve)
      throws ClassNotFoundException {
    System.out.println("JavaxPackageClassLoader.loadClass: " + name + ", " + resolve);
    if (name.startsWith("javax.")) {
      Class<?> aClass = super.findClass(name);

      if (resolve) {
          resolveClass(aClass);
      }
      return aClass;
    }
    return super.loadClass(name, resolve);
  }

  // public final Class<?> findLoadedClass(String name) {} // Can not override a final method.

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    System.out.println("JavaxPackageClassLoader.findClass: " + name);
    return super.findClass(name);
  }

  // protected final void resolveClass(Class<?> c) {} // Can not override a final method.

  // protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError // Can not override a final method.

  public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
    URL url = new File("/tmp/rt.jar").toURI().toURL();
    JavaxPackageClassLoader classLoader = new JavaxPackageClassLoader(url);
    Class<?> aClass = Class.forName("javax.security.auth.Subject", true, classLoader);
    Class<?> bClass = Class.forName("javax.security.auth.Subject");
    System.out.println(aClass.getClassLoader());
    System.out.println(bClass.getClassLoader());
    System.out.println(bClass.equals(aClass));
  }
}
