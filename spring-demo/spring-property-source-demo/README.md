# How @Value works

## MY_SERVICE_NAME

MY_SERVICE_NAME="MY_SERVICE_NAME" mvn exec:java

Name: MY_SERVICE_NAME


## -Dmy.service.name

MAVEN_OPTS="-Dmy.service.name=my.service.name/1" mvn exec:java

Name: my.service.name/1

## MY_SERVICE_NAME AND -Dmy.service.name

MY_SERVICE_NAME="MY_SERVICE_NAME" MAVEN_OPTS="-Dmy.service.name=my.service.name/1" mvn exec:java

Name: my.service.name/1

Conclusion: Java system property has higher priority than environment variable.

It is a little weird. This means if we hardcode some Java system property in some launch script, we can't override it though environment variable.

## @Value is kind of DependencyDescriptor

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1247)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1227)
	  at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:640)
	  at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:130)
	  at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:399)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1420)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:593)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:516)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:324)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$62.710239027.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	  - locked <0x616> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:322)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:897)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:879)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:551)
	  - locked <0x64f> (a java.lang.Object)
	  at huaminglin.demo.spring.propertysource.SpringPropertySourceDemo.main(SpringPropertySourceDemo.java:10)
```

```
org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency
public Object doResolveDependency(DependencyDescriptor descriptor, @Nullable String beanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException

beanName="myService"
descriptor={DependencyDescriptor@1522} "field 'name'"
typeConverter={SimpleTypeConverter@1525}
```

```
org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization
		// Register a default embedded value resolver if no bean post-processor
		// (such as a PropertyPlaceholderConfigurer bean) registered any before:
		// at this point, primarily for resolution in annotation attribute values.
		if (!beanFactory.hasEmbeddedValueResolver()) {
			beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
		}

org.springframework.core.env.AbstractEnvironment.resolvePlaceholders

this.propertyResolver: org.springframework.core.env.PropertySourcesPropertyResolver
this.propertyResolver.propertySources=[PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]


org.springframework.core.env.PropertySourcesPropertyResolver.getProperty(java.lang.String, java.lang.Class<T>, boolean)
                if (logger.isTraceEnabled()) {
					logger.trace("Searching for key '" + key + "' in PropertySource '" +
							propertySource.getName() + "'");
				}
```

Conclusion: The environment has [PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}].

And this is why systemProperties has higher priority than systemEnvironment.

```
org.springframework.core.env.SystemEnvironmentPropertySource.checkPropertyName
	@Nullable
	private String checkPropertyName(String name) {
		// Check name as-is
		if (containsKey(name)) {
			return name;
		}
		// Check name with just dots replaced
		String noDotName = name.replace('.', '_');
		if (!name.equals(noDotName) && containsKey(noDotName)) {
			return noDotName;
		}
		// Check name with just hyphens replaced
		String noHyphenName = name.replace('-', '_');
		if (!name.equals(noHyphenName) && containsKey(noHyphenName)) {
			return noHyphenName;
		}
		// Check name with dots and hyphens replaced
		String noDotNoHyphenName = noDotName.replace('-', '_');
		if (!noDotName.equals(noDotNoHyphenName) && containsKey(noDotNoHyphenName)) {
			return noDotNoHyphenName;
		}
		// Give up
		return null;
	}
```

Conclusion: SystemEnvironmentPropertySource support some kind of relaxed binding for @Value.

## Enable JUL for Spring application

MAVEN_OPTS="-Dmy.service.name=my.service.name/1 -Djava.util.logging.config.file=src/main/resources/logging.properties" mvn exec:java
