package huaminglin.demo.test.xslt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import huaminglin.demo.xslt.XsltDemo;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class XsltDemoUnitTest {

  @Test
  public void testParseUserFromBytes()
      throws IOException, TransformerException, ParserConfigurationException, SAXException {
    String result = XsltDemo.executeXslt("stylesheet/style.xsl", "xml/input.xml");
    assertTrue(result.contains("<Name>name1</Name>"));
  }
}
