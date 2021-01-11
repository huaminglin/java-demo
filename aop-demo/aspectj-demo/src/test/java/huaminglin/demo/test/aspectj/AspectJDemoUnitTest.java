package huaminglin.demo.test.aspectj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import huaminglin.demo.aspectj.BusinessLogic;
import org.junit.jupiter.api.Test;

public class AspectJDemoUnitTest {

  @Test
  public void testAspectJDemo() {
    String value = new BusinessLogic().myMethod("abc");
    assertEquals("{[abc]}", value);
  }
}
