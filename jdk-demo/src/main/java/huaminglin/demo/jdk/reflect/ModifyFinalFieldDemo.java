package huaminglin.demo.jdk.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ModifyFinalFieldDemo {
    public static final Integer MAX = 100;

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
     }

    public static void main(String args[]) throws Exception {
        setFinalStatic(ModifyFinalFieldDemo.class.getField("MAX"), 200);

        System.out.format("Max is %s", MAX);
     }
}
