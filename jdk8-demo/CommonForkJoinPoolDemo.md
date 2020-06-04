# java.util.concurrent.ForkJoinPool.common thread pool

## Run on host

/usr/lib/jvm/java-11-openjdk-amd64/bin/java java-demo/jdk8-demo/target/classes huaminglin.demo.jdk8.stream.CommonForkJoinPoolDemo
CPU Core: 8
CommonPool Parallelism: 7
CommonPool Common Parallelism: 7
Processed in 2012 milliseconds, result: 55

## Run with docker: 4 cpus

docker run -it --cpus 4 -v ${PWD}/target/classes:/app --workdir /app adoptopenjdk/openjdk11 java huaminglin.demo.jdk8.stream.CommonForkJoinPoolDemo

CPU Core: 4
CommonPool Parallelism: 3
CommonPool Common Parallelism: 3
Processed in 3007 milliseconds, result: 55

## Run with docker: 2 cpus

docker run -it --cpus 2 -v ${PWD}/target/classes:/app --workdir /app adoptopenjdk/openjdk11 java huaminglin.demo.jdk8.stream.CommonForkJoinPoolDemo

CPU Core: 2
CommonPool Parallelism: 1
CommonPool Common Parallelism: 1
Processed in 5007 milliseconds, result: 55

## Run with docker: 1 cpu

docker run -it --cpus 1 -v ${PWD}/target/classes:/app --workdir /app adoptopenjdk/openjdk11 java huaminglin.demo.jdk8.stream.CommonForkJoinPoolDemo

CPU Core: 1
CommonPool Parallelism: 1
CommonPool Common Parallelism: 1
Processed in 5008 milliseconds, result: 55
