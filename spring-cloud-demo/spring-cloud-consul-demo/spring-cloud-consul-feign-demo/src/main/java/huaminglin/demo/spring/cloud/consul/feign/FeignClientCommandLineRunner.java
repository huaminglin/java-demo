package huaminglin.demo.spring.cloud.consul.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FeignClientCommandLineRunner implements CommandLineRunner {

  @Autowired
  private HelloServiceProxy helloServiceProxy;

  @Override
  public void run(String... args) throws Exception {
    String result = helloServiceProxy.hello();
    System.out.println("Feign response: " + result);
  }

}
