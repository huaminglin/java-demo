# synchronized() and lock type

biased lock: Biased lock is used to optimize the synchronized() codes which are only access by one thread for the whole runtime.
light lock: Use auto spin to avoid the thread to swap out of the CPU if the lock can be available in a very short time.
heavy lock: The thread is put into the waiting list and needed to awake it when the lock is available. 

## biased lock

```
synchronized (lock) biased:
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 70 01 30 (00000101 01110000 00000001 00110000) (805400581)
      4     4        (object header)                           6b 7f 00 00 (01101011 01111111 00000000 00000000) (32619)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```

00000[1]01 00000000 00000000 00000000
00000[1]01 01110000 00000001 00110000
The bit for biased lock is on by default; if a thread acquires a biased lock, its id is registered in the header.


## biased -> light

Run one thread and wait the thread exit; Run another thread.

```
synchronized (lock) light:
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           49 c8 00 08 (01001001 11001000 00000000 00001000) (134269001)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 48 51 7c (00000101 01001000 01010001 01111100) (2085701637)
      4     4        (object header)                           68 7f 00 00 (01101000 01111111 00000000 00000000) (32616)
      8     4        (object header)                           49 c8 00 08 (01001001 11001000 00000000 00001000) (134269001)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           18 f9 46 51 (00011000 11111001 01000110 01010001) (1363605784)
      4     4        (object header)                           68 7f 00 00 (01101000 01111111 00000000 00000000) (32616)
      8     4        (object header)                           49 c8 00 08 (01001001 11001000 00000000 00001000) (134269001)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

synchronized() exit
huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           49 c8 00 08 (01001001 11001000 00000000 00001000) (134269001)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           30 89 34 83 (00110000 10001001 00110100 10000011) (-2093709008)
      4     4        (object header)                           68 7f 00 00 (01101000 01111111 00000000 00000000) (32616)
      8     4        (object header)                           49 c8 00 08 (01001001 11001000 00000000 00001000) (134269001)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total


Process finished with exit code 0
```



00000101 00000000 00000000 00000000 (initial)
00000101 01001000 01010001 01111100 (biased lock)
00011000 11111001 01000110 01010001 (light lock)
00000001 00000000 00000000 00000000 (light lock release)
00110000 10001001 00110100 10000011 (lock again)


00000101 00000000 00000000 00000000 (initial)
00000001 00000000 00000000 00000000 (light lock release)
Note: After the light lock release, the header is different from the initial value.
The bit after gc bits is used to indicate the biased lock feature on this object.
If identityHashCode() or light/heave lock happened, the biased lock feature is not available on this object.

"00110000 10001001"
For light lock, the gc bits is also set. After light lock release, these bits reset. Weired.

00011000 11111001 01000110 01010001 (light lock )
00110000 10001001 00110100 10000011 (light lock )


## gc and light lock

With gc bits set, then light lock.

```
synchronized() exit
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           79 04 c3 7d (01111001 00000100 11000011 01111101) (2109932665)
      4     4        (object header)                           4e 00 00 00 (01001110 00000000 00000000 00000000) (78)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           28 09 08 cf (00101000 00001001 00001000 11001111) (-821556952)
      4     4        (object header)                           30 7f 00 00 (00110000 01111111 00000000 00000000) (32560)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           79 04 c3 7d (01111001 00000100 11000011 01111101) (2109932665)
      4     4        (object header)                           4e 00 00 00 (01001110 00000000 00000000 00000000) (78)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

```

01111001 00000100 11000011 01111101
00101000 00001001 00001000 11001111
01111001 00000100 11000011 01111101

With light lock, the original object headers are backup to other memory and restore when lock releases.


## heavy lock: Run two threads at the same time and make one thread block for 1 second

```
synchronized (lock) heavy:
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Thread01 in
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           82 93 00 48 (10000010 10010011 00000000 01001000) (1207997314)
      4     4        (object header)                           94 7f 00 00 (10010100 01111111 00000000 00000000) (32660)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Thread02 in
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           82 93 00 48 (10000010 10010011 00000000 01001000) (1207997314)
      4     4        (object header)                           94 7f 00 00 (10010100 01111111 00000000 00000000) (32660)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```
