package huaminglin.demo.encoding.client;


import java.util.Collections;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EncodingClientRestTemplateDemo {

  public static void main(String[] args) {
    String uri = "http://127.0.0.1:8080/path/{p1}?name=a+b c&p2={p1}";
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(
        uri, HttpMethod.GET, null,
        String.class, Collections.singletonMap("p1", "a+b c"));
    System.out.println(response.getBody());
  }

}
