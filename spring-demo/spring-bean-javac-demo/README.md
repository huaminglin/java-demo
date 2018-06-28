maven-compiler-plugin and javax.tools.JavaCompiler

1. Default maven compile settings
mvn clean install -P default && mvn exec:java -P default

detectParameterNameByReflect: arg0
detectParameterNameBySpring: myString2

javap -v target/classes/huaminglin/demo/spring/bean/SpringBeanDemo.class > SpringBeanDemo.javap.default.txt

      LineNumberTable:
        line 12: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lhuaminglin/demo/spring/bean/SpringBeanDemo;

      LineNumberTable:
        line 68: 0
        line 69: 3
        line 70: 6
        line 71: 14
        line 72: 27
        line 73: 31
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      32     0  args   [Ljava/lang/String;
           14      18     1 context   Lorg/springframework/context/annotation/AnnotationConfigApplicationContext;

2. Enable <maven.compiler.parameters>
mvn clean install -P enableParameters && mvn exec:java -P enableParameters

detectParameterNameByReflect: myString2
detectParameterNameBySpring: myString2

javap -v target/classes/huaminglin/demo/spring/bean/SpringBeanDemo.class > SpringBeanDemo.javap.parameters.txt
MethodParameters: name, flags

3. Use javac -g:none
mvn clean install -P g_none && mvn exec:java -P g_none

detectParameterNameByReflect: arg0
detectParameterNameBySpring failed
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'myDate1' defined in huaminglin.demo.spring.bean.SpringBeanDemo$MyConfiguration: Unsatisfied dependency expressed through method 'myDate1' parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'java.lang.String' available: expected single matching bean but found 2: myString1,myString2

javap -v target/classes/huaminglin/demo/spring/bean/SpringBeanDemo.class > SpringBeanDemo.javap.g_none.txt

4. Use javac -g:vars
mvn clean install -P g_vars && mvn exec:java -P g_vars

detectParameterNameByReflect: arg0
detectParameterNameBySpring: myString2

javap -v target/classes/huaminglin/demo/spring/bean/SpringBeanDemo.class > SpringBeanDemo.javap.g_vars.txt

      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lhuaminglin/demo/spring/bean/SpringBeanDemo;


      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      32     0  args   [Ljava/lang/String;
           14      18     1 context   Lorg/springframework/context/annotation/AnnotationConfigApplicationContext;

5. Use javac -g:nones and enable <maven.compiler.parameters>
mvn clean install -P g_none_parameters && mvn exec:java -P g_none_parameters
detectParameterNameByReflect: myString2
detectParameterNameBySpring: myString2

javap -v target/classes/huaminglin/demo/spring/bean/SpringBeanDemo.class > SpringBeanDemo.javap.g_none_parameters.txt
