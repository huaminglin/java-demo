# Demo how @Trnasactional works

## Get txid_current()

```log
sql1()
581
sql2()
582
sql3()
583
583
sql4()
584
585
sql5()
sql1()
586
sql2()
586
```

Conclusion:
@Transactional marks a transaction;
If no @Transactional, each query runs in a separate transaction;
@Transactional inside a @Transactional doesn't start a new transaction be default.

## How JdbcTemplate is created

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.jdbc.core.JdbcTemplate.<init>(JdbcTemplate.java:164)
	  at org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration$JdbcTemplateConfiguration.jdbcTemplate(JdbcTemplateAutoConfiguration.java:69)
	  at org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration$JdbcTemplateConfiguration$$EnhancerBySpringCGLIB$$26379c8b.CGLIB$jdbcTemplate$0(<generated>:-1)
	  at org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration$JdbcTemplateConfiguration$$EnhancerBySpringCGLIB$$26379c8b$$FastClassBySpringCGLIB$$1c506cd.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:244)
	  at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:363)
	  at org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration$JdbcTemplateConfiguration$$EnhancerBySpringCGLIB$$26379c8b.jdbcTemplate(<generated>:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:622)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:456)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1321)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1160)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$157.1704237553.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  - locked <0x19e1> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:277)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1251)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1171)
	  at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:593)
	  at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:90)
	  at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:374)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1411)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:592)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$157.1704237553.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:845)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
	  - locked <0x19e2> (a java.lang.Object)
	  at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:744)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:391)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:312)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1204)
	  at huaminglin.demo.jdbc.spring.transaction.SpringTransactionDemo.main(SpringTransactionDemo.java:27)

## How PgConnection is created

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.jdbc.PgConnection.<init>(PgConnection.java:197)
	  at org.postgresql.Driver.makeConnection(Driver.java:458)
	  at org.postgresql.Driver.connect(Driver.java:260)
	  at java.sql.DriverManager.getConnection(DriverManager.java:677)
	  at java.sql.DriverManager.getConnection(DriverManager.java:189)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriverManager(DriverManagerDataSource.java:154)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriver(DriverManagerDataSource.java:145)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnectionFromDriver(AbstractDriverBasedDataSource.java:205)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnection(AbstractDriverBasedDataSource.java:169)
	  at org.springframework.jdbc.datasource.DataSourceTransactionManager.doBegin(DataSourceTransactionManager.java:262)
	  at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:378)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.createTransactionIfNecessary(TransactionAspectSupport.java:475)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:289)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$EnhancerBySpringCGLIB$$d6998ab7.sql1(<generated>:-1)
	  at huaminglin.demo.jdbc.spring.transaction.SpringTransactionDemo.main(SpringTransactionDemo.java:29)

## How @Transaction commit a transaction

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:276)
	  - locked <0x1d10> (a org.postgresql.core.v3.QueryExecutorImpl)
	  at org.postgresql.jdbc.PgConnection.executeTransactionCommand(PgConnection.java:829)
	  at org.postgresql.jdbc.PgConnection.commit(PgConnection.java:851)
	  at org.springframework.jdbc.datasource.DataSourceTransactionManager.doCommit(DataSourceTransactionManager.java:329)
	  at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:746)
	  at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:714)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:534)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:305)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$EnhancerBySpringCGLIB$$369f9073.sql1(<generated>:-1)
	  at huaminglin.demo.jdbc.spring.transaction.SpringTransactionDemo.main(SpringTransactionDemo.java:29)


## How PgConnection execute sql when @Transactional is used

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:276)
	  - locked <0x1d10> (a org.postgresql.core.v3.QueryExecutorImpl)
	  at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:447)
	  at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:368)
	  at org.postgresql.jdbc.PgStatement.executeWithFlags(PgStatement.java:309)
	  at org.postgresql.jdbc.PgStatement.executeCachedSql(PgStatement.java:295)
	  at org.postgresql.jdbc.PgStatement.executeWithFlags(PgStatement.java:272)
	  at org.postgresql.jdbc.PgStatement.executeQuery(PgStatement.java:225)
	  at org.springframework.jdbc.core.JdbcTemplate$1QueryStatementCallback.doInStatement(JdbcTemplate.java:438)
	  at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:375)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:451)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:461)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:472)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:479)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService.sql1(TransactionService.java:17)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$FastClassBySpringCGLIB$$eb17c41a.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	  at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	  at org.springframework.transaction.interceptor.TransactionInterceptor$$Lambda$453.1141264726.proceedWithInvocation(Unknown Source:-1)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:295)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$EnhancerBySpringCGLIB$$369f9073.sql1(<generated>:-1)
	  at huaminglin.demo.jdbc.spring.transaction.SpringTransactionDemo.main(SpringTransactionDemo.java:29)


## How PgConnection execute sql when @Transactional is not used

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:276)
	  - locked <0x1df0> (a org.postgresql.core.v3.QueryExecutorImpl)
	  at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:447)
	  at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:368)
	  at org.postgresql.jdbc.PgStatement.executeWithFlags(PgStatement.java:309)
	  at org.postgresql.jdbc.PgStatement.executeCachedSql(PgStatement.java:295)
	  at org.postgresql.jdbc.PgStatement.executeWithFlags(PgStatement.java:272)
	  at org.postgresql.jdbc.PgStatement.executeQuery(PgStatement.java:225)
	  at org.springframework.jdbc.core.JdbcTemplate$1QueryStatementCallback.doInStatement(JdbcTemplate.java:438)
	  at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:375)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:451)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:461)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:472)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:479)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService.sql4(TransactionService.java:51)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$FastClassBySpringCGLIB$$eb17c41a.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:684)
	  at huaminglin.demo.jdbc.spring.transaction.TransactionService$$EnhancerBySpringCGLIB$$c3483aab.sql4(<generated>:-1)
	  at huaminglin.demo.jdbc.spring.transaction.SpringTransactionDemo.main(SpringTransactionDemo.java:32)

## org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction

```
            TransactionInfo txInfo = createTransactionIfNecessary(tm, txAttr, joinpointIdentification);

				completeTransactionAfterThrowing(txInfo, ex);

				cleanupTransactionInfo(txInfo);
			commitTransactionAfterReturning(txInfo);
```

## datasource-proxy logging

```log
sql1()
2020-06-25 18:09:05.718  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:1, Time:6, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
617
sql2()
2020-06-25 18:09:05.728  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:2, Time:1, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
618
sql3()
2020-06-25 18:09:05.733  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:3, Time:0, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
619
2020-06-25 18:09:05.734  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:3, Time:0, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
619
sql4()
2020-06-25 18:09:05.740  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:4, Time:1, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
620
2020-06-25 18:09:05.745  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:5, Time:0, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
621
sql5()
sql1()
2020-06-25 18:09:05.753  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:6, Time:1, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
622
sql2()
2020-06-25 18:09:05.754  INFO 19253 --- [           main] n.t.d.l.l.SLF4JQueryLoggingListener      : Name:, Connection:6, Time:1, Success:True, Type:Statement, Batch:False, QuerySize:1, BatchSize:0, Query:["SELECT txid_current()"], Params:[]
622
```
