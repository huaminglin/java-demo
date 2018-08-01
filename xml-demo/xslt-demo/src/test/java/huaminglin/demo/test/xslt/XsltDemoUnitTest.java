package huaminglin.demo.test.xslt;

import huaminglin.demo.xslt.XsltDemo;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XsltDemoUnitTest {

    @Test
    public void testParseUserFromBytes() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        String result = XsltDemo.executeXslt("stylesheet/style.xsl", "xml/input.xml");
        assertTrue(result.contains("<Name>name1</Name>"));
    }
}
