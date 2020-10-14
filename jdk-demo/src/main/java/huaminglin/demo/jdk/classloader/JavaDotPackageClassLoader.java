package huaminglin.demo.jdk.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaDotPackageClassLoader extends URLClassLoader {

  public JavaDotPackageClassLoader(URL url) {
    super(new URL[] {url}, ClassLoader.getSystemClassLoader());
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    System.out.println("JavaDotPackageClassLoader.loadClass: " + name);
    return super.loadClass(name);
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve)
      throws ClassNotFoundException {
    System.out.println("JavaDotPackageClassLoader.loadClass: " + name + ", " + resolve);
    super.findClass(name);
    return super.loadClass(name, resolve);
  }

  // public final Class<?> findLoadedClass(String name) {} // Can not override a final method.

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    System.out.println("JavaDotPackageClassLoader.findClass: " + name);
    return super.findClass(name);
  }

  // protected final void resolveClass(Class<?> c) {} // Can not override a final method.

  // protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError // Can not override a final method.

  public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
    URL url = new File("/tmp/rt.jar").toURI().toURL();
    JavaDotPackageClassLoader classLoader = new JavaDotPackageClassLoader(url);
    Class<?> aClass = Class.forName("java.lang.String", true, classLoader);
    System.out.println(String.class.equals(aClass));
  }
}
