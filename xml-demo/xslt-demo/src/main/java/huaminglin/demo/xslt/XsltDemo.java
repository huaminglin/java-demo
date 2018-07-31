package huaminglin.demo.xslt;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class XsltDemo {
    public static String xslt(InputStream style, InputStream model) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        StreamSource styleSource = new StreamSource(style);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource modelSource = new StreamSource(model);
        StringWriter sw = new StringWriter();
        StreamResult streamResult = new StreamResult(sw);

        transformer.transform(modelSource, streamResult);

        return sw.toString();
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        InputStream inputStreamStyle = XsltDemo.class.getClassLoader().getResourceAsStream("stylesheet/style.xsl");
        InputStream inputStreamModel = XsltDemo.class.getClassLoader().getResourceAsStream("xml/input.xml");
        String result = xslt(inputStreamStyle, inputStreamModel);
        System.out.println(result);
        // TODO No XSLT is executed actually, the style.xls content is printed.
    }
}
