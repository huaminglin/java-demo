package huaminglin.demo.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcServerDemo {

  public static void main(String[] args)  {
    SpringApplication.run(GrpcServerDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

}
