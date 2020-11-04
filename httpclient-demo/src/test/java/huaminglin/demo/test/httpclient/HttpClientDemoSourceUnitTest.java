package huaminglin.demo.test.httpclient;

import static org.junit.jupiter.api.Assertions.assertTrue;

import huaminglin.demo.httpclient.HttpClientDemo;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(HoverflyExtension.class)
@HoverflySimulate(
    config = @HoverflyConfig(adminPort = 9000, proxyPort = 9001, proxyLocalHost = true),
    source = @HoverflySimulate.Source(value = "simulation.json",
        type = HoverflySimulate.SourceType.CLASSPATH),
    enableAutoCapture = false
)
// @HoverflyCore(mode = HoverflyMode.SIMULATE) can't work with @HoverflySimulate. @HoverflySimulate has higher priority.
public class HttpClientDemoSourceUnitTest {

  @Test
  public void testHttpClientConnection() throws IOException {
    String content = new HttpClientDemo().getContent();
    assertTrue(content.contains("Directory listing for"));
  }
}
