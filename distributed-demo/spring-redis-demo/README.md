## Demo Spring Redis

## StudentRepository

class com.sun.proxy.$Proxy48

org.springframework.aop.framework.ProxyFactory: 5 interfaces [huaminglin.demo.redis.spring.StudentRepository, org.springframework.data.repository.Repository, org.springframework.transaction.interceptor.TransactionalProxy, org.springframework.aop.framework.Advised, org.springframework.core.DecoratingProxy]; 1 advisors [org.springframework.dao.annotation.PersistenceExceptionTranslationAdvisor@db44aa2]; targetSource [SingletonTargetSource for target object [com.sun.proxy.$Proxy48@56637cff]]; proxyTargetClass=true; optimize=false; opaque=false; exposeProxy=false; frozen=false

## redis-demo/monitor.sh

xxx.736038 [0 172.25.0.1:35914] "HGETALL" "Student:id1"
xxx.774941 [0 172.25.0.1:35914] "HGETALL" "Student:id1"
xxx.784207 [0 172.25.0.1:35914] "DEL" "Student:id1"
xxx.786376 [0 172.25.0.1:35914] "HMSET" "Student:id1" "_class" "huaminglin.demo.redis.spring.Student" "id" "id1" "name" "name1"
xxx.788870 [0 172.25.0.1:35914] "SADD" "Student" "id1"


## How Repository mechanism works

org.springframework.aop.framework.JdkDynamicAopProxy
org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor
org.springframework.data.keyvalue.repository.support.SimpleKeyValueRepository
org.springframework.data.redis.core.RedisKeyValueTemplate
org.springframework.data.keyvalue.core.KeyValueTemplate
org.springframework.data.redis.core.RedisTemplate
org.springframework.data.redis.connection.DefaultedRedisConnection
org.springframework.data.redis.connection.lettuce.LettuceHashCommands
io.lettuce.core.AbstractRedisAsyncCommands

```stack
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at io.lettuce.core.AbstractRedisAsyncCommands.hmset(AbstractRedisAsyncCommands.java:725)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at io.lettuce.core.FutureSyncInvocationHandler.handleInvocation(FutureSyncInvocationHandler.java:57)
	  at io.lettuce.core.internal.AbstractInvocationHandler.invoke(AbstractInvocationHandler.java:80)
	  at com.sun.proxy.$Proxy53.hmset(Unknown Source:-1)
	  at org.springframework.data.redis.connection.lettuce.LettuceHashCommands.hMSet(LettuceHashCommands.java:345)
	  at org.springframework.data.redis.connection.DefaultedRedisConnection.hMSet(DefaultedRedisConnection.java:973)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.data.redis.core.CloseSuppressingInvocationHandler.invoke(CloseSuppressingInvocationHandler.java:61)
	  at com.sun.proxy.$Proxy56.hMSet(Unknown Source:-1)
	  at org.springframework.data.redis.core.RedisKeyValueAdapter.lambda$put$0(RedisKeyValueAdapter.java:230)
	  at org.springframework.data.redis.core.RedisKeyValueAdapter$$Lambda$549.1190857779.doInRedis(Unknown Source:-1)
	  at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:224)
	  at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:184)
	  at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:171)
	  at org.springframework.data.redis.core.RedisKeyValueAdapter.put(RedisKeyValueAdapter.java:223)
	  at org.springframework.data.keyvalue.core.KeyValueTemplate.lambda$update$1(KeyValueTemplate.java:205)
	  at org.springframework.data.keyvalue.core.KeyValueTemplate$$Lambda$540.441683672.doInKeyValue(Unknown Source:-1)
	  at org.springframework.data.keyvalue.core.KeyValueTemplate.execute(KeyValueTemplate.java:346)
	  at org.springframework.data.keyvalue.core.KeyValueTemplate.update(KeyValueTemplate.java:205)
	  at org.springframework.data.redis.core.RedisKeyValueTemplate.update(RedisKeyValueTemplate.java:179)
	  at org.springframework.data.keyvalue.repository.support.SimpleKeyValueRepository.save(SimpleKeyValueRepository.java:104)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:359)
	  at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:200)
	  at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:644)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:608)
	  at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.lambda$invoke$3(RepositoryFactorySupport.java:595)
	  at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor$$Lambda$459.365201320.get(Unknown Source:-1)
	  at org.springframework.data.repository.util.QueryExecutionConverters$$Lambda$458.1732005742.apply(Unknown Source:-1)
	  at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:595)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:59)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor.invoke(SurroundingTransactionDetectorMethodInterceptor.java:61)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
	  at com.sun.proxy.$Proxy45.save(Unknown Source:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:343)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	  at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
	  at com.sun.proxy.$Proxy45.save(Unknown Source:-1)
	  at huaminglin.demo.redis.spring.StudentService.save(StudentService.java:21)
	  at huaminglin.demo.redis.spring.SpringRedisDemo.main(SpringRedisDemo.java:13)
```
