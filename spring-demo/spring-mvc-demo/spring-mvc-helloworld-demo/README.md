This demo is used to show how to setup a simple spring mvc project with minimal configuration.

No Spring xml configuration.
No web.xml configuration.

## Run a http web server
mvn jetty:run
http://127.0.0.1:8080/hello/


## hamcrest dependency
"cannot access org.hamcrest.Matcher" error happens on compile time.
How does spring-test have dependency on  hamcrest?
spring-test-5.0.7.RELEASE.jar!/org/springframework/test/web/servlet/result/ContentResultMatchers.class
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

## Spring mvc unit test
mvn test
