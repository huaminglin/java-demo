package huaminglin.demo.wsdl.client.chain;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {

    private ThreadLocal<String> request = new ThreadLocal<String>();

    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    public boolean handleMessage(SOAPMessageContext context) {
        try{
            return processMessage(context);
        } catch (Exception e1) {
        }
        return true;
    }

    private boolean processMessage (final SOAPMessageContext context) throws SOAPException {
        Boolean outboundProperty = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty) {
            handleRequest(context);
        } else {
            handleResponse(context);
       }
       return true;
    }

    public boolean handleRequest(final SOAPMessageContext context) throws SOAPException {
        String soapPart = request.get();
        if(soapPart != null) {
            System.out.println("\nRequest:\n" + soapPart + "\nResponse:\n no response!");
            request.set(null);
        }
        soapPart = getSoapPartXML(context);
        request.set(soapPart);
        return true;
    }

    public boolean handleResponse(final SOAPMessageContext context) throws SOAPException {
        String soapPart = getSoapPartXML(context);
        System.out.println("\nRequest:\n" + request.get() + "\nResponse:\n" + soapPart);
        request.set(null);
        return true;
    }

    private String getSoapPartXML(SOAPMessageContext context) {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamResult result = new StreamResult(new StringWriter());
        try {
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            SOAPBody body = context.getMessage().getSOAPBody();
            DOMSource source = new DOMSource(body);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getWriter().toString();
    }

    public boolean handleFault(SOAPMessageContext context) {
        String soapPart;
        soapPart = getSoapPartXML(context);
        System.out.println("\nRequest:\n" + request.get() + "\nResponse/fault:\n" + soapPart);
        request.set(null);
        return true;
    }

    public void close(MessageContext context) {
        System.out.println("void close(MessageContext context)");
    }

}
