cd $(dirname "$0")

java -Xlog:gc*:file=/tmp/gc.log -Xms128m -Xmx128m -cp target/classes:$HOME/.m2/repository/org/openjdk/jol/jol-core/0.14/jol-core-0.14.jar huaminglin.demo.jvm.jol.JolDemo
