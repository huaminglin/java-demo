package huaminglin.demo.datatype.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public final class FreeMarkerDemo {

  public static void main(String[] args) throws IOException, TemplateException {
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("className", "AutoCodeDemo");
    StringWriter result = new StringWriter();
    Template template = new Template("template", new StringReader("${className}"),
        new Configuration(Configuration.VERSION_2_3_23));
    template.process(dataMap, result);
    System.out.println(result);
  }
}
