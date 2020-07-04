# Demo Hazelcast Queue

## queue.put successfully, but sometime the item is lost for unknow reason.

"world01" is lost for every run; Weird.

```
WorkerItem{name='hello', value='world02'}
WorkerItem{name='hello', value='world03'}
WorkerItem{name='hello', value='world04'}
```

## Message Queue vs. Queue

Message queue is n:m relationship; the same item is consumed by all the subscriber.

Queue is 1:1. A publisher puts one item into the queue, then a worker takes it and consumes it.

In my opinion, the queue can be used to forward a request from one microservice to another microservice.

The message queue can be used to notify an update like cache expiration to all the services.
