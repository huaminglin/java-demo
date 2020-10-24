# GC demo

## -Xlog:safepoint:file=gc.log

```
[safepoint    ] Entering safepoint region: EnableBiasedLocking
[safepoint    ] Entering safepoint region: RevokeBias
[safepoint    ] Total time for which application threads were stopped: 0.0004894 seconds, Stopping threads took: 0.0002861 seconds
[safepoint    ] Entering safepoint region: ThreadDump
[safepoint    ] Application time: 0.0114244 seconds
```

The safepoint write logs frequently.

## Use G1 Humongous Allocation

```
[30.974s][info][gc,start     ] GC(0) Pause Young (Concurrent Start) (G1 Humongous Allocation)
[30.991s][info][gc,heap      ] GC(0) Humongous regions: 101->101
[30.991s][info][gc           ] GC(0) Pause Young (Concurrent Start) (G1 Humongous Allocation) 115M->103M(256M) 17.070ms
[80.188s][info][gc,heap        ] GC(9) Humongous regions: 404->0
[80.189s][info][gc             ] GC(9) Pause Full (G1 Humongous Allocation) 406M->2M(256M) 43.422ms
```

## Case 1: Young GC

java -Xlog:gc*:file=gc.log -Xms256m -Xmx512m -cp target/classes huaminglin.demo.jvm.gc.GcDemo

3
3
3
3
8
3
0

The last allocation will trigger a GC, otherwise OOM happens.

## GC demo: allocate an object directly on the old generation

## GCViewer

java -jar gcviewer-1.36.jar gc-case-1.log

```
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderFacade loadModel
INFO: GCViewer version 1.36 (2019-11-30T21:36:26+0000)
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderFactory getDataReaderBySample
INFO: File format: Oracle / OpenJDK unified jvm logging
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderUnifiedJvmLogging read
INFO: Reading Oracle / OpenJDK unified jvm logging format...
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderUnifiedJvmLogging lineContainsParseableEvent
INFO:  Heap region size: 1M
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderUnifiedJvmLogging lineContainsParseableEvent
INFO:  Using G1
Oct 24, 2020 11:00:42 AM com.tagtraum.perf.gcviewer.imp.DataReaderUnifiedJvmLogging read
INFO: Reading done.
Pause Cleanup [n, avg, sum, min, max]:	11	2.8118181818181817E-4	0.003093	1.93E-4	3.8E-4
Pause Remark [n, avg, sum, min, max]:	11	0.0010261818181818182	0.011288000000000001	8.3E-4	0.001433
Pause Young (Concurrent Start) (G1 Evacuation Pause); Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	8	0.012213624999999999	0.09770899999999999	0.0029950000000000003	0.031251
Pause Young (Concurrent Start) (G1 Evacuation Pause); To-space exhausted; Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	1	0.014882	0.014882	0.014882	0.014882
Pause Young (Concurrent Start) (G1 Humongous Allocation); Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	2	0.0028685	0.005737	0.002185	0.003552
Pause Young (Mixed) (G1 Evacuation Pause); Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	3	0.011756999999999998	0.035271	0.008133	0.018251999999999997
Pause Young (Normal) (G1 Evacuation Pause); Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	13	0.017163692307692303	0.22312799999999997	0.003042	0.035439
Pause Young (Prepare Mixed) (G1 Evacuation Pause); Eden regions:; Survivor regions:; Old regions:; Humongous regions:; Metaspace: [n, avg, sum, min, max]:	3	0.007997666666666667	0.023993	0.005865	0.010469
Concurrent Cycle [n, avg, sum, min, max]:	22	0.00991	0.21802	0.0	0.027978000000000003
initiatingOccupancyFraction	java.lang.IllegalStateException: n == 0
heap size used (n, avg, stddev, min, max):	52	279571.6923076923	121408.38899812137	24576	513024
perm size used (n, avg, stddev, min, max):	30	5222.333333333333	1.7485626277024207	5217	5223
tenured size used (n, avg, stddev, min, max):	30	252450.13333333333	136544.97348082118	0	483328
young size used (n, avg, stddev, min, max):	30	19626.666666666668	9269.139264928737	3072	47104
```

