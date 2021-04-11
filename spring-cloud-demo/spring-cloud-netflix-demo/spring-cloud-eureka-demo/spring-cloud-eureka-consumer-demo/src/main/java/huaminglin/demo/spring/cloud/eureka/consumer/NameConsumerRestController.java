package huaminglin.demo.spring.cloud.eureka.consumer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class NameConsumerRestController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/fetch")
  public String serviceInstancesByApplicationName() throws IOException, InterruptedException {
    String applicationName = "EUREKA-PRODUCER";
    final URI uri = discoveryClient.getInstances(applicationName).get(0).getUri();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri.toString() + "/name")).build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    return response.body();
  }
}
