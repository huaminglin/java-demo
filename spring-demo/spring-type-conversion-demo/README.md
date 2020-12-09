# Spring Type Conversion Demo

## no matching editors or conversion strategy found

```
Dec 09, 2020 8:06:13 PM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'myService': Unsatisfied dependency expressed through field 'name'; nested exception is org.springframework.beans.ConversionNotSupportedException: Failed to convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName': no matching editors or conversion strategy found
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'myService': Unsatisfied dependency expressed through field 'name'; nested exception is org.springframework.beans.ConversionNotSupportedException: Failed to convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName': no matching editors or conversion strategy found
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:643)
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:130)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:399)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1420)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:593)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:516)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:324)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:322)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:897)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:879)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:551)
	at huaminglin.demo.spring.typeconversion.SpringTypeConversionDemo.main(SpringTypeConversionDemo.java:10)
Caused by: org.springframework.beans.ConversionNotSupportedException: Failed to convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName': no matching editors or conversion strategy found
	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:76)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1255)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1227)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:640)
	... 13 more
Caused by: java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'huaminglin.demo.spring.typeconversion.MyName': no matching editors or conversion strategy found
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:262)
	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:73)
	... 16 more

Process finished with exit code 1
```

## Run the application with logging enabled

MAVEN_OPTS="-Djava.util.logging.config.file=src/main/resources/logging.properties" mvn exec:java
