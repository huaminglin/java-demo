mvn exec:java

## log "not eligible for auto-proxying"

May 29, 2020 12:49:45 AM org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker postProcessAfterInitialization
INFO: Bean 'myConfiguration' of type [huaminglin.demo.aop.spring.autoproxy.MyConfiguration$$EnhancerBySpringCGLIB$$ab45cdf5] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)

Note: Not sure how to avoid this kind of error.
It seems DefaultAdvisorAutoProxyCreator should not be created through Java config.
Solution: Use BeanDefinitionRegistryPostProcessor to create the bean.
