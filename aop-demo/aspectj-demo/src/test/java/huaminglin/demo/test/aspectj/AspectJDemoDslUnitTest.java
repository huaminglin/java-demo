package huaminglin.demo.test.aspectj;


import static org.junit.jupiter.api.Assertions.assertEquals;

import huaminglin.demo.aspectj.BusinessLogic;
import org.junit.jupiter.api.Test;


public class AspectJDemoDslUnitTest {

  @Test
  public void testHttpClientConnection() {
    String value = new BusinessLogic().execute("abc");
    assertEquals("{[abc]}", value);
  }
}
