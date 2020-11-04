package huaminglin.demo.test.xpath;

import static org.junit.jupiter.api.Assertions.assertTrue;

import huaminglin.demo.xpath.XPathDemo;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class XPathDemoUnitTest {

  @Test
  public void testParseUserFromBytes()
      throws IOException, TransformerException, ParserConfigurationException, SAXException {
    String result = XPathDemo.getStudentByName("xml/input.xml", "name2");
    assertTrue(result.contains("<Name>name2</Name>"));
  }
}
