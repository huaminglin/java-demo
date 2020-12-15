package huaminglin.demo.joor;

import java.util.function.Supplier;
import org.joor.Reflect;

public final class JoorDemo {

  public static void main(String[] args) {
    Reflect compile = Reflect.compile("com.example.HelloWorld", "package com.example;\n"
        + "class HelloWorld implements java.util.function.Supplier<String> {\n"
        + "    public String get() {\n" + "        return \"Hello World!\";\n" + "    }\n" + "}\n");
    Class o = compile.get();
    System.out.println(o.getName());
    System.out.println(o.getClassLoader());
    Supplier<String> supplier = compile.create().get();
    System.out.println(supplier.get());
    /* Output:
com.example.HelloWorld
org.joor.Compile$ByteArrayClassLoader@768b970c
Hello World!
     */
  }
}
