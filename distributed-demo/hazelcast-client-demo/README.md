# Demo Hazelcast Client: how to access a Hazelcast cluster running inside Docker container

## com.hazelcast.config.AbstractConfigLocator

java.lang.NoClassDefFoundError: com/hazelcast/config/AbstractConfigLocator

```stack
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:247)
	  at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:363)
	  at org.springframework.boot.autoconfigure.hazelcast.HazelcastClientConfiguration$HazelcastClientConfigFileConfiguration$$EnhancerBySpringCGLIB$$50c7e95d.hazelcastInstance(<generated>:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(NativeMethodAccessorImpl.java:-1)
	  at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	  at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	  at java.lang.reflect.Method.invoke(Method.java:566)
	  at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:622)
	  at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:607)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1321)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1160)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555)
	  at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$118.1523553211.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  - locked <0xde6> (a java.util.concurrent.ConcurrentHashMap)
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
	  at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$118.1523553211.getObject(Unknown Source:-1)
	  at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
	  at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
	  at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:845)
	  at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
	  at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
	  - locked <0xde7> (a java.lang.Object)
	  at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:744)
	  at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:391)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:312)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1204)
	  at huaminglin.demo.hazelcastclient.HazelcastClientDemo.main(HazelcastClientDemo.java:11)
```

Conclusion:

hazelcast-all has com.hazelcast.config.AbstractConfigLocator, but hazelcast-client doesn't.

## When host name "server01" is not resolvable

```log
com.hazelcast.core.HazelcastException: java.net.UnknownHostException: server01: Name or service not known
	at com.hazelcast.client.impl.protocol.codec.builtin.CustomTypeFactory.createAddress(CustomTypeFactory.java:54) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.codec.custom.AddressCodec.decode(AddressCodec.java:58) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.codec.builtin.CodecUtil.decodeNullable(CodecUtil.java:55) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.codec.ClientAuthenticationCodec.decodeResponse(ClientAuthenticationCodec.java:218) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.authenticateOnCluster(ClientConnectionManagerImpl.java:777) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.getOrConnect(ClientConnectionManagerImpl.java:581) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.connect(ClientConnectionManagerImpl.java:423) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.doConnectToCandidateCluster(ClientConnectionManagerImpl.java:451) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.doConnectToCluster(ClientConnectionManagerImpl.java:385) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.connectToCluster(ClientConnectionManagerImpl.java:346) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.connection.nio.ClientConnectionManagerImpl.start(ClientConnectionManagerImpl.java:296) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.clientside.HazelcastClientInstanceImpl.start(HazelcastClientInstanceImpl.java:355) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.HazelcastClient.constructHazelcastClient(HazelcastClient.java:460) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.HazelcastClient.newHazelcastClientInternal(HazelcastClient.java:416) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.HazelcastClient.newHazelcastClient(HazelcastClient.java:119) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at org.springframework.boot.autoconfigure.hazelcast.HazelcastClientConfiguration$HazelcastClientConfigFileConfiguration.hazelcastInstance(HazelcastClientConfiguration.java:57) ~[spring-boot-autoconfigure-2.1.8.RELEASE.jar:2.1.8.RELEASE]
```

```
com.hazelcast.cluster.Address.Address(java.lang.String, int)
    public Address(String host, int port) throws UnknownHostException {
        this(host, InetAddress.getByName(host), port);
    }
    public Address(String hostname, InetAddress inetAddress, int port) {
        checkNotNull(inetAddress, "inetAddress can't be null");

        type = (inetAddress instanceof Inet4Address) ? IPV4 : IPV6;
        String[] addressArgs = inetAddress.getHostAddress().split("\\%");
        host = hostname != null ? hostname : addressArgs[0];
        if (addressArgs.length == 2) {
            scopeId = addressArgs[1];
        }
        this.port = port;
        hostSet = !AddressUtil.isIpAddress(host);
    }
```

Conclusion:

From the above code, Hazelcast resolve the hostname to ip to get some metadata for the host, not to talk to it.

## Config "server01" to a non-existing ip 10.2.2.66 (Verify public port 15701 is the only access point to the Java Hazelcast client)

