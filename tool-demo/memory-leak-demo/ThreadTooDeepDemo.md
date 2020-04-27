# Demo OOM caused by too many threads created

## -Xss2048k

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss2048k"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo

12194
12195
[WARNING] 
java.lang.StackOverflowError
    at java.io.FileOutputStream.write (FileOutputStream.java:354)
    at java.io.BufferedOutputStream.flushBuffer (BufferedOutputStream.java:81)
    at java.io.BufferedOutputStream.flush (BufferedOutputStream.java:142)
    at java.io.PrintStream.write (PrintStream.java:530)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:87)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:137)
    at java.io.PrintStream.write (PrintStream.java:559)
    at sun.nio.cs.StreamEncoder.writeBytes (StreamEncoder.java:233)
    at sun.nio.cs.StreamEncoder.implFlushBuffer (StreamEncoder.java:312)
    at sun.nio.cs.StreamEncoder.flushBuffer (StreamEncoder.java:104)
    at java.io.OutputStreamWriter.flushBuffer (OutputStreamWriter.java:184)
    at java.io.PrintStream.newLine (PrintStream.java:625)
    at java.io.PrintStream.println (PrintStream.java:813)
    at huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo.printDepth (ThreadTooDeepDemo.java:15)



## -Xss4096k

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss4096k"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo

25238
25239
[WARNING] 
java.lang.StackOverflowError
    at java.io.FileOutputStream.write (FileOutputStream.java:354)
    at java.io.BufferedOutputStream.flushBuffer (BufferedOutputStream.java:81)
    at java.io.BufferedOutputStream.flush (BufferedOutputStream.java:142)
    at java.io.PrintStream.write (PrintStream.java:530)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:87)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:137)
    at java.io.PrintStream.write (PrintStream.java:559)
    at sun.nio.cs.StreamEncoder.writeBytes (StreamEncoder.java:233)
    at sun.nio.cs.StreamEncoder.implFlushBuffer (StreamEncoder.java:312)
    at sun.nio.cs.StreamEncoder.flushBuffer (StreamEncoder.java:104)
    at java.io.OutputStreamWriter.flushBuffer (OutputStreamWriter.java:184)
    at java.io.PrintStream.newLine (PrintStream.java:625)
    at java.io.PrintStream.println (PrintStream.java:813)
    at huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo.printDepth (ThreadTooDeepDemo.java:15)


## -Xss4096k and -Dexec.args="2"

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss4096k"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo -Dexec.args="2"
29600
29601
    at java.io.BufferedOutputStream.flushBuffer (BufferedOutputStream.java:81)
    at java.io.BufferedOutputStream.flush (BufferedOutputStream.java:142)
    at java.io.PrintStream.write (PrintStream.java:530)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:87)
    at java.io.FilterOutputStream.write (FilterOutputStream.java:137)
    at java.io.PrintStream.write (PrintStream.java:559)
    at sun.nio.cs.StreamEncoder.writeBytes (StreamEncoder.java:233)
    at sun.nio.cs.StreamEncoder.implFlushBuffer (StreamEncoder.java:312)
    at sun.nio.cs.StreamEncoder.flushBuffer (StreamEncoder.java:104)
    at java.io.OutputStreamWriter.flushBuffer (OutputStreamWriter.java:184)
    at java.io.PrintStream.newLine (PrintStream.java:625)
    at java.io.PrintStream.println (PrintStream.java:813)
    at huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo.printDepth (ThreadTooDeepDemo.java:15)


## Conclusion

java.lang.StackOverflowError is complicated.
If we run the multiple times, the error happens after different count of printDepth call.
But we can say Xss configure the stack size limit per thread.


## -Xss8192k and -Dexec.args="1000 10000"

export MAVEN_OPTS="-Xms128m -Xmx128m -Xss8192k"
mvn exec:java -Dexec.mainClass=huaminglin.demo.tool.memory.thread.ThreadTooDeepDemo -Dexec.args="1000 1000"


At the start of run the application


free
              total        used        free      shared  buff/cache   available
Mem:       15569140     4688116     8048908      961760     2832116     9597928


