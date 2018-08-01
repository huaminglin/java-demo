package huaminglin.demo.test.xpath;

import huaminglin.demo.xpath.XPathDemo;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XPathDemoUnitTest {

    @Test
    public void testParseUserFromBytes() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        String result = XPathDemo.getStudentByName("xml/input.xml", "name2");
        assertTrue(result.contains("<Name>name2</Name>"));
    }
}
