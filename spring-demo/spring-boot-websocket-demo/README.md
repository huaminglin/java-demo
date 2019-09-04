mvn spring-boot:run

java -jar target/spring-boot-websocket-demo-1.0-SNAPSHOT.jar

mvn dockerfile:build
docker run -p 8080:8080 --rm huaminglin/spring-boot-websocket-demo:1.0-SNAPSHOT

docker run -p 8080:8080 --rm -v $PWD/target/spring-boot-websocket-demo-1.0-SNAPSHOT.jar:/app.jar --entrypoint=/usr/bin/java openjdk:8-jdk-alpine -Djava.security.egd=file:/dev/./urandom -jar /app.jar