cat /proc/27246/status
Name:	java
Umask:	0022
State:	S (sleeping)
Tgid:	27246
Ngid:	0
Pid:	27246
PPid:	21820
TracerPid:	0
Uid:	1000	1000	1000	1000
Gid:	1000	1000	1000	1000
FDSize:	256
Groups:	4 24 27 30 46 116 126 129 1000 
NStgid:	27246
NSpid:	27246
NSpgid:	27246
NSsid:	21820
VmPeak:	 7360064 kB
VmSize:	 7360064 kB
VmLck:	       0 kB
VmPin:	       0 kB
VmHWM:	  192508 kB
VmRSS:	  174976 kB
RssAnon:	  155004 kB
RssFile:	   19972 kB
RssShmem:	       0 kB
VmData:	 1808188 kB
VmStk:	     136 kB
VmExe:	       4 kB
VmLib:	   22744 kB
VmPTE:	    1624 kB
VmSwap:	       0 kB
HugetlbPages:	       0 kB
CoreDumping:	0
THP_enabled:	1
Threads:	201
SigQ:	0/60529
SigPnd:	0000000000000000
ShdPnd:	0000000000000000
SigBlk:	0000000000000000
SigIgn:	0000000000000000
SigCgt:	2000000181005ccf
CapInh:	0000000000000000
CapPrm:	0000000000000000
CapEff:	0000000000000000
CapBnd:	0000003fffffffff
CapAmb:	0000000000000000
NoNewPrivs:	0
Seccomp:	0
Speculation_Store_Bypass:	thread vulnerable
Cpus_allowed:	ff
Cpus_allowed_list:	0-7
Mems_allowed:	00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000001
Mems_allowed_list:	0
voluntary_ctxt_switches:	25
nonvoluntary_ctxt_switches:	0


At the end of run the application

free
              total        used        free      shared  buff/cache   available
Mem:       15569140     7658772     4925260      979968     2985108     6607328
Swap:        999420      737908      261512


cat /proc/27246/status
Name:	java
Umask:	0022
State:	S (sleeping)
Tgid:	27246
Ngid:	0
Pid:	27246
PPid:	21820
TracerPid:	0
Uid:	1000	1000	1000	1000
Gid:	1000	1000	1000	1000
FDSize:	256
Groups:	4 24 27 30 46 116 126 129 1000 
NStgid:	27246
NSpid:	27246
NSpgid:	27246
NSsid:	21820
VmPeak:	17195892 kB
VmSize:	17195892 kB
VmLck:	       0 kB
VmPin:	       0 kB
VmHWM:	 3074180 kB
VmRSS:	 3074180 kB
RssAnon:	 3053972 kB
RssFile:	   20208 kB
RssShmem:	       0 kB
VmData:	11677028 kB
VmStk:	     136 kB
VmExe:	       4 kB
VmLib:	   22788 kB
VmPTE:	   11172 kB
VmSwap:	       0 kB
HugetlbPages:	       0 kB
CoreDumping:	0
THP_enabled:	1
Threads:	1031
SigQ:	0/60529
SigPnd:	0000000000000000
ShdPnd:	0000000000000000
SigBlk:	0000000000000000
SigIgn:	0000000000000000
SigCgt:	2000000181005ccf
CapInh:	0000000000000000
CapPrm:	0000000000000000
CapEff:	0000000000000000
CapBnd:	0000003fffffffff
CapAmb:	0000000000000000
NoNewPrivs:	0
Seccomp:	0
Speculation_Store_Bypass:	thread vulnerable
Cpus_allowed:	ff
Cpus_allowed_list:	0-7
Mems_allowed:	00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000000,00000001
Mems_allowed_list:	0
voluntary_ctxt_switches:	26
nonvoluntary_ctxt_switches:	0


After close the application:
free
              total        used        free      shared  buff/cache   available
Mem:       15569140     4422628     8042764      930616     3103748     9890868
Swap:        999420      724080      275340

Conclusion: The application consumes about (9890868 - 6607328) = 3283540.
This is 3G memory.
But the memory is not on the heap. It is not shown by VisualVM. Where is it?
Check VmSize change: (17195892 - 7360064) = 9835828
This is about 9G. Is reversed virtual memory?

RssAnon:	 3053972 kB
RssFile:	   20208 kB
RssShmem:	       0 kB

RssAnon is about 3G. This is the memory the application actually used.
