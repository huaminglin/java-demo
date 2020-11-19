#  Spring Cloud Eureka Demo

## Eureka Server

http://localhost:8080/


## Eureka client

http://localhost:9090/service-instances/EUREKA-CLIENT

```
[
  {
    "host": "localhost",
    "port": 9080,
    "instanceId": "090bb94c38d6:eureka-client:9080",
    "serviceId": "EUREKA-CLIENT",
    "instanceInfo": {
      "instanceId": "090bb94c38d6:eureka-client:9080",
      "app": "EUREKA-CLIENT",
      "appGroupName": null,
      "ipAddr": "172.28.0.2",
      "sid": "na",
      "homePageUrl": "http://localhost:9080/",
      "statusPageUrl": "http://localhost:9080/actuator/info",
      "healthCheckUrl": "http://localhost:9080/actuator/health",
      "secureHealthCheckUrl": null,
      "vipAddress": "eureka-client",
      "secureVipAddress": "eureka-client",
      "countryId": 1,
      "dataCenterInfo": {
        "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
        "name": "MyOwn"
      },
      "hostName": "localhost",
      "status": "UP",
      "overriddenStatus": "UNKNOWN",
      "leaseInfo": {
        "renewalIntervalInSecs": 30,
        "durationInSecs": 90,
        "registrationTimestamp": 1605802483579,
        "lastRenewalTimestamp": 1605802603595,
        "evictionTimestamp": 0,
        "serviceUpTimestamp": 1605802483579
      },
      "isCoordinatingDiscoveryServer": false,
      "metadata": {
        "management.port": "9080"
      },
      "lastUpdatedTimestamp": 1605802483580,
      "lastDirtyTimestamp": 1605802483505,
      "actionType": "ADDED",
      "asgName": null
    },
    "metadata": {
      "management.port": "9080"
    },
    "secure": false,
    "uri": "http://localhost:9080",
    "scheme": null
  }
]
```
