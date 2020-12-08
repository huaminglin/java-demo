package huaminglin.demo.spring.propertysource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class SpringPropertySourceDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("huaminglin.demo.spring.propertysource");
    context.refresh();
    MyService myService = context.getBean(MyService.class);
    System.out.println("Name: " + myService.getName());
    context.close();
  }
}
