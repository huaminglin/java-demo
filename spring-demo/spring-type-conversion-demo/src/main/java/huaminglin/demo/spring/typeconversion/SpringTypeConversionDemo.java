package huaminglin.demo.spring.typeconversion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class SpringTypeConversionDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("huaminglin.demo.spring.typeconversion");
    context.refresh();
    MyService myService = context.getBean(MyService.class);
    System.out.println("Name: " + myService.getName());
    context.close();
  }
}
