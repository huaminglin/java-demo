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

00000[101] 00000000 00000000 00000000
00000[001] 10101011 01100100 01101101 (identityHashCode)
"101" -> "001", it seems the bit after gc bits has something to do with identityHashCode().
Actually this bit is used to indication whether we call use biased lock feature on this object.
If identityHashCode() or light/heave lock happened, the biased lock feature is not available on this object.

## -XX:ObjectAlignmentInBytes

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
