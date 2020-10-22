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
