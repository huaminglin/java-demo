package huaminglin.demo.wsdl.server;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {

  private ThreadLocal<String> request = new ThreadLocal<String>();

  public Set<QName> getHeaders() {
    return Collections.emptySet();
  }

  public boolean handleMessage(SOAPMessageContext context) {
    try {
      return processMessage(context);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  private boolean processMessage(SOAPMessageContext context) throws SOAPException {
    Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    if (outboundProperty) {// Server generates response to client
      handleResponse(context);
    } else {
      handleRequest(context);
    }
    return true;
  }

  public boolean handleRequest(final SOAPMessageContext context) throws SOAPException {
    System.out.println("\nhandleRequest");
    String soapPart = request.get();
    if (soapPart != null) {
      System.out.println(String.format("Request:\n%s\nResponse:\nNo response.", soapPart));
      request.remove();
    }
    soapPart = getSoapPartXml(context);
    request.set(soapPart);
    return true;
  }

  public boolean handleResponse(final SOAPMessageContext context) throws SOAPException {
    System.out.println("\nhandleResponse");
    String soapPart = getSoapPartXml(context);
    System.out.println(String.format("Request:\n%s\nResponse:\n%s", request.get(), soapPart));
    request.remove();
    return true;
  }

  private String getSoapPartXml(SOAPMessageContext context) {
    TransformerFactory factory = TransformerFactory.newInstance();
    StreamResult result = new StreamResult(new StringWriter());
    try {
      Transformer transformer = factory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource((Node) context.getMessage().getSOAPBody().getChildNodes());
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.getWriter().toString();
  }

  public boolean handleFault(SOAPMessageContext context) {
    String soapPart = getSoapPartXml(context);
    System.out
        .println(String.format("Request:\n%s\nResponse(fault):\n%s", request.get(), soapPart));
    request.set(null);
    return true;
  }

  public void close(MessageContext arg0) {
  }

}
