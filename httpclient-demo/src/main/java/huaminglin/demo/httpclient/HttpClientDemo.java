package huaminglin.demo.httpclient;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.config.LocalHoverflyConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class HttpClientDemo {
    private static String url = "http://192.168.1.110/mypage.html";

    // For some reason the following configuration doesn't support "http.proxyHost" system property setted by Hoverfly
//    private static CloseableHttpClient client = new HttpClientConfiguration().getHttpClient();
    private static CloseableHttpClient client = HttpClients.createSystem();

    public String getContent() throws IOException {
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "text/html");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    public static void main(String[] args) throws IOException {
        LocalHoverflyConfig config = new LocalHoverflyConfig();
        config.proxyLocalHost(true);
        try (Hoverfly hoverfly = new Hoverfly(config, SIMULATE)) {
            hoverfly.start();
            hoverfly.simulate(dsl(
                    service("192.168.1.110")
                            .get("/mypage.html")
                            .willReturn(success("Mock from Hover Fly", "text/html"))
            ));
            String content = new HttpClientDemo().getContent();
            System.out.println(content);
        }
    }
}
