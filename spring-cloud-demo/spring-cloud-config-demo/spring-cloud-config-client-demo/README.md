mvn spring-boot:run

http://localhost:8080/

## Fetch new config from Config Server
curl -X POST http://localhost:8080/actuator/refresh
