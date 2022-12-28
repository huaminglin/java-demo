java -jar target/spring-kafka-boot-demo-1.0-SNAPSHOT.jar

org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
org.springframework.boot.autoconfigure.kafka.ConfigurationProperties
org.springframework.boot.autoconfigure.kafka.KafkaAnnotationDrivenConfiguration

## kafka-coordinator-heartbeat-thread

```
"kafka-coordinator-heartbeat-thread | kafka-intro" #21 daemon prio=5 os_prio=0 cpu=2705.74ms elapsed=1976.02s tid=0x00007ff3641254f0 nid=0x9334 waiting on condition  [0x00007ff3c016d000]
   java.lang.Thread.State: WAITING (parking)
        at jdk.internal.misc.Unsafe.park(java.base@17.0.5/Native Method)
        - parking to wait for  <0x00000000840d29f8> (a java.util.concurrent.locks.ReentrantLock$FairSync)
        at java.util.concurrent.locks.LockSupport.park(java.base@17.0.5/LockSupport.java:211)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@17.0.5/AbstractQueuedSynchronizer.java:715)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@17.0.5/AbstractQueuedSynchronizer.java:938)
        at java.util.concurrent.locks.ReentrantLock$Sync.lock(java.base@17.0.5/ReentrantLock.java:153)
        at java.util.concurrent.locks.ReentrantLock.lock(java.base@17.0.5/ReentrantLock.java:322)
        at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.poll(ConsumerNetworkClient.java:249)
        at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.pollNoWakeup(ConsumerNetworkClient.java:306)
        at org.apache.kafka.clients.consumer.internals.AbstractCoordinator$HeartbeatThread.run(AbstractCoordinator.java:1386)
        - locked <0x0000000083e10b80> (a org.apache.kafka.clients.consumer.internals.ConsumerCoordinator)
```

## org.springframework.kafka.KafkaListenerEndpointContainer#0-0-C-1

```
"org.springframework.kafka.KafkaListenerEndpointContainer#0-0-C-1" #19 prio=5 os_prio=0 cpu=6894.69ms elapsed=1976.21s tid=0x00007ff3e0a1dcb0 nid=0x9330 runnable  [0x00007ff3c0573000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPoll.wait(java.base@17.0.5/Native Method)
        at sun.nio.ch.EPollSelectorImpl.doSelect(java.base@17.0.5/EPollSelectorImpl.java:118)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(java.base@17.0.5/SelectorImpl.java:129)
        - locked <0x0000000083fa9518> (a sun.nio.ch.Util$2)
        - locked <0x0000000083f45970> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(java.base@17.0.5/SelectorImpl.java:141)
        at org.apache.kafka.common.network.Selector.select(Selector.java:873)
        at org.apache.kafka.common.network.Selector.poll(Selector.java:465)
        at org.apache.kafka.clients.NetworkClient.poll(NetworkClient.java:560)
        at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.poll(ConsumerNetworkClient.java:265)
        at org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient.poll(ConsumerNetworkClient.java:236)
        at org.apache.kafka.clients.consumer.KafkaConsumer.pollForFetches(KafkaConsumer.java:1297)
        at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1238)
        at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1211)
        at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollConsumer(KafkaMessageListenerContainer.java:1531)
        at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doPoll(KafkaMessageListenerContainer.java:1521)
        at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1345)
        at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257)
        at java.util.concurrent.Executors$RunnableAdapter.call(java.base@17.0.5/Executors.java:539)
        at java.util.concurrent.FutureTask.run(java.base@17.0.5/FutureTask.java:264)
        at java.lang.Thread.run(java.base@17.0.5/Thread.java:833)
```

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
