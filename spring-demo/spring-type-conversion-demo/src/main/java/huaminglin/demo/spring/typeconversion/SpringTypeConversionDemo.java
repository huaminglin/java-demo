package huaminglin.demo.spring.typeconversion;

import java.util.Collections;
import java.util.HashSet;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configuration
public class SpringTypeConversionDemo {

  @Bean(name="conversionService")
  public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(MyConverter converter) {
    FormattingConversionServiceFactoryBean bean = new FormattingConversionServiceFactoryBean();
    bean.setConverters(new HashSet(Collections.singleton(converter)));
    return bean;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("huaminglin.demo.spring.typeconversion");
    context.refresh();
    MyService myService = context.getBean(MyService.class);
    System.out.println("Name: " + myService.getName());
    System.out.println("Age: " + myService.getAge());
    context.close();
  }
}
