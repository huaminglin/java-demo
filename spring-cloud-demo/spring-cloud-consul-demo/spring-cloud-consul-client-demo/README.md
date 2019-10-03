mvn spring-boot:run

#####################################################
## Output:
Services:
consul
spring-cloud-consul-server-demo
spring-cloud-consul-server-demo-management
web
spring-cloud-consul-server-demo:
DefaultServiceInstance{instanceId='spring-cloud-consul-server-demo-8080', serviceId='spring-cloud-consul-server-demo', host='x.x.x.x', port=8080, secure=false, metadata={secure=false}}

#####################################################
## spring.cloud.consul.discovery.catalogServicesWatch.enabled=false to disable the following thread
"catalogWatchTaskScheduler-1" #18 prio=5 os_prio=0 tid=0x00007f48ace4d000 nid=0x2197 waiting on condition [0x00007f48dc145000]
   java.lang.Thread.State: TIMED_WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x00000000f100c928> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
        at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
        at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1067)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1127)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
