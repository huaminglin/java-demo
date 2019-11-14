## mvn spring-boot:run

## Use local git as datasource
http://localhost:8888/config/client1/dev
http://localhost:8888/config/client1-dev.properties

## Use local filesystem as datasource
http://localhost:8080/client1/dev

## Setup a reverse proxy to track the client requests
docker run --network host --rm -it mitmproxy/mitmproxy mitmweb --mode reverse:http://127.0.0.1:8888 --listen-host 0.0.0.0 --listen-port 38080 --no-web-open-browser --web-iface 0.0.0.0

Web server listening at http://0.0.0.0:8081/
Proxy server listening at http://*:38080

## Submit a change and verify the change is avaialbe from HTTP API
git clone /home/user1/spring-cloud-config-git
Update and Commit
git push origin HEAD:master
http://localhost:8888/config/client1-dev.properties
