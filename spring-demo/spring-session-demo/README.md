# Spring Session Demo

## Run the applciation

mvn spring-boot:run


## Check session data in the Redis

```
redis-cli
127.0.0.1:6379> keys *
1) "spring:session:sessions:8b169f13-b119-4299-b297-bb64ac25d3e4"
2) "spring:session:sessions:expires:8b169f13-b119-4299-b297-bb64ac25d3e4"
3) "spring:session:expirations:1605543960000"
```
