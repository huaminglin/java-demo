java -jar target/spring-kafka-boot-producer-demo-1.0-SNAPSHOT.jar

org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
org.springframework.boot.autoconfigure.kafka.ConfigurationProperties
org.springframework.boot.autoconfigure.kafka.KafkaAnnotationDrivenConfiguration

## How the @KafkaListener annotated method is invoked

"org.springframework.kafka.KafkaListenerEndpointContainer#0-0-C-1@4083" prio=5 tid=0x13 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.spring.boot.SpringBootDemo.listen(SpringBootDemo.java:25)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:181)
	  at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:114)
	  at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:48)
	  at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:248)
	  at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:80)
	  at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:51)
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:1077)
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:1057)
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:999)
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:867)
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:725)
	  at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	  at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:264)
	  at java.util.concurrent.FutureTask.run(FutureTask.java:-1)
	  at java.lang.Thread.run(Thread.java:834)

## How the org.springframework.kafka.listener.KafkaMessageListenerContainer is created

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer.<init>(KafkaMessageListenerContainer.java:147)
	  at org.springframework.kafka.listener.ConcurrentMessageListenerContainer.doStart(ConcurrentMessageListenerContainer.java:150)
	  at org.springframework.kafka.listener.AbstractMessageListenerContainer.start(AbstractMessageListenerContainer.java:269)
	  - locked <0x1139> (a java.lang.Object)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.startIfNecessary(KafkaListenerEndpointRegistry.java:289)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.start(KafkaListenerEndpointRegistry.java:238)
	  at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:182)
	  at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:53)
	  at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:360)
	  at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:158)
	  at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:122)
	  at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:885)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:553)
	  - locked <0x11e1> (a java.lang.Object)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:780)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:333)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1277)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1265)
	  at huaminglin.demo.spring.boot.SpringBootDemo.main(SpringBootDemo.java:31)

## How the org.springframework.kafka.listener.ConcurrentMessageListenerContainer is created

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.kafka.listener.ConcurrentMessageListenerContainer.<init>(ConcurrentMessageListenerContainer.java:71)
	  at org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory.createContainerInstance(ConcurrentKafkaListenerContainerFactory.java:66)
	  at org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory.createContainerInstance(ConcurrentKafkaListenerContainerFactory.java:40)
	  at org.springframework.kafka.config.AbstractKafkaListenerContainerFactory.createListenerContainer(AbstractKafkaListenerContainerFactory.java:221)
	  at org.springframework.kafka.config.AbstractKafkaListenerContainerFactory.createListenerContainer(AbstractKafkaListenerContainerFactory.java:50)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.createListenerContainer(KafkaListenerEndpointRegistry.java:183)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.registerListenerContainer(KafkaListenerEndpointRegistry.java:155)
	  - locked <0x1138> (a java.util.concurrent.ConcurrentHashMap)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.registerListenerContainer(KafkaListenerEndpointRegistry.java:129)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistrar.registerAllEndpoints(KafkaListenerEndpointRegistrar.java:138)
	  - locked <0x1139> (a java.util.ArrayList)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistrar.afterPropertiesSet(KafkaListenerEndpointRegistrar.java:132)
	  at org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor.afterSingletonsInstantiated(KafkaListenerAnnotationBeanPostProcessor.java:243)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:776)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:869)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
	  - locked <0x113a> (a java.lang.Object)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:780)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:333)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1277)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1265)
	  at huaminglin.demo.spring.boot.SpringBootDemo.main(SpringBootDemo.java:31)

