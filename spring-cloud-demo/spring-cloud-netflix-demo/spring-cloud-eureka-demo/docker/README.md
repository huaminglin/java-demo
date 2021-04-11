#  Spring Cloud Eureka Demo

## Eureka Server

http://localhost:8080/


## Eureka client

http://localhost:9080/service-instances/EUREKA-CLIENT

```
[
{
"scheme": "http",
"host": "eureka-client01",
"port": 9080,
"serviceId": "EUREKA-CLIENT",
"uri": "http://eureka-client01:9080",
"instanceInfo": {
"instanceId": "ad4edd8a627b:eureka-client:9080",
"app": "EUREKA-CLIENT",
"appGroupName": null,
"ipAddr": "172.28.0.4",
"sid": "na",
"homePageUrl": "http://eureka-client01:9080/",
"statusPageUrl": "http://eureka-client01:9080/actuator/info",
"healthCheckUrl": "http://eureka-client01:9080/actuator/health",
"secureHealthCheckUrl": null,
"vipAddress": "eureka-client",
"secureVipAddress": "eureka-client",
"countryId": 1,
"dataCenterInfo": {
"@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
"name": "MyOwn"
},
"hostName": "eureka-client01",
"status": "UP",
"overriddenStatus": "UNKNOWN",
"leaseInfo": {
"renewalIntervalInSecs": 30,
"durationInSecs": 90,
"registrationTimestamp": 1618157024898,
"lastRenewalTimestamp": 1618157144790,
"evictionTimestamp": 0,
"serviceUpTimestamp": 1618157024898
},
"isCoordinatingDiscoveryServer": false,
"metadata": {
"management.port": "9080"
},
"lastUpdatedTimestamp": 1618157024898,
"lastDirtyTimestamp": 1618157024783,
"actionType": "ADDED",
"asgName": null
},
"secure": false,
"instanceId": "ad4edd8a627b:eureka-client:9080",
"metadata": {
"management.port": "9080"
}
}
]
```

## URLs

http://localhost:8080/

http://localhost:9080/service-instances/EUREKA-PRODUCER

http://localhost:10080/name

http://localhost:10180/fetch

