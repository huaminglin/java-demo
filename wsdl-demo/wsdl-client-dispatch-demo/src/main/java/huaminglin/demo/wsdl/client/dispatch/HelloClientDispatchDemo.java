package huaminglin.demo.wsdl.client.dispatch;

import com.sun.xml.ws.developer.JAXWSProperties;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.http.HTTPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import huaminglin.demo.wsdl.client.dispatch.chain.LogHandler;

public class HelloClientDispatchDemo {

    private static String getString(Source source) throws IOException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StringWriter sw = new StringWriter();
        try {
            transformer.transform(source, new StreamResult(sw));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    private static Dispatch<Source> createDispatcher() {
        QName serviceName = new QName("http://server.wsdl.demo.huaminglin/","Request");
        QName portName = new QName("http://server.wsdl.demo.huaminglin/","Response");
        Service service = Service.create(serviceName);
        service.addPort(portName, HTTPBinding.HTTP_BINDING, "http://127.0.0.1:9999/hello/rest");
        service.setHandlerResolver(new HandlerResolver() {
            public List<Handler> getHandlerChain(PortInfo port) {
                List<Handler> chain = new ArrayList<Handler>();
                chain.add(new LogHandler());
                return chain;
            }
        });
        Dispatch<Source> dispatcher = service.createDispatch(portName, Source.class, Service.Mode.PAYLOAD);
        Map<String, Object> requestContext = dispatcher.getRequestContext();
        requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "POST");
        requestContext.put(JAXWSProperties.CONNECT_TIMEOUT, 20 * 1000);
        requestContext.put(JAXWSProperties.REQUEST_TIMEOUT, 20 * 1000);
        return dispatcher;
    }

    public static void main(String[] args) throws IOException {
        Dispatch dispatcher = createDispatcher();
        Source request = new StreamSource(new StringReader("<request>request1</request>"));
        StreamSource response = (StreamSource) dispatcher.invoke(request);
        System.out.println("response in main: " + response);
        System.out.println(getString(response));
    }
}
