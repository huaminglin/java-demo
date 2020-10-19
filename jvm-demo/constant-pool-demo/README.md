# Constant pool demo

## String constant pool demo

Open VisualVm to dump heap when the application is sleeping.

Check the heap dump on the OQL console:

Run 'select s from java.lang.String s where s.toString().startsWith("huaminglin.")'

```
Check heap dump about ClassNameClass. First sleep: 60
huaminglin.demo.jvm.constant.pool.ClassNameClass
huaminglin.demo.jvm.constant.pool.ClassNameClass
Class name is in the string constant pool: true
huaminglin.demo.jvm.constant.pool.ClassNameClass.staticString2
Check heap dump about ClassNameClass. Second Second: 60
```

1. Verify StringConstantPoolDemo.class.getName() is a string in the string constant pool.

2. String of class name is used in the class loader

From the string references, we can found the related class loader. The string name is used as some kind of lock.

jdk.internal.loader.ClassLoader$AppClassLoader: paralelLockMap


3. When the string constant in a class is put into the the string constant pool in the heap

a. Class.forName("huaminglin.demo.jvm.constant.pool.StaticStringFieldClass", false, ClassLoader.getSystemClassLoader())

From the first heap dump, "huaminglin.demo.jvm.constant.pool.StaticStringFieldClass.staticString1" isn't found.

b. Class.forName("huaminglin.demo.jvm.constant.pool.StaticStringFieldClass", true, ClassLoader.getSystemClassLoader())

From the second heap dump, "huaminglin.demo.jvm.constant.pool.StaticStringFieldClass.staticString1" is found.

In the "<references>" node, we can find "static <resolved_references> in java.lang.Calss#304 StaticStringFieldCalss".

Conclusion, when a class resolve its reference symblic, it puts its static string pool into the dynamic static string pool in the heap.
