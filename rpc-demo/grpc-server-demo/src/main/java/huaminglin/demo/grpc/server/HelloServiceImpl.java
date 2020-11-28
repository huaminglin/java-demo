package huaminglin.demo.grpc.server;

import huaminglin.demo.grpc.HelloRequest;
import huaminglin.demo.grpc.HelloResponse;
import huaminglin.demo.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

  @Override
  public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    HelloResponse response = HelloResponse.newBuilder().setGreeting("Greeting " + request.getFirstName()).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
