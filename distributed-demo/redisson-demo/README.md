# Demo redisson

## Run the demo
mvn clean package

mvn exec:java -Dexec.mainClass="huaminglin.demo.redis.redisson.RedissonDemo"

```
demoKeys()
Count: 14
str1
hll-huge-v3
float1
hll-small-v3
int1
s
xxx
q
h
a
zt
pi
zp
z
demoAtomicLong()
true
false
demoRBucket()
entity1
myname
```

## docker-demo/redis-demo/monitor.sh

```
   OK
   xxx.523422 [0 172.21.0.1:40872] "DBSIZE"
   xxx.535701 [0 172.21.0.1:40892] "SCAN" "0" "COUNT" "10"
   xxx.544626 [0 172.21.0.1:40870] "SCAN" "11" "COUNT" "10"
   xxx.553980 [0 172.21.0.1:40884] "SET" "rlong" "6"
   xxx.562938 [0 172.21.0.1:40880] "EVAL" "local currValue = redis.call('get', KEYS[1]); if currValue == ARGV[1] or (tonumber(ARGV[1]) == 0 and currValue == false) then redis.call('set', KEYS[1], ARGV[2]); return 1 else return 0 end" "1" "rlong" "6" "27"
   xxx.563056 [0 lua] "get" "rlong"
   xxx.563065 [0 lua] "set" "rlong" "27"
   xxx.569634 [0 172.21.0.1:40888] "EVAL" "local currValue = redis.call('get', KEYS[1]); if currValue == ARGV[1] or (tonumber(ARGV[1]) == 0 and currValue == false) then redis.call('set', KEYS[1], ARGV[2]); return 1 else return 0 end" "1" "rlong" "6" "27"
   xxx.569688 [0 lua] "get" "rlong"
   xxx.592112 [0 172.21.0.1:40900] "SET" "rbucket" "\x00\x01'huaminglin.demo.redis.redisson.MyEntity\xfc\aentity1\x00"
   xxx.598727 [0 172.21.0.1:40936] "GET" "rbucket"
   xxx.610060 [0 172.21.0.1:40938] "GET" "rbucket"
   xxx.618504 [0 172.21.0.1:40956] "SET" "rbucket" "\x00\x01'huaminglin.demo.redis.redisson.MyEntity\xfc\x06myname\x00"
   xxx.626506 [0 172.21.0.1:40896] "GET" "rbucket"
```
