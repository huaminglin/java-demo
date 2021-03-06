package huaminglin.demo.xslt;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XsltDemo {

  public static String xslt(InputStream style, InputStream model)
      throws TransformerException, ParserConfigurationException, IOException, SAXException {
    StreamSource styleSource = new StreamSource(style);

    // The following codes can't be used as alternative for above code.
//        Document documentStyle = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(style);
//        DOMSource styleSource = new DOMSource(documentStyle);

    Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
    transformer.setOutputProperty(OutputKeys.METHOD, "html");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    // StreamSource modelSource = new StreamSource(model); // This can be used as the alternative for the below code.
    Document documentModel = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(model);
    DOMSource modelSource = new DOMSource(documentModel);
    StringWriter sw = new StringWriter();
    StreamResult streamResult = new StreamResult(sw);

    transformer.transform(modelSource, streamResult);

    return sw.toString();
  }


  public static String executeXslt(String styleResourcePath, String modelResourcePath)
      throws TransformerException, ParserConfigurationException, IOException, SAXException {
    InputStream inputStreamStyle = XsltDemo.class.getClassLoader()
        .getResourceAsStream(styleResourcePath);
    InputStream inputStreamModel = XsltDemo.class.getClassLoader()
        .getResourceAsStream(modelResourcePath);
    return xslt(inputStreamStyle, inputStreamModel);
  }

  public static void main(String[] args)
      throws TransformerException, ParserConfigurationException, IOException, SAXException {
    String result = executeXslt("stylesheet/style.xsl", "xml/input.xml");
    System.out.println(result);
  }
}
