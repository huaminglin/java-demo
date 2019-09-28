mvn -Dserver.address=0.0.0.0 spring-boot:run
#######################################################
## audit
http://127.0.0.1:8080/actuator/auditevents
Authorization: Basic YWRtaW46YWRtaW4=

http://127.0.0.1:8080/actuator/auditevents?after=2019-09-28T06:23:23.986Z

#######################################################
## beans
http://127.0.0.1:8080/actuator/beans

#######################################################
## caches
http://127.0.0.1:8080/actuator/caches
{
    "cacheManagers": {
        "cacheManager": {
            "caches": {
                "ids": {
                    "target": "java.util.concurrent.ConcurrentHashMap"
                }
            }
        }
    }
}

http://127.0.0.1:8080/actuator/caches/ids
{
    "target": "java.util.concurrent.ConcurrentHashMap",
    "name": "ids",
    "cacheManager": "cacheManager"
}

curl --user admin:admin 'http://127.0.0.1:8080/actuator/caches' -i -X DELETE

#######################################################
## conditions
http://127.0.0.1:8080/actuator/conditions

#######################################################
## configprops
http://127.0.0.1:8080/actuator/configprops

#######################################################
## env
http://127.0.0.1:8080/actuator/env

#######################################################
## health
http://127.0.0.1:8080/actuator/health

#######################################################
## heapdump
http://127.0.0.1:8080/actuator/heapdump

#######################################################
## httptrace
http://127.0.0.1:8080/actuator/httptrace

#######################################################
## info
http://127.0.0.1:8080/actuator/info

#######################################################
## Loggers
Retrieving All Loggers
http://127.0.0.1:8080/actuator/loggers

Setting a Log Level
POST http://127.0.0.1:8080/actuator/loggers/huaminglin.demo.spring.boot.SpringBootActuatorDemo
"Content-Type": "application/json"
{ "configuredLevel": "TRACE" }

Clearing a Log Level
POST http://127.0.0.1:8080/actuator/loggers/huaminglin.demo.spring.boot.SpringBootActuatorDemo
"Content-Type": "application/json"
{}

#######################################################
## mappings
http://127.0.0.1:8080/actuator/mappings

#######################################################
## metrics
http://127.0.0.1:8080/actuator/metrics

http://127.0.0.1:8080/actuator/metrics/jvm.memory.max

#######################################################
## prometheus
http://127.0.0.1:8080/actuator/prometheus

#######################################################
## scheduledtasks
http://127.0.0.1:8080/actuator/scheduledtasks