## Online analyser: GCeasy

https://gceasy.io/

Industry's first machine learning guided Garbage collection log analysis tool.

GCeasy has in-built intelligence to auto-detect problems in the JVM & Android GC logs and recommend solutions to it.

GCeasy: GC Intelligence Report
```
JVM memory size: Young Generation; Old Generation; Humongous; Meta Space; Young + Old + Meta space
JVM memory size: Allocated vs Peak
Key Performance Indicators: Throughput
Key Performance Indicators: Latency: Avg Pause GC Time, Max Pause GC time
GC Pause Duration Time Range
G1 Collection Phases Statistics: Young GC, Concurrent Marking, Root Region Scanning, Remark, Cleanup
G1 Collection Phases Statistics: Total Time, Avg Time, Std Dev Time, Min Time, Max Time, Interval Time, Count
G1 GC Time: Pause GC Time, Concurrent CG Time
Object Stats: Total created bytes, Total promoted bytes, Avg create rage, Avg promotion rate
Consecutive Full GC
Long Pause
Allocation stall metrics
GC Causes
This GC is triggered when copying live objects from one set of regions to another set of regions. When Young generation regions are only copied then Young GC is triggered. When both Young + Tenured regions are copied, Mixed GC is triggered.
Humongous allocations are allocations that are larger than 50% of the region size in G1.
Frequent humongous allocations can cause couple of performance issues:1. If the regions contain humongous objects, space between the last humongous object in the region and the end of the region will be unused. If there are multiple such humongous objects, this unused space can cause the heap to become fragmented.2. Until Java 1.8u40 reclamation of humongous regions were only done during full GC events. Where as in the newer JVMs, clearing humongous objects are done in cleanup phase.
Tenuring Summary
Reports object aging summary in Young generation
```

## openjdk:8u265-jdk and "java -Xms256m -Xmx512m -Xloggc:/gc.log -cp /app.jar huaminglin.demo.jvm.gc.GcDemo"

./update.sh, the tty doesn't forward the input to the Java process.

Then use "docker attach" to the Java process.

```
OpenJDK 64-Bit Server VM (25.265-b01) for linux-amd64 JRE (1.8.0_265-b01), built on Jul 27 2020 07:33:48 by "openjdk" with gcc 4.4.7 20120313 (Red Hat 4.4.7-23)
Memory: 4k page, physical 15569112k(7650844k free), swap 999420k(999420k free)
CommandLine flags: -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=536870912 -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
48.389: [GC (Allocation Failure)  65536K->69021K(251392K), 0.0111200 secs]
48.998: [GC (Allocation Failure)  134557K->141259K(251392K), 0.0165749 secs]
49.014: [Full GC (Ergonomics)  141259K->126508K(348672K), 0.0458920 secs]
49.214: [GC (Allocation Failure)  192044K->197101K(348672K), 0.0114090 secs]
49.655: [GC (Allocation Failure)  262637K->269966K(412160K), 0.0129472 secs]
49.668: [Full GC (Ergonomics)  269966K->254023K(489472K), 0.0626084 secs]
52.017: [GC (Allocation Failure) -- 383047K->478719K(489472K), 0.0234673 secs]
52.040: [Full GC (Ergonomics)  478719K->380398K(489472K), 0.0739053 secs]
60.107: [Full GC (Ergonomics)  478638K->57952K(429568K), 0.0234742 secs]
```


## openjdk:8u265-jdk and "java -Xms256m -Xmx512m -Xloggc:/gc.log -XX:+PrintGCDetails -XX:+PrintReferenceGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintTenuringDistribution -cp /app.jar huaminglin.demo.jvm.gc.GcDemo"

gc-java8-UseParallelGC.log

