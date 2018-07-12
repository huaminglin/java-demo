package huaminglin.demo.httpclient;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class HttpClientDemo {
    private static String url = "http://127.0.0.1:8080/";

    private static CloseableHttpClient client = new HttpClientConfiguration().getHttpClient();

    public String getContent() throws IOException {
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "text/html");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    public static void main(String[] args) throws IOException {
        try (Hoverfly hoverfly = new Hoverfly(HoverflyConfig.localConfigs().proxyLocalHost(), SIMULATE)) {
            hoverfly.start();
            hoverfly.simulate(dsl(
                    service("127.0.0.1:8080")
                            .get("/")
                            .willReturn(success("Mock from Hover Fly", "text/html"))
            ));
            String content = new HttpClientDemo().getContent();
            System.out.println(content);
        }
    }
}
