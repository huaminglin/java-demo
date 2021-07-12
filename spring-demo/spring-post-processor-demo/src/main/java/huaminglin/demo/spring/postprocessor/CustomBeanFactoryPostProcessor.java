package huaminglin.demo.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  static {
    System.out.println("CustomBeanFactoryPostProcessor.static");
  }

  public CustomBeanFactoryPostProcessor() {
    System.out.println("CustomBeanFactoryPostProcessor()");
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    System.out.println("CustomBeanFactoryPostProcessor.postProcessBeanFactory: " + System.identityHashCode(beanFactory));
  }
}
