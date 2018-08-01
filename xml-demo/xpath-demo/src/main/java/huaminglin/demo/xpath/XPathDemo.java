package huaminglin.demo.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class XPathDemo {

    private static String node2String(Node node) throws TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return writer.toString();
    }

    private static Node getStudentByName(Document doc, XPath xpath, String name) {
        try {
            XPathExpression expr = xpath.compile("/School/Student/Name[text()='" + name + "']/..");
            return (Node) expr.evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStudentByName(String resourcePath, String name) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        InputStream model = XPathDemo.class.getClassLoader().getResourceAsStream(resourcePath);
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        Document documentModel = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(model);
        Node node = getStudentByName(documentModel, xpath, name);
        return node2String(node);
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String result = getStudentByName("xml/input.xml", "name1");
        System.out.println(result);
    }
}
