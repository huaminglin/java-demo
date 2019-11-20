## mvn spring-boot:run

## Use local git as datasource
http://localhost:8888/config/client1/dev
http://localhost:8888/config/client1-dev.properties

## Use local filesystem as datasource
http://localhost:8080/client1/dev

## Setup a reverse proxy to track the client requests
docker run --network host --rm -it mitmproxy/mitmproxy mitmweb --mode reverse:http://127.0.0.1:8888 --listen-host 0.0.0.0 --listen-port 38080 --no-web-open-browser --web-iface 0.0.0.0

Web server listening at http://0.0.0.0:8081/
Proxy server listening at http://*:38080

## Submit a change and verify the change is avaialbe from HTTP API
git clone /home/user1/spring-cloud-config-git
Update and Commit
git push origin HEAD:master
http://localhost:8888/config/client1-dev.properties


## spring-cloud-starter-bus-amqp
On spring-cloud-config-server-demon startup, a queue is created on MQ server:
http://127.0.0.1:15672/#/queues/%2F/springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg

2019-11-19 00:49:52.597  INFO 24848 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 22 endpoint(s) beneath base path '/actuator'
2019-11-19 00:49:52.806  INFO 24848 --- [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'application-1.springCloudBusInput' has 1 subscriber(s).
2019-11-19 00:49:53.081  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel springCloudBusOutput
2019-11-19 00:49:53.243  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel nullChannel
2019-11-19 00:49:53.291  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel errorChannel
2019-11-19 00:49:53.377  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel springCloudBusInput
2019-11-19 00:49:53.405  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageHandler org.springframework.cloud.stream.binding.StreamListenerMessageHandler@42f842c0
2019-11-19 00:49:53.554  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageHandler errorLogger
2019-11-19 00:49:53.658  INFO 24848 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
2019-11-19 00:49:53.659  INFO 24848 --- [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'application-1.errorChannel' has 1 subscriber(s).
2019-11-19 00:49:53.660  INFO 24848 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger
2019-11-19 00:49:54.130  INFO 24848 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [127.0.0.1:5672]
2019-11-19 00:49:54.253  INFO 24848 --- [           main] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#447e827d:0/SimpleConnection@a69db [delegate=amqp://guest@127.0.0.1:5672/, localPort= 44804]
2019-11-19 00:49:54.317  INFO 24848 --- [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'application-1.springCloudBusOutput' has 1 subscriber(s).
2019-11-19 00:49:54.375  INFO 24848 --- [           main] c.s.b.r.p.RabbitExchangeQueueProvisioner : declaring queue for inbound: springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg, bound to: springCloudBus
2019-11-19 00:49:54.415  INFO 24848 --- [           main] o.s.i.monitor.IntegrationMBeanExporter   : Registering MessageChannel springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg.errors
2019-11-19 00:49:54.560  INFO 24848 --- [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'application-1.springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg.errors' has 1 subscriber(s).
2019-11-19 00:49:54.561  INFO 24848 --- [           main] o.s.c.stream.binder.BinderErrorChannel   : Channel 'application-1.springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg.errors' has 2 subscriber(s).
2019-11-19 00:49:54.596  INFO 24848 --- [           main] o.s.i.a.i.AmqpInboundChannelAdapter      : started inbound.springCloudBus.anonymous.0COldWAzQpmVr_tgWHy3Lg
2019-11-19 00:49:54.713  INFO 24848 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8888 (http) with context path ''


curl -X POST http://localhost:8888/actuator/bus-refresh
