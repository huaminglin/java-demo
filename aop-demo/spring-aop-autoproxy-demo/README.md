mvn exec:java

## log "not eligible for auto-proxying"

May 29, 2020 12:49:45 AM org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker postProcessAfterInitialization
INFO: Bean 'myConfiguration' of type [huaminglin.demo.aop.spring.autoproxy.MyConfiguration$$EnhancerBySpringCGLIB$$ab45cdf5] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)

Note: Not sure how to avoid this kind of error.
It seems DefaultAdvisorAutoProxyCreator should not be created through Java config.
Solution: Use BeanDefinitionRegistryPostProcessor to create the bean.

## Check how the proxy is created for the target bean

Set a conditional breakpoint on org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.wrapIfNecessary: "myBean".equals(beanName)

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.wrapIfNecessary(AbstractAutoProxyCreator.java:349)
	  at org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.postProcessAfterInitialization(AbstractAutoProxyCreator.java:299)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(AbstractAutowireCapableBeanFactory.java:429)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1782)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:593)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$39.178049969.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  - locked <0x755> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:845)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
	  - locked <0x756> (a java.lang.Object)
	  at huaminglin.demo.aop.spring.autoproxy.SpringAopAutoProxyDemo.main(SpringAopAutoProxyDemo.java:10)

```Java
org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.wrapIfNecessary
		// Create proxy if we have advice.
		Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
		if (specificInterceptors != DO_NOT_PROXY) {
			this.advisedBeans.put(cacheKey, Boolean.TRUE);
			Object proxy = createProxy(
					bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
			this.proxyTypes.put(cacheKey, proxy.getClass());
			return proxy;
		}
```

## org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean


Set a conditional breakpoint on org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean: "myBean".equals(beanName)

AbstractAutowireCapableBeanFactory.doCreateBean:
        exposedObject = initializeBean(beanName, exposedObject, mbd);
		return exposedObject;

AbstractAutowireCapableBeanFactory.initializeBean
    wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)
    return wrappedBean;

## getBeanPostProcessors

org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization

getBeanPostProcessors():
0 = {ApplicationContextAwareProcessor@2061}
1 = {ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor@2062}
2 = {PostProcessorRegistrationDelegate$BeanPostProcessorChecker@2063}
3 = {DefaultAdvisorAutoProxyCreator@1856} "proxyTargetClass=false; optimize=false; opaque=false; exposeProxy=false; frozen=false"
4 = {AutowiredAnnotationBeanPostProcessor@2064}
5 = {ApplicationListenerDetector@2065}

Note: DefaultAdvisorAutoProxyCreator is a SmartInstantiationAwareBeanPostProcessor, then it has chance to create a proxy for the target bean.
