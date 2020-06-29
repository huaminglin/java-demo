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
1354.35802468035794999
demoString()
value1
demoBytes()
15DB659B
7A7A7A7A
demoList()
item1
demoSet()
[item2, item1]
demoHash()
{item2=value2, item1=value1}
demoSortedSet()
[item3, item1]
demoPipeline()
8
8
demoTransaction()
[0, 0]
demoCas()
null
demoSubscriber()
Subscribe channel on main thread, wait for the message "quit" to exit.
```

## redis-demo/monitor.sh

OK
xxx.882233 [0 172.21.0.1:39460] "INCR" "int1"
xxx.882706 [0 172.21.0.1:39460] "GET" "int1"
xxx.884794 [0 172.21.0.1:39460] "INCRBYFLOAT" "float1" "123.12345678912345"
xxx.885634 [0 172.21.0.1:39460] "GET" "float1"
xxx.886044 [0 172.21.0.1:39460] "SET" "str1" "value1"
xxx.886309 [0 172.21.0.1:39460] "GET" "str1"
xxx.886496 [0 172.21.0.1:39460] "SET" "\x15\xdbe\x9b" "\x15\xdbe\x9b"
xxx.886644 [0 172.21.0.1:39460] "GET" "\x15\xdbe\x9b"
xxx.890527 [0 172.21.0.1:39460] "LPUSH" "zzzz" "zzzz"
xxx.890733 [0 172.21.0.1:39460] "RPOP" "zzzz"
xxx.891013 [0 172.21.0.1:39460] "LPUSH" "q" "item1"
xxx.891181 [0 172.21.0.1:39460] "LPUSH" "q" "item2"
xxx.891342 [0 172.21.0.1:39460] "RPOP" "q"
xxx.891558 [0 172.21.0.1:39460] "SADD" "s" "item1"
xxx.891704 [0 172.21.0.1:39460] "SADD" "s" "item2"
xxx.896666 [0 172.21.0.1:39460] "SMEMBERS" "s"
xxx.897598 [0 172.21.0.1:39460] "HSET" "h" "item1" "value1"
xxx.897859 [0 172.21.0.1:39460] "HSET" "h" "item2" "value2"
xxx.898021 [0 172.21.0.1:39460] "HGETALL" "h"
xxx.898360 [0 172.21.0.1:39460] "ZADD" "z" "1.5" "item1"
xxx.898535 [0 172.21.0.1:39460] "ZADD" "z" "0.5" "item2"
xxx.898680 [0 172.21.0.1:39460] "ZADD" "z" "2.5" "item3"
xxx.898830 [0 172.21.0.1:39460] "ZREVRANGE" "z" "0" "1"
xxx.404400 [0 172.21.0.1:39460] "INCR" "pi"
xxx.404416 [0 172.21.0.1:39460] "GET" "pi"
xxx.404873 [0 172.21.0.1:39460] "WATCH" "a"
xxx.405058 [0 172.21.0.1:39460] "MULTI"
xxx.405854 [0 172.21.0.1:39460] "ZADD" "zt" "0.5" "item2"
xxx.405866 [0 172.21.0.1:39460] "ZADD" "zt" "2.5" "item3"
xxx.405870 [0 172.21.0.1:39460] "EXEC"
xxx.406334 [0 172.21.0.1:39460] "SET" "a" "a1"
xxx.406514 [0 172.21.0.1:39460] "WATCH" "a"
xxx.406712 [0 172.21.0.1:39460] "MULTI"
xxx.407292 [0 172.21.0.1:39466] "SET" "a" "a2"
xxx.407596 [0 172.21.0.1:39460] "EXEC"
xxx.408185 [0 172.21.0.1:39460] "PUBLISH" "channel1" "Hello world"
xxx.408370 [0 172.21.0.1:39460] "PUBLISH" "channel1" "quit"
xxx.408542 [0 172.21.0.1:39460] "SUBSCRIBE" "channel1"

## HyperLogLog: small cardinality

Even for small cardinality, the gap can still happen.

Redis HyperLogLog doesn't have special logic for small cardinality.

```
findFirstCardinalityUnEqual()
9 - 8: hll-huge-v7-798
8 - 7: hll-huge-v7-1594
8 - 7: hll-huge-v7-1711
8 - 7: hll-huge-v7-2744
5 - 4: hll-huge-v7-3190
5 - 4: hll-huge-v7-3529
3 - 2: hll-huge-v7-3865
9 - 8: hll-huge-v7-4284
6 - 5: hll-huge-v7-4525
7 - 6: hll-huge-v7-5464
5 - 4: hll-huge-v7-5751
5 - 4: hll-huge-v7-5858
7 - 6: hll-huge-v7-6180
6 - 5: hll-huge-v7-7227
8 - 7: hll-huge-v7-7720
6 - 5: hll-huge-v7-8314
5 - 4: hll-huge-v7-8673
8 - 7: hll-huge-v7-9054
8 - 7: hll-huge-v7-9463
```
