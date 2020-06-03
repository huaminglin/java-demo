package huaminglin.demo.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class SecondBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    static {
        System.out.println("SecondBeanDefinitionRegistryPostProcessor.static");
    }

    public SecondBeanDefinitionRegistryPostProcessor() {
        System.out.println("SecondBeanDefinitionRegistryPostProcessor()");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("SecondBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry {");
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SecondBeanFactoryPostProcessor.class);
        registry.registerBeanDefinition("secondBeanFactoryPostProcessor", beanDefinition);
        System.out.println("registerBeanDefinition: secondBeanFactoryPostProcessor");
        System.out.println("} SecondBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("SecondBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
    }
}
