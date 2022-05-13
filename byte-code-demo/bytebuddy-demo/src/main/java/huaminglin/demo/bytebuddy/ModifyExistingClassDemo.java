package huaminglin.demo.bytebuddy;

import java.lang.reflect.Field;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.matcher.ElementMatchers;

public final class ModifyExistingClassDemo {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    Class<? extends MyExistingClass> dynamicType = new ByteBuddy()
        .redefine(MyExistingClass.class)
        .name("huaminglin.demo.bytebuddy.NewClass")
        .field(ElementMatchers.named("NAME"))
        .value("newName")
        .make()
        .load(ModifyExistingClassDemo.class.getClassLoader())
        .getLoaded();
    Field field = dynamicType.getDeclaredField("NAME");
    Object fieldValue = field.get(null);
    System.out.println(dynamicType.getSuperclass().getName());
    System.out.println(dynamicType.getName());
    System.out.println(fieldValue);
    System.out.println(MyExistingClass.NAME);
    /*
java.lang.Object
huaminglin.demo.bytebuddy.NewClass
newName
name
     */
  }
}
