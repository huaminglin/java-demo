mvn -Dserver.address=0.0.0.0 -Dserver.port=8080  spring-boot:run

###################################################
## spring.application.name is required
Consul service ids must not be empty, must start with a letter, end with a letter or digit, and have as interior characters only letters, digits, and hyphen.

###################################################
## spring-cloud-consul-demo is registered in Consul
http://127.0.0.1:8500/ui/dc1/services
