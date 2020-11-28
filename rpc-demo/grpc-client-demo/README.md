# grpc client demo

## grpc-client-spring-boot-autoconfigure

If we remove grpc-client-spring-boot-autoconfigure from dependency list, the following exception happens:

```
Exception in thread "main" io.grpc.ManagedChannelProvider$ProviderNotFoundException: No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact
        at io.grpc.ManagedChannelProvider.provider(ManagedChannelProvider.java:60)
        at io.grpc.ManagedChannelBuilder.forAddress(ManagedChannelBuilder.java:39)
        at huaminglin.demo.grpc.client.HelloServiceClient.hello(HelloServiceClient.java:14)
        at huaminglin.demo.grpc.client.GrpcClientDemo.main(GrpcClientDemo.java:13)
```
