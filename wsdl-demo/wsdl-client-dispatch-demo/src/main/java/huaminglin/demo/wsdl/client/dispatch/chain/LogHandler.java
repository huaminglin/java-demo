package huaminglin.demo.wsdl.client.dispatch.chain;

import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class LogHandler implements LogicalHandler<LogicalMessageContext> {

  private String getContentAsString(LogicalMessageContext context) {
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = null;
    try {
      transformer = factory.newTransformer();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }
    StringWriter sw = new StringWriter();
    try {
      transformer.transform(context.getMessage().getPayload(), new StreamResult(sw));
    } catch (TransformerException e) {
      e.printStackTrace();
    }
    return sw.toString();
  }

  public void close(MessageContext context) {
    System.out.println("void close(MessageContext context)");
  }

  public boolean handleFault(LogicalMessageContext context) {
    System.out.println("boolean handleFault(LogicalMessageContext context)");
    System.out.println(getContentAsString(context));
    return true;
  }

  public boolean handleMessage(LogicalMessageContext context) {
    System.out.println("boolean handleMessage(LogicalMessageContext context)");
    System.out.println(getContentAsString(context));
    return true;
  }
}
