# JOL demo

## org.openjdk.jol.vm.VM.current().details()

```
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
# Running 64-bit HotSpot VM.
# Using compressed oop with 3-bit shift.
# Using compressed klass with 3-bit shift.
# WARNING | Compressed references base/shifts are guessed by the experiment!
# WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
# WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
```

## huaminglin.demo.jvm.jol.SimpleInt

```
   huaminglin.demo.jvm.jol.SimpleInt object internals:
    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         0    12        (object header)                           N/A
        12     4    int SimpleInt.state                           N/A
   Instance size: 16 bytes
   Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

## huaminglin.demo.jvm.jol.SimpleInt and System.identityHashCode

```
   huaminglin.demo.jvm.jol.SimpleInt object internals:
    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
         4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         8     4        (object header)                           0b 1c 01 08 (00001011 00011100 00000001 00001000) (134290443)
        12     4    int SimpleInt.state                           0
   Instance size: 16 bytes
   Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

```
The identity hash code is 1684890795
huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 ab 64 6d (00000001 10101011 01100100 01101101) (1835313921)
      4     4        (object header)                           64 00 00 00 (01100100 00000000 00000000 00000000) (100)
      8     4        (object header)                           0b 1c 01 08 (00001011 00011100 00000001 00001000) (134290443)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

```

1684890795: 1100100 01101101 01100100 10101011

JVM stores that value in little-endian format.

## -XX:ObjectAlignmentInBytes

## lock

```
synchronized (lock):
huaminglin.demo.jvm.jol.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 70 01 ac (00000101 01110000 00000001 10101100) (-1409191931)
      4     4        (object header)                           ec 7f 00 00 (11101100 01111111 00000000 00000000) (32748)
      8     4        (object header)                           0b 1c 01 08 (00001011 00011100 00000001 00001000) (134290443)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

## VM.current().addressOf()

Object address: 30611150768

## jdk.internal.vm.annotation.Contended, -XX:ContendedPaddingWidth tuning and -XX:-RestrictContended

## boolean[3]
   [Z object internals:
    OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         8     4           (object header)                           be 70 07 00 (10111110 01110000 00000111 00000000) (487614)
        12     4           (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
        16     3   boolean [Z.<elements>                             N/A
        19     5           (loss due to the next object alignment)
   Instance size: 24 bytes
   Space losses: 0 bytes internal + 5 bytes external = 5 bytes total

## -XX:-UseCompressedOops

## How GC affect object headers

``

Gc
Address changed: 1
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           0d 00 00 00 (00001101 00000000 00000000 00000000) (13)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 2
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           15 00 00 00 (00010101 00000000 00000000 00000000) (21)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 3
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           1d 00 00 00 (00011101 00000000 00000000 00000000) (29)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 4
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           25 00 00 00 (00100101 00000000 00000000 00000000) (37)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 5
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           2d 00 00 00 (00101101 00000000 00000000 00000000) (45)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 6
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           35 00 00 00 (00110101 00000000 00000000 00000000) (53)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 7
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           3d 00 00 00 (00111101 00000000 00000000 00000000) (61)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 8
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           45 00 00 00 (01000101 00000000 00000000 00000000) (69)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 9
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           4d 00 00 00 (01001101 00000000 00000000 00000000) (77)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 10
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           55 00 00 00 (01010101 00000000 00000000 00000000) (85)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 11
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           5d 00 00 00 (01011101 00000000 00000000 00000000) (93)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 12
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           65 00 00 00 (01100101 00000000 00000000 00000000) (101)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 13
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           6d 00 00 00 (01101101 00000000 00000000 00000000) (109)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 14
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           75 00 00 00 (01110101 00000000 00000000 00000000) (117)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 15
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           7d 00 00 00 (01111101 00000000 00000000 00000000) (125)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 16
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           7d 00 00 00 (01111101 00000000 00000000 00000000) (125)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
``

The first byte is used for GC track, so it doesn't conflict with identityHashCode().


## identityHashCode(), then gc

0[0001]001: The middle 4 bits are used to track GC.

```
Gc
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

513169028
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 84 56 96 (00000001 10000100 01010110 10010110) (-1772715007)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 1
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           09 84 56 96 (00001001 10000100 01010110 10010110) (-1772714999)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 2
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           11 84 56 96 (00010001 10000100 01010110 10010110) (-1772714991)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 3
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           19 84 56 96 (00011001 10000100 01010110 10010110) (-1772714983)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 4
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           21 84 56 96 (00100001 10000100 01010110 10010110) (-1772714975)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 5
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           29 84 56 96 (00101001 10000100 01010110 10010110) (-1772714967)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 6
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           31 84 56 96 (00110001 10000100 01010110 10010110) (-1772714959)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 7
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           39 84 56 96 (00111001 10000100 01010110 10010110) (-1772714951)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 8
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           41 84 56 96 (01000001 10000100 01010110 10010110) (-1772714943)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 9
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           49 84 56 96 (01001001 10000100 01010110 10010110) (-1772714935)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 10
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           51 84 56 96 (01010001 10000100 01010110 10010110) (-1772714927)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 11
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           59 84 56 96 (01011001 10000100 01010110 10010110) (-1772714919)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 12
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           61 84 56 96 (01100001 10000100 01010110 10010110) (-1772714911)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 13
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           69 84 56 96 (01101001 10000100 01010110 10010110) (-1772714903)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 14
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           71 84 56 96 (01110001 10000100 01010110 10010110) (-1772714895)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 15
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           79 84 56 96 (01111001 10000100 01010110 10010110) (-1772714887)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

Address changed: 16
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           79 84 56 96 (01111001 10000100 01010110 10010110) (-1772714887)
      4     4        (object header)                           1e 00 00 00 (00011110 00000000 00000000 00000000) (30)
      8     4        (object header)                           8f 04 00 00 (10001111 00000100 00000000 00000000) (1167)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```
