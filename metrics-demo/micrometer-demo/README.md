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

## Micrometer meters: counter, timer, gauge, distribution summary

```/actuator/prometheus
# HELP service_sleep_histogram_max  
# TYPE service_sleep_histogram_max gauge
service_sleep_histogram_max{application="micrometer-demo",} 1284.0
# HELP service_sleep_histogram  
# TYPE service_sleep_histogram histogram
service_sleep_histogram_bucket{application="micrometer-demo",le="1.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="10.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="11.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="12.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="13.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="14.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="16.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="21.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="26.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="31.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="36.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="41.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="46.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="51.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="56.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="64.0",} 0.0
service_sleep_histogram_bucket{application="micrometer-demo",le="85.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="106.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="127.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="148.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="169.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="190.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="211.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="232.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="256.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="341.0",} 1.0
service_sleep_histogram_bucket{application="micrometer-demo",le="426.0",} 2.0
service_sleep_histogram_bucket{application="micrometer-demo",le="511.0",} 2.0
service_sleep_histogram_bucket{application="micrometer-demo",le="596.0",} 2.0
service_sleep_histogram_bucket{application="micrometer-demo",le="681.0",} 2.0
service_sleep_histogram_bucket{application="micrometer-demo",le="766.0",} 2.0
service_sleep_histogram_bucket{application="micrometer-demo",le="851.0",} 3.0
service_sleep_histogram_bucket{application="micrometer-demo",le="936.0",} 3.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1024.0",} 3.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1365.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1706.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2047.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2388.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2729.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3070.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3411.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3752.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4096.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5461.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6826.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8191.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9556.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="10921.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="12286.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="13651.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="15016.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="16384.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="21845.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="27306.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="32767.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="38228.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="43689.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="49150.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="54611.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="60072.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="65536.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="87381.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="109226.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="131071.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="152916.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="174761.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="196606.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="218451.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="240296.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="262144.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="349525.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="436906.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="524287.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="611668.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="699049.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="786430.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="873811.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="961192.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1048576.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1398101.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1747626.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2097151.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2446676.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2796201.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3145726.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3495251.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3844776.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4194304.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5592405.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6990506.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8388607.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9786708.0",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.1184809E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.258291E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.3981011E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.5379112E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.6777216E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.2369621E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.7962026E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.3554431E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.9146836E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.4739241E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.0331646E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.5924051E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.1516456E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.7108864E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.9478485E7",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.11848106E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.34217727E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.56587348E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.78956969E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.0132659E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.23696211E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.46065832E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.68435456E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.57913941E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.47392426E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.36870911E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.26349396E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.15827881E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.05306366E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.94784851E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.84263336E8",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.073741824E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.431655765E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.789569706E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.147483647E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.505397588E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.863311529E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.22122547E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.579139411E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.937053352E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.294967296E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.726623061E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.158278826E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.589934591E9",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.0021590356E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.1453246121E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.2884901886E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.4316557651E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.5748213416E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.7179869184E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.2906492245E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.8633115306E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.4359738367E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.0086361428E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.5812984489E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.153960755E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.7266230611E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.2992853672E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.8719476736E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.1625968981E10",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.14532461226E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.37438953471E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.60345445716E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.83251937961E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.06158430206E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.29064922451E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.51971414696E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.74877906944E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.66503875925E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.58129844906E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.49755813887E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.41381782868E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.33007751849E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.2463372083E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.16259689811E11",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.007885658792E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.099511627776E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.466015503701E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.832519379626E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.199023255551E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.565527131476E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.932031007401E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.298534883326E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.665038759251E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.031542635176E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.398046511104E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.864062014805E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.330077518506E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.796093022207E12",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.0262108525908E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.1728124029609E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.319413953331E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.4660155037011E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.6126170540712E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.7592186044416E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.3456248059221E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.9320310074026E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.5184372088831E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.1048434103636E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.6912496118441E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.2776558133246E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.8640620148051E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.4504682162856E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.0368744177664E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.3824992236885E13",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.17281240296106E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.40737488355327E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.64193736414548E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.87649984473769E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.1110623253299E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.34562480592211E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.58018728651432E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.81474976710656E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.75299968947541E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.69124961184426E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.62949953421311E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.56774945658196E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.50599937895081E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.44424930131966E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.38249922368851E14",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.032074914605736E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.125899906842624E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.501199875790165E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.876499844737706E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.251799813685247E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.627099782632788E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.002399751580329E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.37769972052787E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.752999689475411E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.128299658422952E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.503599627370496E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.004799503160661E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.505999378950826E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.007199254740991E15",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.0508399130531156E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.200959900632132E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.3510798882111486E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.5011998757901652E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.6513198633691816E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.8014398509481984E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.4019198012642644E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.0023997515803304E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.6028797018963968E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.2033596522124624E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.8038396025285288E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.4043195528445952E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.0047995031606608E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.6052794534767272E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.2057594037927936E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.6076792050570576E16",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.20095990063213232E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.44115188075855872E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.68134386088498528E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.92153584101141152E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.16172782113783808E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.40191980126426464E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.64211178139069088E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.8823037615171174E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.843071682022823E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.8038396025285293E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="5.7646075230342349E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="6.7253754435399411E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="7.6861433640456461E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="8.6469112845513523E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.6076792050570586E17",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.05684471255627635E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.15292150460684698E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.53722867280912922E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="1.92153584101141171E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.305843009213694E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="2.6901501774159764E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.0744573456182584E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.4587645138205409E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="3.8430716820228234E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="4.2273788502251054E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="9.223372036854776E18",} 4.0
service_sleep_histogram_bucket{application="micrometer-demo",le="+Inf",} 4.0
service_sleep_histogram_count{application="micrometer-demo",} 4.0
service_sleep_histogram_sum{application="micrometer-demo",} 2568.0


# HELP service_sleep_timer_seconds  
# TYPE service_sleep_timer_seconds summary
service_sleep_timer_seconds_count{application="micrometer-demo",} 43.0
service_sleep_timer_seconds_sum{application="micrometer-demo",} 101.169259938
# HELP service_sleep_timer_seconds_max  
# TYPE service_sleep_timer_seconds_max gauge
service_sleep_timer_seconds_max{application="micrometer-demo",} 4.990144316
# HELP service_sleep_long_task_timer_seconds  
# TYPE service_sleep_long_task_timer_seconds untyped
service_sleep_long_task_timer_seconds_active_count{application="micrometer-demo",} 6.0
service_sleep_long_task_timer_seconds_duration_sum{application="micrometer-demo",} 11.823024079
# HELP service_sleep_gauge  
# TYPE service_sleep_gauge gauge
service_sleep_gauge{application="micrometer-demo",} 4725.0

# HELP service_sleep_distribution_summary_bytes  
# TYPE service_sleep_distribution_summary_bytes summary
service_sleep_distribution_summary_bytes_count{application="micrometer-demo",} 43.0
service_sleep_distribution_summary_bytes_sum{application="micrometer-demo",} 100976.0
# HELP service_sleep_distribution_summary_bytes_max  
# TYPE service_sleep_distribution_summary_bytes_max gauge
service_sleep_distribution_summary_bytes_max{application="micrometer-demo",} 4990.0

# HELP service_sleep_percentile_timer_seconds_max  
# TYPE service_sleep_percentile_timer_seconds_max gauge
service_sleep_percentile_timer_seconds_max{application="micrometer-demo",} 4.99
# HELP service_sleep_percentile_timer_seconds  
# TYPE service_sleep_percentile_timer_seconds histogram
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.001",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.001048576",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.001398101",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.001747626",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.002097151",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.002446676",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.002796201",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.003145726",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.003495251",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.003844776",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.004194304",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.005592405",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.006990506",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.008388607",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.009786708",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.011184809",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.01258291",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.013981011",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.015379112",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.016777216",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.022369621",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.027962026",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.033554431",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.039146836",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.044739241",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.050331646",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.055924051",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.061516456",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.067108864",} 0.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.089478485",} 2.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.111848106",} 2.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.134217727",} 2.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.156587348",} 2.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.178956969",} 2.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.20132659",} 3.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.223696211",} 4.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.246065832",} 4.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.268435456",} 5.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.357913941",} 7.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.447392426",} 7.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.536870911",} 7.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.626349396",} 7.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.715827881",} 7.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.805306366",} 8.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.894784851",} 8.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="0.984263336",} 8.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="1.073741824",} 10.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="1.431655765",} 13.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="1.789569706",} 17.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="2.147483647",} 20.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="2.505397588",} 22.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="2.863311529",} 23.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="3.22122547",} 30.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="3.579139411",} 34.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="3.937053352",} 36.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="4.294967296",} 39.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="5.726623061",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="7.158278826",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="8.589934591",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="10.021590356",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="11.453246121",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="12.884901886",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="14.316557651",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="15.748213416",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="17.179869184",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="22.906492245",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="28.633115306",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="30.0",} 43.0
service_sleep_percentile_timer_seconds_bucket{application="micrometer-demo",le="+Inf",} 43.0
service_sleep_percentile_timer_seconds_count{application="micrometer-demo",} 43.0
service_sleep_percentile_timer_seconds_sum{application="micrometer-demo",} 100.976

# HELP service_sleep_counter_total Sleep Service Counter
# TYPE service_sleep_counter_total counter
service_sleep_counter_total{application="micrometer-demo",dev="performance",} 100976.0
```
