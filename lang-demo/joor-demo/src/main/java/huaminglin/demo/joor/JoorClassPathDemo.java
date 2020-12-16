package huaminglin.demo.joor;

import org.joor.Reflect;

public final class JoorClassPathDemo {

  public static void main(String[] args) {
    Reflect compile = Reflect.compile("com.example.HelloWorld", "package com.example;\n"
        + "class HelloWorld { public String toString() { return org.apache.commons.lang3.ArrayUtils.class.getName() + ':' + org.apache.commons.io.FileUtils.class.getName();}}\n");
    Class o = compile.get();
    System.out.println(o.getName());
    System.out.println(o.getClassLoader());
    System.out.println(compile.create().get().toString());
    /* Output:
com.example.HelloWorld
org.joor.Compile$ByteArrayClassLoader@13c9d689
org.apache.commons.lang3.ArrayUtils:org.apache.commons.io.FileUtils
     */
  }
}
