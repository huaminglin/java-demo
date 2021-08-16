package huaminglin.demo.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class SpringBootDemo {

  @Primary
  @Bean("myCustomizedBeanName")
  MyBean myBean() {
    return new MyBean();
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemo.class, args);
    MyBean bean = context.getBean(MyBean.class);
    System.out.println(bean.getUserHome());
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
