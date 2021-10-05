java -jar target/spring-kafka-boot-producer-demo-1.0-SNAPSHOT.jar

org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
org.springframework.boot.autoconfigure.kafka.ConfigurationProperties
org.springframework.boot.autoconfigure.kafka.KafkaAnnotationDrivenConfiguration


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

## jackson-databind is required

It's confusing that we have to declare this dependency explicitly.

```

2021-09-09 00:37:51.916 ERROR 36207 --- [ad | producer-1] o.apache.kafka.common.utils.KafkaThread  : Uncaught exception in thread 'kafka-producer-network-thread | producer-1':

java.lang.NoClassDefFoundError: com/fasterxml/jackson/databind/JsonNode
	at org.apache.kafka.common.requests.ApiVersionsRequest$Builder.<clinit>(ApiVersionsRequest.java:36) ~[kafka-clients-2.6.0.jar:na]
	at org.apache.kafka.clients.NetworkClient.handleConnections(NetworkClient.java:910) ~[kafka-clients-2.6.0.jar:na]
	at org.apache.kafka.clients.NetworkClient.poll(NetworkClient.java:555) ~[kafka-clients-2.6.0.jar:na]
	at org.apache.kafka.clients.producer.internals.Sender.runOnce(Sender.java:325) ~[kafka-clients-2.6.0.jar:na]
	at org.apache.kafka.clients.producer.internals.Sender.run(Sender.java:240) ~[kafka-clients-2.6.0.jar:na]
	at java.base/java.lang.Thread.run(Thread.java:829) ~[na:na]
Caused by: java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.JsonNode
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:581) ~[na:na]
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178) ~[na:na]
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522) ~[na:na]
	... 6 common frames omitted
```

## org.apache.kafka.common.utils.KafkaThread

```
	at java.base/java.lang.Thread.&lt;init&gt;(Thread.java:456)
	at java.base/java.lang.Thread.&lt;init&gt;(Thread.java:709)
	at java.base/java.lang.Thread.&lt;init&gt;(Thread.java:582)
	at org.apache.kafka.common.utils.KafkaThread.<init>(KafkaThread.java:43)
	at org.apache.kafka.clients.producer.KafkaProducer.&lt;init&gt;(KafkaProducer.java:432)
	at org.apache.kafka.clients.producer.KafkaProducer.&lt;init&gt;(KafkaProducer.java:290)
	at org.springframework.kafka.core.DefaultKafkaProducerFactory.createRawProducer(DefaultKafkaProducerFactory.java:743)
	at org.springframework.kafka.core.DefaultKafkaProducerFactory.createKafkaProducer(DefaultKafkaProducerFactory.java:584)
	at org.springframework.kafka.core.DefaultKafkaProducerFactory.doCreateProducer(DefaultKafkaProducerFactory.java:544)
	at org.springframework.kafka.core.DefaultKafkaProducerFactory.createProducer(DefaultKafkaProducerFactory.java:519)
	at org.springframework.kafka.core.DefaultKafkaProducerFactory.createProducer(DefaultKafkaProducerFactory.java:513)
	at org.springframework.kafka.core.KafkaTemplate.getTheProducer(KafkaTemplate.java:666)
	at org.springframework.kafka.core.KafkaTemplate.doSend(KafkaTemplate.java:552)
	at org.springframework.kafka.core.KafkaTemplate.send(KafkaTemplate.java:363)
	at huaminglin.demo.spring.boot.SpringBootProducerDemo.main(SpringBootProducerDemo.java:25)
```
