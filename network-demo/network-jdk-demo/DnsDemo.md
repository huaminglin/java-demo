# Java Security properties to config DNS response cache

## DNS positive response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server01

```
server01/10.2.2.67, time: 5161589
server01/10.2.2.67, time: 83454
server01/10.2.2.67, time: 144073
server01/10.2.2.67, time: 56324
server01/10.2.2.67, time: 34082
```

Note: networkaddress.cache.ttl default value is: -1 (forever).


## DNS negative response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server0123

```
Unknown host: server0123, time: 5297460
Unknown host: server0123, time: 128889
Unknown host: server0123, time: 1551532
Unknown host: server0123, time: 150074
Unknown host: server0123, time: 2634105
```

Note: networkaddress.cache.negative.ttl default value is 10.

## Disable positive response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server01 0

Update /etc/hosts to check whether the running programming can get the update.

```
server01/10.2.2.70, time: 1495925
server01/10.2.2.70, time: 253817
server01/10.2.2.70, time: 198478
server01/10.2.2.71, time: 251857
server01/10.2.2.71, time: 188935
```

## Disable negative response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server0123 -1 0

```
Unknown host: server0123, time: 2482463
Unknown host: server0123, time: 1752881
Unknown host: server0123, time: 1610260
Unknown host: server0123, time: 1647940
Unknown host: server0123, time: 1357274
```
