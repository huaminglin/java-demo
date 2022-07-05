package huaminglin.demo.spring.boot.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class SpringBootEnvironmentDemo {

  private static void checkPropertySource(ConfigurableEnvironment environment) {
    MutablePropertySources propertySources = environment.getPropertySources();
    System.out.println("propertySources: " + propertySources.getClass().getName());
    for (PropertySource source : propertySources) {
      System.out.println(source.getName() + ": " + source.getClass().getName());
    }
  }

  private static void checkConverter(ConfigurableEnvironment environment) {
    ApplicationConversionService conversionService = (ApplicationConversionService) environment.getConversionService();
    System.out.println("conversionService: " + conversionService.getClass().getName());
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootEnvironmentDemo.class, args);
    ConfigurableEnvironment environment = context.getEnvironment();
    checkPropertySource(environment);
    checkConverter(environment);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
