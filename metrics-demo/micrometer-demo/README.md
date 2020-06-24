# Demo Micrometer

## Run the application

mvn spring-boot:run or mvn exec:java

http://127.0.0.1:8080/actuator/metrics/

http://127.0.0.1:8080/actuator/prometheus

## java -jar target/micrometer-demo-1.0-SNAPSHOT.jar

Verify jar is executable.

## Build the image

sudo mvn dockerfile:build

sudo docker images

huaminglin/micrometer-demo:1.0-SNAPSHOT

## Use jmeter to send a lot of http requests

$JMETER_HOME/bin/jmeter -Jthread_count=10 -Jthread_loop=100 -n -t requests.jmx -l /tmp/micrometer.result -e -o /tmp/micrometer

Creating summariser <summary>
Created the tree successfully using requests.jmx
Starting standalone test xxx
Waiting for possible Shutdown/StopTestNow/HeapDump/ThreadDump message on port 4445
Warning: Nashorn engine is planned to be removed from a future JDK release
summary +    259 in 00:00:16 =   16.4/s Avg:     3 Min:     1 Max:    35 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary +    500 in 00:00:30 =   16.7/s Avg:     2 Min:     0 Max:    21 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =    759 in 00:00:46 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    19 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   1259 in 00:01:16 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    27 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   1759 in 00:01:46 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    19 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   2259 in 00:02:16 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     2 Min:     0 Max:    32 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   2759 in 00:02:46 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    10 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   3259 in 00:03:16 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    12 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   3759 in 00:03:46 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:     9 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   4259 in 00:04:16 =   16.6/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:     9 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   4759 in 00:04:46 =   16.7/s Avg:     1 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     2 Min:     0 Max:    27 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   5259 in 00:05:16 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    11 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   5759 in 00:05:46 =   16.7/s Avg:     1 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     5 Min:     0 Max:    28 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   6259 in 00:06:16 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    12 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   6759 in 00:06:46 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    19 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   7259 in 00:07:16 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    20 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   7759 in 00:07:46 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     3 Min:     0 Max:    32 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   8259 in 00:08:16 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     0 Min:     0 Max:    12 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   8759 in 00:08:46 =   16.7/s Avg:     2 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    21 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   9259 in 00:09:16 =   16.7/s Avg:     1 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    500 in 00:00:30 =   16.7/s Avg:     1 Min:     0 Max:    10 Err:     0 (0.00%) Active: 10 Started: 10 Finished: 0
summary =   9759 in 00:09:46 =   16.7/s Avg:     1 Min:     0 Max:    35 Err:     0 (0.00%)
summary +    241 in 00:00:15 =   16.6/s Avg:     1 Min:     0 Max:     8 Err:     0 (0.00%) Active: 0 Started: 10 Finished: 10
summary =  10000 in 00:10:00 =   16.7/s Avg:     1 Min:     0 Max:    35 Err:     0 (0.00%)
Tidying up ...    xxx
... end of run

## Add spring-boot-starter-aop to support @Aspect for @Timed and TimedAspect

```/actuator/prometheus
# HELP micrometer_demo_timed_sleep_seconds_max  
# TYPE micrometer_demo_timed_sleep_seconds_max gauge
micrometer_demo_timed_sleep_seconds_max{application="micrometer-demo",class="huaminglin.demo.metrics.micrometer.SleepService",exception="none",method="sleep",} 3.326713232
# HELP micrometer_demo_timed_sleep_seconds  
# TYPE micrometer_demo_timed_sleep_seconds summary
micrometer_demo_timed_sleep_seconds_count{application="micrometer-demo",class="huaminglin.demo.metrics.micrometer.SleepService",exception="none",method="sleep",} 1.0
micrometer_demo_timed_sleep_seconds_sum{application="micrometer-demo",class="huaminglin.demo.metrics.micrometer.SleepService",exception="none",method="sleep",} 3.326713232
```

## Check active configuration 

http://127.0.0.1:8080/actuator/configprops