```
2020-07-03 18:39:27.073  INFO 6031 --- [           main] h.d.hazelcastclient.HazelcastClientDemo  : No active profile set, falling back to default profiles: default
2020-07-03 18:39:27.497  INFO 6031 --- [           main] c.h.i.config.AbstractConfigLocator       : Loading 'hazelcast-client.xml' from the classpath.
2020-07-03 18:39:27.933  INFO 6031 --- [           main] c.h.c.impl.spi.ClientInvocationService   : hz.client_1 [dev] [4.0.1] Running with 2 response threads, dynamic=true
2020-07-03 18:39:27.972  INFO 6031 --- [           main] com.hazelcast.core.LifecycleService      : hz.client_1 [dev] [4.0.1] HazelcastClient 4.0.1 (20200409 - e086b9c) is STARTING
2020-07-03 18:39:27.972  INFO 6031 --- [           main] com.hazelcast.core.LifecycleService      : hz.client_1 [dev] [4.0.1] HazelcastClient 4.0.1 (20200409 - e086b9c) is STARTED
2020-07-03 18:39:27.992  INFO 6031 --- [           main] c.h.c.i.c.ClientConnectionManager        : hz.client_1 [dev] [4.0.1] Trying to connect to cluster: dev
2020-07-03 18:39:27.995  INFO 6031 --- [           main] c.h.c.i.c.ClientConnectionManager        : hz.client_1 [dev] [4.0.1] Trying to connect to [127.0.0.1]:15701
2020-07-03 18:39:28.022  INFO 6031 --- [           main] com.hazelcast.core.LifecycleService      : hz.client_1 [dev] [4.0.1] HazelcastClient 4.0.1 (20200409 - e086b9c) is CLIENT_CONNECTED
2020-07-03 18:39:28.023  INFO 6031 --- [           main] c.h.c.i.c.ClientConnectionManager        : hz.client_1 [dev] [4.0.1] Authenticated with server [server01]:5701:ed550e70-85ce-4da6-a1ff-982f7736c8cb, server version: 4.0, local address: /127.0.0.1:57929
2020-07-03 18:39:28.025  INFO 6031 --- [           main] c.h.internal.diagnostics.Diagnostics     : hz.client_1 [dev] [4.0.1] Diagnostics disabled. To enable add -Dhazelcast.diagnostics.enabled=true to the JVM arguments.
2020-07-03 18:39:28.029  INFO 6031 --- [lient_1.event-1] c.h.c.impl.spi.ClientClusterService      : hz.client_1 [dev] [4.0.1] 

Members [2] {
	Member [server01]:5701 - ed550e70-85ce-4da6-a1ff-982f7736c8cb
	Member [172.28.0.4]:5701 - 00215dca-df8f-45ac-a9a1-d0c036a26f2c
}

2020-07-03 18:39:28.101  INFO 6031 --- [           main] c.h.c.i.s.ClientStatisticsService        : Client statistics is enabled with period 5 seconds.
2020-07-03 18:39:28.178  INFO 6031 --- [           main] h.d.hazelcastclient.HazelcastClientDemo  : Started HazelcastClientDemo in 1.395 seconds (JVM running for 1.652)
Main Thread exits: 1, main

```

Check the HMC page about Clients:

```
 hz.client_1
172.28.0.1
No
Java
server01:5701
4.0.1
1970cdf5-96e6-4bb5-9022-c346bc33ff33
```

Conclusion:

The Java Hazelcast client is registered as "172.28.0.1", and it is connected to "server01:5701" only.

Question: The Java Hazelcast client talks to "127.0.0.1:15701" or to "server01:5701" directly?

After mapping server01 to "10.2.2.66", the question is answered.

The Java Hazelcast client doesn't talk to "server01:5701" directly.

Not sure why Hazelcast needs to resolve "server01" ip address.

Verify with ss command, get the hazelcast client connection port from the log "57929":

```
ss -p | grep 57929
tcp              ESTAB                  0                   0                                                                                [::ffff:127.0.0.1]:57929                                   [::ffff:127.0.0.1]:15701                 users:(("java",pid=6031,fd=37))                                                
tcp              ESTAB                  0                   0                                                                                [::ffff:127.0.0.1]:15701                                   [::ffff:127.0.0.1]:57929                                                                                                
```

The connection is between port 57929 and 15701.

The 15701 forwards data to the hazelcast cluster as "172.28.0.1", which is the network gateway.

## Hazelcast as Message Queue

On HMC Scripting page, publish a message to shutdown the hazelcast instance (client):

hazelcast.getTopic("topic1").publish("quit")
