This demo is used to show how to customize Spring MVC features.

## Run a http web server
mvn jetty:run
http://127.0.0.1:8080/custom/

## Run a http web server in debug mode
mvnDebug jetty:run

## Spring mvc unit test
mvn test

## Conclusion
@EnableWebMvc + WebMvcConfigurer can be used to add HttpMessageConverter.
