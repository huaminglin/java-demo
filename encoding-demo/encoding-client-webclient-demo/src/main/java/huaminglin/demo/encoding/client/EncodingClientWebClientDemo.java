package huaminglin.demo.encoding.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class EncodingClientWebClientDemo {

    public static void main(String[] args) {
        WebClient client1 = WebClient.create();
        WebClient.RequestBodySpec bodySpec = client1
                .method(HttpMethod.GET)
                .uri("http://127.0.0.1:8080/path/{p1}?name=a+b c&p2={p1}", Collections.singletonMap("p1", "a+b c"));
        Mono<ClientResponse> exchange = bodySpec.exchange();
        ClientResponse block = exchange.block();
        Mono<String> stringMono = block.bodyToMono(String.class);
        String message = stringMono.block();
        System.out.println(message);
    }

}
