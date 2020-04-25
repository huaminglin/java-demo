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
