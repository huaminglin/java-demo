cd $(dirname "$0")

sudo docker rm javademo

sudo docker run --name javademo -it -v ${PWD}/target/classes:/app -p 9191:9191 --workdir /app adoptopenjdk/openjdk11 java huaminglin.demo.io.socket.server.SocketServerBindDemo 60
