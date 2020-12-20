# Demo joor

## Which classloader is used to load the class

com.example.HelloWorld	5244	huaminglin.demo.joor.JoorClassPathDemo.main()	org.joor.Compile$ByteArrayClassLoader@2c72f750

Its parent classloader is jdk.internal.loader.ClassLoaders$AppClassLoader.

So the dynamic class is loaded from a special classloader, not the current classloader.

It's not easy for joor to load the new compiled class with current classloader, it has to put the new compiled class to the CLASSPATH of the target classloader, and it might be impossible since we have many kinds of classloader.

Conclusion: joor should not be used in complicated classloader environment (for example, webapp).
