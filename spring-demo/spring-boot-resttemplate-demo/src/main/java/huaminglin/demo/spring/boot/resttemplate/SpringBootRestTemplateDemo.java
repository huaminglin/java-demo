package huaminglin.demo.spring.boot.resttemplate;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootRestTemplateDemo {
    private static String url = "http://127.0.0.1:8080/";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootRestTemplateDemo.class, args);
        System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
            try (Hoverfly hoverfly = new Hoverfly(HoverflyConfig.localConfigs().proxyLocalHost(), SIMULATE)) {
                hoverfly.start();
                hoverfly.simulate(dsl(
                        service("127.0.0.1:8080")
                                .get("/")
                                .willReturn(success("{\"name\": \"name1\"}", "application/json"))
                ));
                System.out.println(System.getProperty("http.proxyHost"));
                System.out.println(System.getProperty("http.proxyPort"));
                Student student = restTemplate.getForObject(
                    url, Student.class);
                System.out.println(student.getName());
            }
		};
	}
}