```
eureka-client01_1    | 2021-04-11 16:31:04.365  INFO 1 --- [tbeatExecutor-0] c.n.d.s.t.d.RedirectingEurekaHttpClient  : Request execution error. endpoint=DefaultEndpoint{ serviceUrl='http://eureka-server01:8080/eureka/}, exception=I/O error on PUT request for "http://eureka-server01:8080/eureka/apps/EUREKA-CLIENT/bcd58755a702:eureka-client:9080": eureka-server01: Temporary failure in name resolution; nested exception is java.net.UnknownHostException: eureka-server01: Temporary failure in name resolution stacktrace=org.springframework.web.client.ResourceAccessException: I/O error on PUT request for "http://eureka-server01:8080/eureka/apps/EUREKA-CLIENT/bcd58755a702:eureka-client:9080": eureka-server01: Temporary failure in name resolution; nested exception is java.net.UnknownHostException: eureka-server01: Temporary failure in name resolution
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:785)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:711)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.exchange(RestTemplate.java:602)
eureka-client01_1    |  at org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient.sendHeartBeat(RestTemplateEurekaHttpClient.java:99)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.executeOnNewServer(RedirectingEurekaHttpClient.java:121)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.execute(RedirectingEurekaHttpClient.java:80)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:120)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.renew(DiscoveryClient.java:893)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient$HeartbeatThread.run(DiscoveryClient.java:1457)
eureka-client01_1    |  at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.FutureTask.run(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
eureka-client01_1    |  at java.base/java.lang.Thread.run(Unknown Source)
eureka-client01_1    | Caused by: java.net.UnknownHostException: eureka-server01: Temporary failure in name resolution
eureka-client01_1    |  at java.base/java.net.Inet4AddressImpl.lookupAllHostAddr(Native Method)
eureka-client01_1    |  at java.base/java.net.InetAddress$PlatformNameService.lookupAllHostAddr(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAddressesFromNameService(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress$NameServiceAddresses.get(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName0(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName(Unknown Source)
eureka-client01_1    |  at org.apache.http.impl.conn.SystemDefaultDnsResolver.resolve(SystemDefaultDnsResolver.java:45)
eureka-client01_1    |  at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:112)
eureka-client01_1    |  at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:376)
eureka-client01_1    |  at org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:393)
eureka-client01_1    |  at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:236)
eureka-client01_1    |  at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186)
eureka-client01_1    |  at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89)
eureka-client01_1    |  at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110)
eureka-client01_1    |  at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)
eureka-client01_1    |  at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)
eureka-client01_1    |  at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56)
eureka-client01_1    |  at org.springframework.http.client.HttpComponentsClientHttpRequest.executeInternal(HttpComponentsClientHttpRequest.java:87)
eureka-client01_1    |  at org.springframework.http.client.AbstractBufferingClientHttpRequest.executeInternal(AbstractBufferingClientHttpRequest.java:48)
eureka-client01_1    |  at org.springframework.http.client.AbstractClientHttpRequest.execute(AbstractClientHttpRequest.java:66)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:776)
eureka-client01_1    |  ... 20 more
eureka-client01_1    | 
eureka-client01_1    | 2021-04-11 16:31:04.365  WARN 1 --- [tbeatExecutor-0] c.n.d.s.t.d.RetryableEurekaHttpClient    : Request execution failed with message: I/O error on PUT request for "http://eureka-server01:8080/eureka/apps/EUREKA-CLIENT/bcd58755a702:eureka-client:9080": eureka-server01: Temporary failure in name resolution; nested exception is java.net.UnknownHostException: eureka-server01: Temporary failure in name resolution
eureka-client01_1    | 2021-04-11 16:31:04.366 ERROR 1 --- [tbeatExecutor-0] com.netflix.discovery.DiscoveryClient    : DiscoveryClient_EUREKA-CLIENT/bcd58755a702:eureka-client:9080 - was unable to send heartbeat!
eureka-client01_1    | 
eureka-client01_1    | com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:112) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$3.execute(EurekaHttpClientDecorator.java:92) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.sendHeartBeat(EurekaHttpClientDecorator.java:89) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.renew(DiscoveryClient.java:893) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient$HeartbeatThread.run(DiscoveryClient.java:1457) ~[eureka-client-1.10.11.jar!/:1.10.11]
eureka-client01_1    |  at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Unknown Source) ~[na:na]
eureka-client01_1    |  at java.base/java.util.concurrent.FutureTask.run(Unknown Source) ~[na:na]
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source) ~[na:na]
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source) ~[na:na]
eureka-client01_1    |  at java.base/java.lang.Thread.run(Unknown Source) ~[na:na]
eureka-client01_1    | 
eureka-client01_1    | 2021-04-11 16:31:04.367  INFO 1 --- [freshExecutor-0] c.n.d.s.t.d.RedirectingEurekaHttpClient  : Request execution error. endpoint=DefaultEndpoint{ serviceUrl='http://eureka-server01:8080/eureka/}, exception=I/O error on GET request for "http://eureka-server01:8080/eureka/apps/delta": eureka-server01; nested exception is java.net.UnknownHostException: eureka-server01 stacktrace=org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://eureka-server01:8080/eureka/apps/delta": eureka-server01; nested exception is java.net.UnknownHostException: eureka-server01
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:785)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:711)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.exchange(RestTemplate.java:602)
eureka-client01_1    |  at org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient.getApplicationsInternal(RestTemplateEurekaHttpClient.java:145)
eureka-client01_1    |  at org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient.getDelta(RestTemplateEurekaHttpClient.java:155)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$7.execute(EurekaHttpClientDecorator.java:152)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.executeOnNewServer(RedirectingEurekaHttpClient.java:121)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.execute(RedirectingEurekaHttpClient.java:80)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getDelta(EurekaHttpClientDecorator.java:149)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$7.execute(EurekaHttpClientDecorator.java:152)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:120)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getDelta(EurekaHttpClientDecorator.java:149)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$7.execute(EurekaHttpClientDecorator.java:152)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getDelta(EurekaHttpClientDecorator.java:149)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.getAndUpdateDelta(DiscoveryClient.java:1135)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.fetchRegistry(DiscoveryClient.java:1016)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.refreshRegistry(DiscoveryClient.java:1531)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient$CacheRefreshThread.run(DiscoveryClient.java:1498)
eureka-client01_1    |  at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.FutureTask.run(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
eureka-client01_1    |  at java.base/java.lang.Thread.run(Unknown Source)
eureka-client01_1    | Caused by: java.net.UnknownHostException: eureka-server01
eureka-client01_1    |  at java.base/java.net.InetAddress$CachedAddresses.get(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName0(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName(Unknown Source)
eureka-client01_1    |  at java.base/java.net.InetAddress.getAllByName(Unknown Source)
eureka-client01_1    |  at org.apache.http.impl.conn.SystemDefaultDnsResolver.resolve(SystemDefaultDnsResolver.java:45)
eureka-client01_1    |  at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:112)
eureka-client01_1    |  at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:376)
eureka-client01_1    |  at org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:393)
eureka-client01_1    |  at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:236)
eureka-client01_1    |  at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186)
eureka-client01_1    |  at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89)
eureka-client01_1    |  at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110)
eureka-client01_1    |  at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)
eureka-client01_1    |  at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)
eureka-client01_1    |  at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56)
eureka-client01_1    |  at org.springframework.http.client.HttpComponentsClientHttpRequest.executeInternal(HttpComponentsClientHttpRequest.java:87)
eureka-client01_1    |  at org.springframework.http.client.AbstractBufferingClientHttpRequest.executeInternal(AbstractBufferingClientHttpRequest.java:48)
eureka-client01_1    |  at org.springframework.http.client.AbstractClientHttpRequest.execute(AbstractClientHttpRequest.java:66)
eureka-client01_1    |  at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:776)
eureka-client01_1    |  ... 23 more
eureka-client01_1    | 
eureka-client01_1    | 2021-04-11 16:31:04.367  WARN 1 --- [freshExecutor-0] c.n.d.s.t.d.RetryableEurekaHttpClient    : Request execution failed with message: I/O error on GET request for "http://eureka-server01:8080/eureka/apps/delta": eureka-server01; nested exception is java.net.UnknownHostException: eureka-server01
eureka-client01_1    | 2021-04-11 16:31:04.367  INFO 1 --- [freshExecutor-0] com.netflix.discovery.DiscoveryClient    : DiscoveryClient_EUREKA-CLIENT/bcd58755a702:eureka-client:9080 - was unable to refresh its cache! This periodic background refresh will be retried in 30 seconds. status = Cannot execute request on any known server stacktrace = com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:112)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getDelta(EurekaHttpClientDecorator.java:149)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$7.execute(EurekaHttpClientDecorator.java:152)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77)
eureka-client01_1    |  at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getDelta(EurekaHttpClientDecorator.java:149)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.getAndUpdateDelta(DiscoveryClient.java:1135)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.fetchRegistry(DiscoveryClient.java:1016)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient.refreshRegistry(DiscoveryClient.java:1531)
eureka-client01_1    |  at com.netflix.discovery.DiscoveryClient$CacheRefreshThread.run(DiscoveryClient.java:1498)
eureka-client01_1    |  at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.FutureTask.run(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
eureka-client01_1    |  at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
eureka-client01_1    |  at java.base/java.lang.Thread.run(Unknown Source)
eureka-client01_1    | 
```

After "docker stop docker_eureka-server01_1", the other services use their local cache data to provide service.

com.netflix.discovery.DiscoveryClient: "was unable to send heartbeat!"

com.netflix.discovery.DiscoveryClient: "was unable to refresh its cache!"
