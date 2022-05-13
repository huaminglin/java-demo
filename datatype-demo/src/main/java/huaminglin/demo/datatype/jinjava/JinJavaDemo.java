package huaminglin.demo.datatype.jinjava;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.JinjavaConfig.Builder;
import com.hubspot.jinjava.interpret.RenderResult;
import java.util.HashMap;
import java.util.Map;

public final class JinJavaDemo {

  public static Object execute(JinjavaConfig jinjavaConfig, MyClass context) {
    Jinjava jinjava = new Jinjava(jinjavaConfig);

    Map<String, Object> root = new HashMap<>();
    root.put("context", context);
    RenderResult renderResult = jinjava.renderForResult("{{context.myField}}", root);
    if (renderResult.hasErrors()) {
      return renderResult.getErrors();
    }
    return renderResult.getOutput();
  }


  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    Builder builder = JinjavaConfig.newBuilder();
    JinjavaConfig jinjavaConfig = builder.build();
    BeanELReflectionResolver.hackJinjavaConfig(jinjavaConfig);
    System.out.println(execute(jinjavaConfig, new MyClass()));
  }
}
