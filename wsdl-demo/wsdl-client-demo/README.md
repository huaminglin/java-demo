mvn exec:java

mvn -Dwsdl.location=local exec:java
WSDL:wsdl/hello.wsdl
WSDL:file:/home/user1/workspace/java-demo/wsdl-demo/wsdl-client-demo/target/classes/wsdl/hello.wsdl

Request:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
<S:Body>
<ns2:sayHello xmlns:ns2="http://server.wsdl.demo.huaminglin/">
<guestname>
<name>name1</name>
</guestname>
</ns2:sayHello>
</S:Body>
</S:Envelope>

Response:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
<S:Header/>
<S:Body>
<ns2:sayHelloResponse xmlns:ns2="http://server.wsdl.demo.huaminglin/">
<return>
<message>Hello name1</message>
</return>
</ns2:sayHelloResponse>
</S:Body>
</S:Envelope>

void close(MessageContext context)
Hello name1
