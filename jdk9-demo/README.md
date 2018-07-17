## >= JDK 9
export JAVA_HOME=$HOME/bin/jdk-10.0.1

## Maven version 3.5
export PATH=$HOME/bin/apache-maven-3.5.4/bin:$PATH

## maven-compiler-plugin 3.7.0

## module-info.java
module huaminglin.demo.jdk9 {
    requires jdk.incubator.httpclient;
}

"jdk.incubator.httpclient" is required, otherwise:
package jdk.incubator.http is declared in module jdk.incubator.httpclient, which is not in the module graph.
