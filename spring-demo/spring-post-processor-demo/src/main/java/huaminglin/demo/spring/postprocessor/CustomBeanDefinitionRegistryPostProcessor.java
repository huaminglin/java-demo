package huaminglin.demo.spring.postprocessor;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    static {
        System.out.println("CustomBeanDefinitionRegistryPostProcessor.static");
    }

    public CustomBeanDefinitionRegistryPostProcessor() {
        System.out.println("CustomBeanDefinitionRegistryPostProcessor()");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("CustomBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry {");
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SecondBeanDefinitionRegistryPostProcessor.class);
        registry.registerBeanDefinition("secondBeanDefinitionRegistryPostProcessor", beanDefinition);
        System.out.println("registerBeanDefinition: secondBeanDefinitionRegistryPostProcessor");
        System.out.println("} CustomBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanDefinitionRegistryPostProcessor.postProcessBeanFactory");
    }
}
