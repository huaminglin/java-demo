package huaminglin.demo.wsdl.server;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.util.Map;

@WebServiceProvider
@BindingType(value= HTTPBinding.HTTP_BINDING)
public class RestWSProvider implements Provider<Source> {
    @javax.annotation.Resource(type=Object.class)
    protected WebServiceContext wsContext;

    private String getString(Source source) {
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

    private String makeMsg(MessageContext messageContext) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request method: " + messageContext.get(MessageContext.HTTP_REQUEST_METHOD));
        sb.append(", Path: "+ messageContext.get(MessageContext.PATH_INFO));
        sb.append(", Query string: " + messageContext.get(MessageContext.QUERY_STRING));
        sb.append(", User agent: " + ((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("user-agent"));
        sb.append(", Content type: " + ((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("content-type"));
        sb.append(", Http accept: " + ((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("accept"));
        sb.append(", Http host: " + ((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("host"));
        sb.append(", Connection: " + ((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("connection"));
        System.out.println(messageContext);
        return sb.toString();
      }

    @Override
    public Source invoke(Source request) {
        String strRequest = getString(request);
        MessageContext messageContext = wsContext.getMessageContext();
        String content = "<response><content>" + makeMsg(messageContext) + "</content><request><![CDATA[" + strRequest + "]]]></request></response>";
        // xml must be valid xml.
        // XML reader error: javax.xml.stream.XMLStreamException: ParseError at [row,col]:[1,1]
        // Message: Content is not allowed in prolog.
        // com.sun.xml.ws.streaming.XMLStreamReaderException
        // To disable this feature, set com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace system property to false
        return new StreamSource(new StringReader(content));
    }
}
