package huaminglin.demo.test.httpclient;

import huaminglin.demo.httpclient.HttpClientDemo;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflyCore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertEquals;

@HoverflyCore(
        mode = HoverflyMode.SIMULATE,
        config = @HoverflyConfig(adminPort = 9000, proxyPort = 9001, proxyLocalHost = true)
)
@ExtendWith(HoverflyExtension.class)
public class HttpClientDemoDslUnitTest {

    @Test
    public void testHttpClientConnection(Hoverfly hoverfly) throws IOException {
        hoverfly.simulate(dsl(
                service("127.0.0.1:8080")
                        .get("/")
                        .willReturn(success("Mock from Hover Fly", "text/html"))
        ));
        String content = new HttpClientDemo().getContent();
        assertEquals("Mock from Hover Fly", content);
    }
}
