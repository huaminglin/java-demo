# Spring Cache Demo

## Run the applciation

mvn spring-boot:run

## JConsole to check the ehcache MBeans

It seems JConsole can access JMX MBeans through local protocol.

No com.sun.management.jmxremote. config is required.

JConsole: MBeans > javax.cache > CacheConfiguration, CacheStatistics

In the CacheStatistics, there is an "instant" cache node. And there is only one Operations "clear" available.

Conclusion: It seems we can't view the cache content through JMX.

## Remote JMX config

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9099 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1"
