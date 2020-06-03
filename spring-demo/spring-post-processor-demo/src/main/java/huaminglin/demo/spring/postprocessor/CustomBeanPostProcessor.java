package huaminglin.demo.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanPostProcessor implements BeanPostProcessor {

    static {
        System.out.println("CustomBeanPostProcessor.static");
    }

    public CustomBeanPostProcessor() {
        System.out.println("CustomBeanPostProcessor()");
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Called CustomBeanPostProcessor.postProcessBeforeInitialization() for : " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Called CustomBeanPostProcessor.postProcessAfterInitialization() for : " + beanName);
        return bean;
    }

}
