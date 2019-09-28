mvn clean package
mvn exec:java -Dexec.mainClass="huaminglin.demo.redis.jedis.JedisDemo"
mvn exec:java -Dexec.mainClass="huaminglin.demo.redis.redisson.RedissonDemo"

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 message1

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 message2

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 quit