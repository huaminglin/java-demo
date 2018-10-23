mvn exec:java

WSDL:wsdl/hello.wsdl
WSDL:file:/home/user1/workspace/java-demo/wsdl-demo/wsdl-client-demo/target/classes/wsdl/hello.wsdl

Request:
<?xml version="1.0" encoding="UTF-8"?><S:Body xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
<ns2:sayHello xmlns:ns2="http://server.wsdl.demo.huaminglin/">
<guestname>
<name>name1</name>
</guestname>
</ns2:sayHello>
</S:Body>

Response:
<?xml version="1.0" encoding="UTF-8"?><S:Body xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
<ns2:sayHelloResponse xmlns:ns2="http://server.wsdl.demo.huaminglin/">
<return>
<message>Hello name1</message>
</return>
</ns2:sayHelloResponse>
</S:Body>

void close(MessageContext context)
Hello name1
