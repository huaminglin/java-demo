# mvn exec:java

## Topic my-topic not present in metadata after 60000 ms

Run the producer without an active Kafka cluster.

```
[kafka-producer-network-thread | producer-myTransactionId1] WARN org.apache.kafka.clients.NetworkClient - [Producer clientId=producer-myTransactionId1, transactionalId=myTransactionId1] Bootstrap broker localhost:9092 (id: -1 rack: null) disconnected
Exception in thread "main" java.util.concurrent.ExecutionException: org.apache.kafka.common.errors.TimeoutException: Topic my-topic not present in metadata after 60000 ms.
	at org.apache.kafka.clients.producer.KafkaProducer$FutureFailure.<init>(KafkaProducer.java:1314)
	at org.apache.kafka.clients.producer.KafkaProducer.doSend(KafkaProducer.java:970)
	at org.apache.kafka.clients.producer.KafkaProducer.send(KafkaProducer.java:870)
	at org.apache.kafka.clients.producer.KafkaProducer.send(KafkaProducer.java:758)
	at huaminglin.demo.kafka.KafkaProducerDemo.main(KafkaProducerDemo.java:30)
Caused by: org.apache.kafka.common.errors.TimeoutException: Topic my-topic not present in metadata after 60000 ms.
```

## Cannot perform a 'send' before completing a call to initTransactions when transactions are enabled.

If we configure TRANSACTIONAL_ID_CONFIG, the producer is a transactional producer.

```
[main] INFO org.apache.kafka.clients.producer.ProducerConfig - ProducerConfig values: 
	acks = -1
	batch.size = 16384
	bootstrap.servers = [localhost:9092]
	buffer.memory = 33554432
	client.dns.lookup = use_all_dns_ips
	client.id = producer-myTransactionId1
	compression.type = none
	connections.max.idle.ms = 540000
	delivery.timeout.ms = 120000
	enable.idempotence = true
	interceptor.classes = []
	internal.auto.downgrade.txn.commit = false
	key.serializer = class org.apache.kafka.common.serialization.IntegerSerializer
	linger.ms = 1
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
	retries = 1
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
	transactional.id = myTransactionId1
	value.serializer = class org.apache.kafka.common.serialization.StringSerializer

[main] INFO org.apache.kafka.clients.producer.KafkaProducer - [Producer clientId=producer-myTransactionId1, transactionalId=myTransactionId1] Instantiated a transactional producer.
[main] INFO org.apache.kafka.clients.producer.KafkaProducer - [Producer clientId=producer-myTransactionId1, transactionalId=myTransactionId1] Overriding the default acks to all since idempotence is enabled.
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 2.6.0
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 62abe01bee039651
[main] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1632033963267
[kafka-producer-network-thread | producer-myTransactionId1] INFO org.apache.kafka.clients.Metadata - [Producer clientId=producer-myTransactionId1, transactionalId=myTransactionId1] Cluster ID: 0MSnns6IQWOAX6RK1dkpag
Exception in thread "main" java.lang.IllegalStateException: Cannot perform a 'send' before completing a call to initTransactions when transactions are enabled.
	at org.apache.kafka.clients.producer.internals.TransactionManager.failIfNotReadyForSend(TransactionManager.java:432)
	at org.apache.kafka.clients.producer.KafkaProducer.doSend(KafkaProducer.java:933)
	at org.apache.kafka.clients.producer.KafkaProducer.send(KafkaProducer.java:870)
	at org.apache.kafka.clients.producer.KafkaProducer.send(KafkaProducer.java:758)
	at huaminglin.demo.kafka.KafkaProducerDemo.main(KafkaProducerDemo.java:30)

Process finished with exit code 1
```

Note: "Overriding the default acks to all since idempotence is enabled.".

Note: "Instantiated a transactional producer", and initTransactions is required.

