package huaminglin.demo.wsdl.server;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@HandlerChain(file = "handlerChain.xml", name = "LogChain")
public class HelloProvider {
    @WebMethod(operationName = "sayHello")
    public HelloResponse sayHello(@WebParam(name = "guestname") HelloRequest guestname) {
        HelloResponse response = new HelloResponse();
        response.setMessage("Hello " + guestname.getName());
        return response;
    }
}
