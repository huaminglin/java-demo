package huaminglin.demo.spring.boot;

import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component("myCustomizedAwareBeanName")
public class MyAwareBean implements BeanFactoryAware, BeanNameAware, BeanClassLoaderAware, ApplicationEventPublisherAware,
    MessageSourceAware, ResourceLoaderAware, ApplicationStartupAware, EnvironmentAware, EmbeddedValueResolverAware,
    ImportAware, LoadTimeWeaverAware, ApplicationContextAware,
    NotificationPublisherAware, InitializingBean, DisposableBean, SmartInitializingSingleton {
  @Value("${user.home}")
  private String value;

  static {
    System.out.println("MyAwareBean.static");
  }

  public MyAwareBean() {
    System.out.println("MyAwareBean()");
  }

  @PostConstruct
  public void myPostConstruct() {
    System.out.println("@PostConstruct: init()");
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {

  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

  }

  @Override
  public void setEmbeddedValueResolver(StringValueResolver resolver) {

  }

  @Override
  public void setEnvironment(Environment environment) {

  }

  @Override
  public void setMessageSource(MessageSource messageSource) {

  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {

  }

  @Override
  public void setImportMetadata(AnnotationMetadata importMetadata) {

  }

  @Override
  public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {

  }

  @Override
  public void setBeanName(String name) {

  }

  @Override
  public void setApplicationStartup(ApplicationStartup applicationStartup) {

  }

  @Override
  public void setNotificationPublisher(NotificationPublisher notificationPublisher) {

  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }

  @Override
  public void destroy() throws Exception {

  }

  @Override
  public void afterSingletonsInstantiated() {

  }
}
