package huaminglin.demo.jdk.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GenericTypeDemo {

  public static void printType(Type aType) {
    if (aType instanceof ParameterizedType) {
      ParameterizedType type = (ParameterizedType) aType;
      System.out.println(
          "ParameterizedType: " + type + ", " + type.getTypeName() + ", " + type.getOwnerType()
              + ", "
              + type.getRawType());

      Type[] genericTypes = type.getActualTypeArguments();
      for (Type genericType : genericTypes) {
        if (genericType instanceof ParameterizedType) {
          printType((ParameterizedType) genericType);
        } else {
          System.out.println("Actual Type Argument: " + genericType);
        }
      }
    } else {
      System.out.println("Type: " + aType);
    }

  }

  public static void printClass(Class<?> aClass) {
    System.out.println(aClass);
    {
      System.out.println("getGenericSuperclass(): ");
      Type genericSuperclass = aClass.getGenericSuperclass();
      printType(genericSuperclass);
    }
    System.out.println("getGenericInterfaces(): ");
    for (Type type : aClass.getGenericInterfaces()) {
      printType(type);
    }
  }

  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
    // sun.net.www.http.KeepAliveStreamCleaner is JDK internal API and it is not available at compile timer by default.
    // To be compatible with Java 8 compiled codes, the internal API is available at the run time.
    // class KeepAliveStreamCleaner extends LinkedList<KeepAliveCleanerEntry> implements Runnable
    Class<?> aClass = Class.forName("sun.net.www.http.KeepAliveStreamCleaner");
    printClass(aClass);

    System.out.println("----------------------------");
    printClass(ArrayList.class);

    System.out.println("----------------------------");
    System.out.println("ArrayList.add.getGenericParameterTypes()");
    Method method = ArrayList.class.getMethod("add", Object.class);
    Type[] types = method.getGenericParameterTypes();
    for (Type type : types) {
      printType(type);
    }
  }

/*
class sun.net.www.http.KeepAliveStreamCleaner
getGenericSuperclass():
ParameterizedType: java.util.LinkedList<sun.net.www.http.KeepAliveCleanerEntry>, java.util.LinkedList<sun.net.www.http.KeepAliveCleanerEntry>, null, class java.util.LinkedList
Actual Type Argument: class sun.net.www.http.KeepAliveCleanerEntry
getGenericInterfaces():
Type: interface java.lang.Runnable
----------------------------
class java.util.ArrayList
getGenericSuperclass():
ParameterizedType: java.util.AbstractList<E>, java.util.AbstractList<E>, null, class java.util.AbstractList
Actual Type Argument: E
getGenericInterfaces():
ParameterizedType: java.util.List<E>, java.util.List<E>, null, interface java.util.List
Actual Type Argument: E
Type: interface java.util.RandomAccess
Type: interface java.lang.Cloneable
Type: interface java.io.Serializable
----------------------------
ArrayList.add.getGenericParameterTypes()
Type: E
 */
}
