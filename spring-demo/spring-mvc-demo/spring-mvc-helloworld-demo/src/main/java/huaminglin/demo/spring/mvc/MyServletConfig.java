package huaminglin.demo.spring.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "huaminglin.demo.spring.mvc.controller"
})
public class MyServletConfig {

}
