package huaminglin.demo.test.jdk9;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import huaminglin.demo.jdk9.JDK9Demo;
import jdk.incubator.http.HttpClient;
import org.junit.jupiter.api.Test;

public class JDK9DemoUnitTest {

  @Test
  public void testUserBytes() {
    HttpClient client = JDK9Demo.getClient();
    assertNotNull(client);
  }
}
