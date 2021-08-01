# Check how @Value resolve placeholder

## Could not resolve placeholder

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'springBootDemo': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'lc.time2' in value "${lc.time2}"
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:405) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1422) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:594) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:882) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878) ~[spring-context-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.6.RELEASE.jar:2.2.6.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.6.RELEASE.jar:2.2.6.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.6.RELEASE.jar:2.2.6.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.6.RELEASE.jar:2.2.6.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.6.RELEASE.jar:2.2.6.RELEASE]
	at huaminglin.demo.spring.boot.SpringBootDemo.main(SpringBootDemo.java:15) ~[classes/:na]
Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'lc.time2' in value "${lc.time2}"
	at org.springframework.util.PropertyPlaceholderHelper.parseStringValue(PropertyPlaceholderHelper.java:178) ~[spring-core-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.util.PropertyPlaceholderHelper.replacePlaceholders(PropertyPlaceholderHelper.java:124) ~[spring-core-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.core.env.AbstractPropertyResolver.doResolvePlaceholders(AbstractPropertyResolver.java:236) ~[spring-core-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.core.env.AbstractPropertyResolver.resolveRequiredPlaceholders(AbstractPropertyResolver.java:210) ~[spring-core-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.context.support.PropertySourcesPlaceholderConfigurer.lambda$processProperties$0(PropertySourcesPlaceholderConfigurer.java:175) ~[spring-context-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.resolveEmbeddedValue(AbstractBeanFactory.java:909) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1231) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1210) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:640) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:130) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:399) ~[spring-beans-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	... 16 common frames omitted

# SystemEnvironmentPropertySource key compatibility

org.springframework.core.env.SystemEnvironmentPropertySource.resolvePropertyName
    protected final String resolvePropertyName(String name) {
        Assert.notNull(name, "Property name must not be null");
        String resolvedName = this.checkPropertyName(name);
        if (resolvedName != null) {
            return resolvedName;
        } else {
            String uppercasedName = name.toUpperCase();
            if (!name.equals(uppercasedName)) {
                resolvedName = this.checkPropertyName(uppercasedName);
                if (resolvedName != null) {
                    return resolvedName;
                }
            }
            return name;
        }
    }
    
org.springframework.core.env.SystemEnvironmentPropertySource#checkPropertyName
    private String checkPropertyName(String name) {
        if (this.containsKey(name)) {
            return name;
        } else {
            String noDotName = name.replace('.', '_');
            if (!name.equals(noDotName) && this.containsKey(noDotName)) {
                return noDotName;
            } else {
                String noHyphenName = name.replace('-', '_');
                if (!name.equals(noHyphenName) && this.containsKey(noHyphenName)) {
                    return noHyphenName;
                } else {
                    String noDotNoHyphenName = noDotName.replace('-', '_');
                    return !noDotName.equals(noDotNoHyphenName) && this.containsKey(noDotNoHyphenName) ? noDotNoHyphenName : null;
                }
            }
        }
    }

# Spring Boot and Environment

org.springframework.boot.SpringApplication#run(String...)
ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments);

org.springframework.context.support.PropertySourcesPlaceholderConfigurer
    private Environment environment;


# Call stack of org.springframework.util.PropertyPlaceholderHelper.replacePlaceholders(String, PropertyPlaceholderHelper.PlaceholderResolver)

Use conditional breakpoint: value.equals("${lc.time2}")

replacePlaceholders:123, PropertyPlaceholderHelper (org.springframework.util)
doResolvePlaceholders:236, AbstractPropertyResolver (org.springframework.core.env)
resolveRequiredPlaceholders:210, AbstractPropertyResolver (org.springframework.core.env)
lambda$processProperties$0:175, PropertySourcesPlaceholderConfigurer (org.springframework.context.support)
resolveStringValue:-1, 1617156106 (org.springframework.context.support.PropertySourcesPlaceholderConfigurer$$Lambda$217)
resolveEmbeddedValue:909, AbstractBeanFactory (org.springframework.beans.factory.support)
doResolveDependency:1231, DefaultListableBeanFactory (org.springframework.beans.factory.support)
resolveDependency:1210, DefaultListableBeanFactory (org.springframework.beans.factory.support)
inject:640, AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement (org.springframework.beans.factory.annotation)
inject:130, InjectionMetadata (org.springframework.beans.factory.annotation)
postProcessProperties:399, AutowiredAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation)
populateBean:1422, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:594, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:517, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
lambda$doGetBean$0:323, AbstractBeanFactory (org.springframework.beans.factory.support)
getObject:-1, 1528741718 (org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$151)
getSingleton:222, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:321, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:202, AbstractBeanFactory (org.springframework.beans.factory.support)
preInstantiateSingletons:882, DefaultListableBeanFactory (org.springframework.beans.factory.support)
finishBeanFactoryInitialization:878, AbstractApplicationContext (org.springframework.context.support)
refresh:550, AbstractApplicationContext (org.springframework.context.support)
refresh:747, SpringApplication (org.springframework.boot)
refreshContext:397, SpringApplication (org.springframework.boot)
run:315, SpringApplication (org.springframework.boot)
run:1226, SpringApplication (org.springframework.boot)
run:1215, SpringApplication (org.springframework.boot)
main:15, SpringBootDemo (huaminglin.demo.spring.boot)
