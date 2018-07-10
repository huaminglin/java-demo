## Run a http server for demo
python -m SimpleHTTPServer 8080

## Run the demo
mvn exec:java

## Demo Hoverfly
Hoverfly can work as a http proxy.
io.specto.hoverfly.junit.core.ProxyConfigurer: System.setProperty("http.proxyHost", this.hoverflyConfig.getHost());
Using Hoverfly, we don't need a standalone running http server to run this demo.
