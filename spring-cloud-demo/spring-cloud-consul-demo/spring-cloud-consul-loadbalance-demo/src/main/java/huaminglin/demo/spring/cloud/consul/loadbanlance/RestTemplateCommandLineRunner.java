package huaminglin.demo.spring.cloud.consul.loadbanlance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateCommandLineRunner implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        String result = this.restTemplate.getForObject("http://spring-cloud-consul-server-demo/hello", String.class);
        System.out.println("Response: " + result);
    }

}
