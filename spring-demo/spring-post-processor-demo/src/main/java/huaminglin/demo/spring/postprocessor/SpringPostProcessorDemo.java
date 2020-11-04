package huaminglin.demo.spring.postprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringPostProcessorDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan(SpringPostProcessorDemo.class.getPackage().getName());
    context.refresh();
  }

}
