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

@WebServiceProvider
@BindingType(value= HTTPBinding.HTTP_BINDING)
public class RestWSProvider implements Provider<Source> {
    @javax.annotation.Resource(type=Object.class)
    protected WebServiceContext wsContext;

    @Override
    public Source invoke(Source request) {
        MessageContext messageContext = wsContext.getMessageContext();
        String path = (String) messageContext.get(MessageContext.PATH_INFO);
        String content = "<content>" + path + "</content>";
        // xml must be valid xml.
        // XML reader error: javax.xml.stream.XMLStreamException: ParseError at [row,col]:[1,1]
        // Message: Content is not allowed in prolog.
        // com.sun.xml.ws.streaming.XMLStreamReaderException
        // To disable this feature, set com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace system property to false
        return new StreamSource(new StringReader(content));
    }
}
