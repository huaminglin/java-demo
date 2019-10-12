package huaminglin.demo.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudGatewayDemo {
    @Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("path_route", r -> r.path("/").uri("http://127.0.0.1:9091")).build();
	}

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayDemo.class, args);
	}
}
