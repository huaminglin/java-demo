package huaminglin.demo.datatype.jinjava;

import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.el.ext.JinjavaBeanELResolver;
import java.lang.reflect.Field;
import java.util.ArrayList;
import jinjava.javax.el.CompositeELResolver;
import jinjava.javax.el.ELContext;
import jinjava.javax.el.PropertyNotFoundException;

public class BeanELReflectionResolver extends JinjavaBeanELResolver {

  private BeanELReflectionResolver() {
    super(true);
  }

  public static void hackJinjavaConfig(JinjavaConfig jinjavaConfig) {
    CompositeELResolver elResolver = (CompositeELResolver) jinjavaConfig.getElResolver();
    try {
      Field declaredField = CompositeELResolver.class.getDeclaredField("resolvers");
      declaredField.setAccessible(true);
      ArrayList list = (ArrayList) declaredField.get(elResolver);
      list.set(list.size() -1, new BeanELReflectionResolver());
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  public static Field getField(Class<?> clazz, String name) {
    Field field = null;
    while (clazz != null && field == null) {
      try {
        field = clazz.getDeclaredField(name);
      } catch (Exception ignored) {
      }
      clazz = clazz.getSuperclass();
    }
    return field;
  }

  @Override
  public Object getValue(ELContext context, Object base, Object property) {
    try {
      Object result = super.getValue(context, base, property);
      return result instanceof Class ? null : result;
    } catch (PropertyNotFoundException e) {
      Field field = getField(base.getClass(), property.toString());
      field.setAccessible(true);
      try {
        Object value = field.get(base);
        context.setPropertyResolved(true);
        return value;
      } catch (IllegalAccessException illegalAccessException) {
        illegalAccessException.printStackTrace();
        throw e;
      }
    }
  }
}
