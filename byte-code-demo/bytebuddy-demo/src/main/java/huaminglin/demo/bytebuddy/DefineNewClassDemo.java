package huaminglin.demo.bytebuddy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;

public final class DefineNewClassDemo {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    Class<?> dynamicType = new ByteBuddy()
        .subclass(Object.class)
        .name("huaminglin.demo.bytebuddy.MyClass")
        .defineField("myField", String.class, Modifier.PUBLIC | Modifier.FINAL | Modifier.STATIC)
        .value("My field")
        .make()
        .load(DefineNewClassDemo.class.getClassLoader())
        .getLoaded();

    Field field = dynamicType.getDeclaredField("myField");
    Object fieldValue = field.get(null);
    System.out.println(dynamicType.getName() + ": " + fieldValue);
  }
}
