package huaminglin.demo.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SecondBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    static {
        System.out.println("SecondBeanFactoryPostProcessor.static");
    }

    public SecondBeanFactoryPostProcessor() {
        System.out.println("SecondBeanFactoryPostProcessor()");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("SecondBeanFactoryPostProcessor.postProcessBeanFactory");
    }
}
