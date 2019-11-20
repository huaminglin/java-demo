mvn spring-boot:run

## Fetch config from server
"http://127.0.0.1:8888/config/client1/default" is triggered on startup to fetch config from server.
http://localhost:8080/

## Fetch new config from Config Server
curl -X POST http://localhost:8080/actuator/refresh

## spring-cloud-starter-bus-amqp
2019-11-19 00:38:33.207  INFO 22115 --- [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'client1-1.springCloudBusInput' has 1 subscriber(s).
2019-11-19 00:38:33.463  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel springCloudBusOutput
2019-11-19 00:38:33.587  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel springCloudBusInput
2019-11-19 00:38:33.611  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel nullChannel
2019-11-19 00:38:33.649  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel errorChannel
2019-11-19 00:38:33.718  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageHandler org.springframework.cloud.stream.binding.StreamListenerMessageHandler@333a9961
2019-11-19 00:38:33.813  INFO 22115 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageHandler errorLogger
2019-11-19 00:38:33.876  INFO 22115 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
2019-11-19 00:38:33.877  INFO 22115 --- [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'client1-1.errorChannel' has 1 subscriber(s).
2019-11-19 00:38:33.877  INFO 22115 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger
