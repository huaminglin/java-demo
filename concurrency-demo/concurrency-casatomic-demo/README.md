# CasAtomicDemo

## Console log

```
true
false
```

## public API

```
java.util.concurrent.atomic.AtomicLong.value

    private volatile long value;

    public final boolean compareAndSet(long expectedValue, long newValue) {
        return U.compareAndSetLong(this, VALUE, expectedValue, newValue);
    }
```

jdk.internal.misc.Unsafe.compareAndSetLong
```
    @HotSpotIntrinsicCandidate
    public final native boolean compareAndSetLong(Object o, long offset,
                                                  long expected,
                                                  long x);
```
