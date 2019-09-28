mvn -Dserver.address=0.0.0.0 spring-boot:run

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
## audit
http://127.0.0.1:8080/actuator/auditevents
Authorization: Basic YWRtaW46YWRtaW4=

http://127.0.0.1:8080/actuator/auditevents?after=2019-09-28T06:23:23.986Z

#######################################################
## beans
http://127.0.0.1:8080/actuator/beans
