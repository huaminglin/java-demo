# VolatileValueDemo

## The main memory is not updated even after the update thread exit

```
The init_value will be changed to [1]
The init_value will be changed to [2]
The init_value will be changed to [3]
The init_value will be changed to [4]
The init_value will be changed to [5]
Updater exit()
```

## volatile on object reference makes the access to object fields volatile

```
The init_value will be changed to [1]
The init_value is update ot [1]
The init_value will be changed to [2]
The init_value is update ot [2]
The init_value will be changed to [3]
The init_value is update ot [3]
The init_value will be changed to [4]
The init_value is update ot [4]
The init_value will be changed to [5]
The init_value is update ot [5]
Reader exit()
Updater exit()
```

## IO operation and non volatile variable

volatile sync between CPU cache and main memory.

IO operation can cause the current thread out of CPU and invalidate all its CPU caches.
