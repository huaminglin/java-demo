## Run a http server for demo
python -m SimpleHTTPServer 8080

## Run the demo
mvn exec:java

## Demo Hoverfly
Hoverfly can work as a http proxy.
io.specto.hoverfly.junit.core.ProxyConfigurer: System.setProperty("http.proxyHost", this.hoverflyConfig.getHost());
Using Hoverfly, we don't need a standalone running http server to run this demo.

Hoverfly: http.proxyHost https.proxyHost http.nonProxyHosts http.proxyPort https.proxyPort
HttpClients.custom().useSystemProperties() is required to enable customized http clients to support Hoverfly.

## Hoverfly capture
hoverctl start
hoverctl mode capture # It seems root permission is needed to run in capture mode.
http://localhost:8888/
curl --proxy http://localhost:8500 http://localhost:8080/
hoverctl logs
hoverctl export /tmp/simulation.json
hoverctl mode simulate
hoverctl stop

## @HoverflyCore and @HoverflySimulate
@HoverflyCore(mode = HoverflyMode.SIMULATE) can't work with @HoverflySimulate.
@HoverflySimulate has higher priority.
