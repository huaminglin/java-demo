package huaminglin.demo.grpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GrpcClientDemo {

  public static void main(String[] args)  {
    ConfigurableApplicationContext context = SpringApplication.run(GrpcClientDemo.class, args);
    HelloServiceClient client = context.getBean(HelloServiceClient.class);
    String greeting = client.hello();
    System.out.println(greeting);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
