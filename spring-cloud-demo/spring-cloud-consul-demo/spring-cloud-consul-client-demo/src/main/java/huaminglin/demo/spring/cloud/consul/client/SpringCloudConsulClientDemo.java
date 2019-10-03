package huaminglin.demo.spring.cloud.consul.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudConsulClientDemo implements CommandLineRunner {
    @Autowired
    DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulClientDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Services:");
        discoveryClient.getServices().forEach(System.out::println);
        System.out.println("spring-cloud-consul-server-demo:");
        discoveryClient.getInstances("spring-cloud-consul-server-demo").forEach(System.out::println);
    }
}
