package huaminglin.demo.spring.bean.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanJavaDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("huaminglin.demo.spring.bean");
    context.refresh();
    MyHandler myHandler = context.getBean(MyHandler.class);
    System.out.println(myHandler.isActive());
    context.close();
  }
}
