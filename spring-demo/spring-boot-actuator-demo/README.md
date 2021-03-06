mvn -Dserver.address=0.0.0.0 -Dserver.port=8080  spring-boot:run

docker rmi huaminglin/spring-boot-actuator-demo:1.0-SNAPSHOT
mvn dockerfile:build
docker run -p 8080:8080 --rm huaminglin/spring-boot-actuator-demo:1.0-SNAPSHOT

Since Spring Boot relies on Spring Security’s defaults, CSRF protection is turned on by default.
This means that the actuator endpoints that require a POST (shutdown and loggers endpoints), PUT or DELETE will get a 403 forbidden error when the default security configuration is in use.

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

#######################################################
## sessions
http://127.0.0.1:8080/hello/session
http://127.0.0.1:8080/actuator/sessions?username=admin
http://127.0.0.1:8080/actuator/sessions/788681e5-d76f-49ca-bbe8-195f80ac3227
http://127.0.0.1:8080/h2-console
TODO: check h2-console username and password

#######################################################
## threaddump
POST http://127.0.0.1:8080/actuator/shutdown

#######################################################
## threaddump
http://127.0.0.1:8080/actuator/threaddump
