# Native Memory Tracking

## -XX:NativeMemoryTracking=summary

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss8192k -XX:NativeMemoryTracking=summary"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.NativeMemoryDemo -Dexec.args="10"

ps aux|grep NativeMemoryDemo

jcmd 7460 VM.native_memory

7460:

Native Memory Tracking:

Total: reserved=1678576KB, committed=312864KB
-                 Java Heap (reserved=131072KB, committed=131072KB)
                            (mmap: reserved=131072KB, committed=131072KB) 
 
-                     Class (reserved=1071549KB, committed=24045KB)
                            (classes #3941)
                            (  instance classes #3701, array classes #240)
                            (malloc=445KB #8962) 
                            (mmap: reserved=1071104KB, committed=23600KB) 
                            (  Metadata:   )
                            (    reserved=22528KB, committed=20912KB)
                            (    used=20177KB)
                            (    free=735KB)
                            (    waste=0KB =0.00%)
                            (  Class space:)
                            (    reserved=1048576KB, committed=2688KB)
                            (    used=2446KB)
                            (    free=242KB)
                            (    waste=0KB =0.00%)
 
-                    Thread (reserved=165108KB, committed=83104KB)
                            (thread #35)
                            (stack: reserved=164944KB, committed=82940KB)
                            (malloc=121KB #192) 
                            (arena=43KB #68)
 
-                      Code (reserved=248345KB, committed=12141KB)
                            (malloc=657KB #3537) 
                            (mmap: reserved=247688KB, committed=11484KB) 
 
-                        GC (reserved=54856KB, committed=54856KB)
                            (malloc=17188KB #3621) 
                            (mmap: reserved=37668KB, committed=37668KB) 
 
-                  Compiler (reserved=185KB, committed=185KB)
                            (malloc=57KB #310) 
                            (arena=129KB #5)
 
-                  Internal (reserved=676KB, committed=676KB)
                            (malloc=636KB #1602) 
                            (mmap: reserved=40KB, committed=40KB) 
 
-                     Other (reserved=2KB, committed=2KB)
                            (malloc=2KB #1) 
 
-                    Symbol (reserved=5460KB, committed=5460KB)
                            (malloc=4141KB #43299) 
                            (arena=1319KB #1)
 
-    Native Memory Tracking (reserved=1005KB, committed=1005KB)
                            (malloc=8KB #104) 
                            (tracking overhead=997KB)
 
-               Arena Chunk (reserved=196KB, committed=196KB)
                            (malloc=196KB) 
 
-                   Logging (reserved=4KB, committed=4KB)
                            (malloc=4KB #184) 
 
-                 Arguments (reserved=18KB, committed=18KB)
                            (malloc=18KB #481) 
 
-                    Module (reserved=98KB, committed=98KB)
                            (malloc=98KB #1363) 


## -XX:NativeMemoryTracking=summary

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss8192k -XX:NativeMemoryTracking=summary"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.NativeMemoryDemo -Dexec.args="100 1000"

ps aux|grep NativeMemoryDemo

jcmd 8631 VM.native_memory baseline
8631:
Baseline succeeded

jcmd 8631 VM.native_memory summary.diff

8631:

Native Memory Tracking:

Total: reserved=1681557KB +20791KB, committed=312545KB +20855KB

-                 Java Heap (reserved=131072KB, committed=131072KB)
                            (mmap: reserved=131072KB, committed=131072KB)
 
-                     Class (reserved=1071539KB, committed=24035KB)
                            (classes #3944)
                            (  instance classes #3704, array classes #240)
                            (malloc=435KB #8846 +8)
                            (mmap: reserved=1071104KB, committed=23600KB)
                            (  Metadata:   )
                            (    reserved=22528KB, committed=20912KB)
                            (    used=20194KB +1KB)
                            (    free=718KB -1KB)
                            (    waste=0KB =0.00%)
                            (  Class space:)
                            (    reserved=1048576KB, committed=2688KB)
                            (    used=2448KB)
                            (    free=240KB)
                            (    waste=0KB =0.00%)
 
-                    Thread (reserved=168167KB +32420KB, committed=83123KB +32420KB)
                            (thread #31 +4)
                            (stack: reserved=168020KB +32784KB, committed=82976KB +32784KB)
                            (malloc=108KB +14KB #167 +20)
                            (arena=38KB -379 #60 +8)
 
-                      Code (reserved=248322KB +2KB, committed=11858KB +66KB)
                            (malloc=634KB +2KB #3539 +10)
                            (mmap: reserved=247688KB, committed=11224KB +64KB)
 
-                        GC (reserved=54849KB, committed=54849KB)
                            (malloc=17181KB #3649 +3)
                            (mmap: reserved=37668KB, committed=37668KB)
 
-                  Compiler (reserved=200KB, committed=200KB)
                            (malloc=71KB #322 +2)
                            (arena=129KB #5)
 
-                  Internal (reserved=632KB +13KB, committed=632KB +13KB)
                            (malloc=592KB +13KB #1553 +48)
                            (mmap: reserved=40KB, committed=40KB)
 
-                     Other (reserved=2KB, committed=2KB)
                            (malloc=2KB #1)
 
-                    Symbol (reserved=5460KB, committed=5460KB)
                            (malloc=4141KB #43282)
                            (arena=1319KB #1)
 
-    Native Memory Tracking (reserved=1003KB -2KB, committed=1003KB -2KB)
                            (malloc=9KB +2KB #122 +29)
                            (tracking overhead=994KB -4KB)
 
-               Arena Chunk (reserved=191KB -11641KB, committed=191KB -11641KB)
                            (malloc=191KB -11641KB)
 
-                   Logging (reserved=4KB, committed=4KB)
                            (malloc=4KB #184)
 
-                 Arguments (reserved=18KB, committed=18KB)
                            (malloc=18KB #481)
 
-                    Module (reserved=98KB, committed=98KB)
                            (malloc=98KB #1363)
