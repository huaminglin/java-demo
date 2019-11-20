## The client might fail to load configuration from server since they starts at the same time
http://localhost:38180/
curl -X POST http://localhost:38180/actuator/refresh

http://localhost:38280/
curl -X POST http://localhost:38280/actuator/refresh

## Check RabbitMQ
http://127.0.0.1:15672

## Make change to git and Invoke server refresh (No spring-cloud-config-monitor is needed)
curl -X POST http://localhost:8888/actuator/bus-refresh

## Make change to git and Invoke mock webhook (Provided by spring-cloud-config-monitor)
curl -v -X POST "http://localhost:8888/monitor" \
-H "Content-Type: application/json" \
-H "X-Event-Key: repo:push" \
-H "X-Hook-UUID: webhook-uuid" \
-d '{"push": {"changes": []} }'

Response:
[
    "*"
]