## How the MessageListenerContainer is started

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.kafka.listener.KafkaMessageListenerContainer.doStart(KafkaMessageListenerContainer.java:228)
	  at org.springframework.kafka.listener.AbstractMessageListenerContainer.start(AbstractMessageListenerContainer.java:269)
	  - locked <0x1204> (a java.lang.Object)
	  at org.springframework.kafka.listener.ConcurrentMessageListenerContainer.doStart(ConcurrentMessageListenerContainer.java:164)
	  at org.springframework.kafka.listener.AbstractMessageListenerContainer.start(AbstractMessageListenerContainer.java:269)
	  - locked <0x11ca> (a java.lang.Object)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.startIfNecessary(KafkaListenerEndpointRegistry.java:289)
	  at org.springframework.kafka.config.KafkaListenerEndpointRegistry.start(KafkaListenerEndpointRegistry.java:238)
	  at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:182)
	  at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:53)
	  at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:360)
	  at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:158)
	  at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:122)
	  at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:885)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:553)
	  - locked <0x11d4> (a java.lang.Object)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:780)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:333)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1277)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1265)
	  at huaminglin.demo.spring.boot.SpringBootDemo.main(SpringBootDemo.java:31)

this.listenerConsumerFuture = containerProperties.getConsumerTaskExecutor().submitListenable(this.listenerConsumer)


## ProducerConfig values

```
2021-08-18 14:56:28.752  INFO 29214 --- [           main] o.a.k.clients.producer.ProducerConfig    : ProducerConfig values: 
   	acks = 1
   	batch.size = 16384
   	bootstrap.servers = [127.0.0.1:9092]
   	buffer.memory = 33554432
   	client.dns.lookup = use_all_dns_ips
   	client.id = producer-1
   	compression.type = none
   	connections.max.idle.ms = 540000
   	delivery.timeout.ms = 120000
   	enable.idempotence = false
   	interceptor.classes = []
   	internal.auto.downgrade.txn.commit = true
   	key.serializer = class org.apache.kafka.common.serialization.StringSerializer
   	linger.ms = 0
   	max.block.ms = 60000
   	max.in.flight.requests.per.connection = 5
   	max.request.size = 1048576
   	metadata.max.age.ms = 300000
   	metadata.max.idle.ms = 300000
   	metric.reporters = []
   	metrics.num.samples = 2
   	metrics.recording.level = INFO
   	metrics.sample.window.ms = 30000
   	partitioner.class = class org.apache.kafka.clients.producer.internals.DefaultPartitioner
   	receive.buffer.bytes = 32768
   	reconnect.backoff.max.ms = 1000
   	reconnect.backoff.ms = 50
   	request.timeout.ms = 30000
   	retries = 2147483647
   	retry.backoff.ms = 100
   	sasl.client.callback.handler.class = null
   	sasl.jaas.config = null
   	sasl.kerberos.kinit.cmd = /usr/bin/kinit
   	sasl.kerberos.min.time.before.relogin = 60000
   	sasl.kerberos.service.name = null
   	sasl.kerberos.ticket.renew.jitter = 0.05
   	sasl.kerberos.ticket.renew.window.factor = 0.8
   	sasl.login.callback.handler.class = null
   	sasl.login.class = null
   	sasl.login.refresh.buffer.seconds = 300
   	sasl.login.refresh.min.period.seconds = 60
   	sasl.login.refresh.window.factor = 0.8
   	sasl.login.refresh.window.jitter = 0.05
   	sasl.mechanism = GSSAPI
   	security.protocol = PLAINTEXT
   	security.providers = null
   	send.buffer.bytes = 131072
   	ssl.cipher.suites = null
   	ssl.enabled.protocols = [TLSv1.2, TLSv1.3]
   	ssl.endpoint.identification.algorithm = https
   	ssl.engine.factory.class = null
   	ssl.key.password = null
   	ssl.keymanager.algorithm = SunX509
   	ssl.keystore.location = null
   	ssl.keystore.password = null
   	ssl.keystore.type = JKS
   	ssl.protocol = TLSv1.3
   	ssl.provider = null
   	ssl.secure.random.implementation = null
   	ssl.trustmanager.algorithm = PKIX
   	ssl.truststore.location = null
   	ssl.truststore.password = null
   	ssl.truststore.type = JKS
   	transaction.timeout.ms = 60000
   	transactional.id = null
   	value.serializer = class org.apache.kafka.common.serialization.StringSerializer
```
