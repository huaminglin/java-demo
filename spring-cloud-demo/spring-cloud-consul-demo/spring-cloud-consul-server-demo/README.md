mvn -Dserver.address=0.0.0.0 -Dserver.port=8080  spring-boot:run

###################################################
## spring.application.name is required
Consul service ids must not be empty, must start with a letter, end with a letter or digit, and have as interior characters only letters, digits, and hyphen.

###################################################
## spring-cloud-consul-server-demo is registered in Consul
http://127.0.0.1:8500/ui/dc1/services

2019-10-02 08:55:21.175  INFO 11165 --- [           main] o.s.c.c.s.ConsulServiceRegistry          : Registering service with consul: NewService{id='spring-cloud-consul-server-demo-8080', name='spring-cloud-consul-server-demo', tags=[secure=false], address='x.x.x.x', meta=null, port=8080, enableTagOverride=null, check=Check{script='null', interval='10s', ttl='null', http='http://x.x.x.x:8080/actuator/health', method='null', header={}, tcp='null', timeout='null', deregisterCriticalServiceAfter='null', tlsSkipVerify=null, status='null'}, checks=null}

###################################################
## Disable the service registration
mvn -Dserver.address=0.0.0.0 -Dserver.port=8080 -Dspring.cloud.consul.discovery.register=false spring-boot:run

###################################################
## spring-boot-starter-actuator and management.server.port

http://127.0.0.1:4452/actuator/health

2019-10-03 03:29:12.300  INFO 24454 --- [           main] o.s.c.c.s.ConsulServiceRegistry          : Registering service with consul: NewService{id='spring-cloud-consul-server-demo-8080', name='spring-cloud-consul-server-demo', tags=[secure=false], address='x.x.x.x', meta=null, port=8080, enableTagOverride=null, check=Check{script='null', interval='10s', ttl='null', http='http://x.x.x.x:4452/actuator/health', method='null', header={}, tcp='null', timeout='null', deregisterCriticalServiceAfter='null', tlsSkipVerify=null, status='null'}, checks=null}
2019-10-03 03:29:12.357  INFO 24454 --- [           main] o.s.c.c.s.ConsulServiceRegistry          : Registering service with consul: NewService{id='spring-cloud-consul-server-demo-8080-management', name='spring-cloud-consul-server-demo-management', tags=[management], address='x.x.x.x', meta=null, port=4452, enableTagOverride=null, check=Check{script='null', interval='10s', ttl='null', http='http://x.x.x.x:4452/actuator/health', method='null', header={}, tcp='null', timeout='null', deregisterCriticalServiceAfter='null', tlsSkipVerify=null, status='null'}, checks=null}
