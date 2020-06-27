## Demo jedis

mvn clean package

mvn exec:java -Dexec.mainClass="huaminglin.demo.redis.jedis.JedisDemo"

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 message1

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 message2

docker exec redis-demo_server_1 redis-cli PUBLISH channel1 quit

## Output

```log
demoInteger()
11
demoFloat()
1231.23456789123449995
demoString()
value1
demoBytes()
15DB659B
7A7A7A7A
demoList()
item2
demoSet()
[item2, item1]
demoHash()
{item2=value2, item1=value1}
demoSortedSet()
[item3, item1]
demoPipeline()
[item3, item2]
demoTransaction()
[0, 0]
demoCas()
null
demoSubscriber()
Subscribe channel on main thread, wait for the message "quit" to exit.
```
