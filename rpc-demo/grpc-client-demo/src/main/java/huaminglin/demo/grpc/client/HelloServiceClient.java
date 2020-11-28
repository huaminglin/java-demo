package huaminglin.demo.grpc.client;

import huaminglin.demo.grpc.HelloRequest;
import huaminglin.demo.grpc.HelloResponse;
import huaminglin.demo.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceClient {

  public String hello() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
        .usePlaintext().build();
    HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
    HelloResponse helloResponse = stub
        .hello(HelloRequest.newBuilder().setFirstName("Jim").setLastName("Green").build());
    channel.shutdown();
    return helloResponse.getGreeting();
  }
}
