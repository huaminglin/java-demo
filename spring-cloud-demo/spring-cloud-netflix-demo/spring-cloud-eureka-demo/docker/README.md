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
