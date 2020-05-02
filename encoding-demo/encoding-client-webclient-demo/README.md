# How WebClient construct request URL

## Run the application and check the log

18:37:50.176 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d] Created a new pooled channel, now 1 active connections and 0 inactive connections
18:37:50.219 [reactor-http-epoll-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkAccessible: true
18:37:50.219 [reactor-http-epoll-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkBounds: true
18:37:50.220 [reactor-http-epoll-1] DEBUG io.netty.util.ResourceLeakDetectorFactory - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@3ce783d5
18:37:50.226 [reactor-http-epoll-1] DEBUG reactor.netty.channel.BootstrapHandlers - [id: 0xdb94c90d] Initialized pipeline DefaultChannelPipeline{(BootstrapHandlers$BootstrapInitializerHandler#0 = reactor.netty.channel.BootstrapHandlers$BootstrapInitializerHandler), (reactor.left.httpCodec = io.netty.handler.codec.http.HttpClientCodec), (reactor.left.decompressor = io.netty.handler.codec.http.HttpContentDecompressor), (reactor.right.reactiveBridge = reactor.netty.channel.ChannelOperationsHandler)}
18:37:50.233 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] Registering pool release on close event for channel
18:37:50.233 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] Channel connected, now 1 active connections and 0 inactive connections
18:37:50.234 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] onStateChange(PooledConnection{channel=[id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080]}, [connected])
18:37:50.242 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] onStateChange(GET{uri=/, connection=PooledConnection{channel=[id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080]}}, [configured])
18:37:50.243 [reactor-http-epoll-1] DEBUG reactor.netty.http.client.HttpClientConnect - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] Handler is being applied: {uri=http://127.0.0.1:8080/path/a%2Bb%20c?name=a+b%20c&p2=a%2Bb%20c, method=GET}
18:37:50.246 [reactor-http-epoll-1] DEBUG reactor.netty.resources.PooledConnectionProvider - [id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080] onStateChange(GET{uri=/path/a%2Bb%20c?name=a+b%20c&p2=a%2Bb%20c, connection=PooledConnection{channel=[id: 0xdb94c90d, L:/127.0.0.1:35594 - R:127.0.0.1/127.0.0.1:8080]}}, [request_prepared])

Note:
{uri=http://127.0.0.1:8080/path/a%2Bb%20c?name=a+b%20c&p2=a%2Bb%20c, method=GET}
From the log we know the '+' in the URI variable is encoded.

## java.lang.UnsupportedOperationException: Reflective setAccessible(true) disabled

java.lang.UnsupportedOperationException: Reflective setAccessible(true) disabled
	at io.netty.util.internal.ReflectionUtil.trySetAccessible(ReflectionUtil.java:31)
	at io.netty.util.internal.PlatformDependent0$4.run(PlatformDependent0.java:225)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at io.netty.util.internal.PlatformDependent0.<clinit>(PlatformDependent0.java:219)
	at io.netty.util.internal.PlatformDependent.isAndroid(PlatformDependent.java:289)
	at io.netty.util.internal.PlatformDependent.<clinit>(PlatformDependent.java:92)
	at io.netty.util.ConstantPool.<init>(ConstantPool.java:32)
	at io.netty.channel.ChannelOption$1.<init>(ChannelOption.java:36)
	at io.netty.channel.ChannelOption.<clinit>(ChannelOption.java:36)
	at reactor.netty.tcp.TcpClient.<clinit>(TcpClient.java:635)
	at reactor.netty.http.client.HttpClient.<clinit>(HttpClient.java:1155)
	at org.springframework.http.client.reactive.ReactorClientHttpConnector.<init>(ReactorClientHttpConnector.java:57)
	at org.springframework.web.reactive.function.client.DefaultWebClientBuilder.getOrInitConnector(DefaultWebClientBuilder.java:273)
	at org.springframework.web.reactive.function.client.DefaultWebClientBuilder.build(DefaultWebClientBuilder.java:256)
	at org.springframework.web.reactive.function.client.WebClient.create(WebClient.java:140)
	at huaminglin.demo.encoding.client.EncodingClientWebClientDemo.main(EncodingClientWebClientDemo.java:11)

Note: The exception is handled. Not sure why the stack trace is printed.

## From WebClient to HierarchicalUriComponents

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.web.util.HierarchicalUriComponents.encodeTemplate(HierarchicalUriComponents.java:256)
	  at org.springframework.web.util.UriComponentsBuilder.buildInternal(UriComponentsBuilder.java:406)
	  at org.springframework.web.util.UriComponentsBuilder.build(UriComponentsBuilder.java:391)
	  at org.springframework.web.util.UriComponentsBuilder.build(UriComponentsBuilder.java:380)
	  at org.springframework.web.util.DefaultUriBuilderFactory$DefaultUriBuilder.build(DefaultUriBuilderFactory.java:380)
	  at org.springframework.web.util.DefaultUriBuilderFactory.expand(DefaultUriBuilderFactory.java:200)
	  at org.springframework.web.reactive.function.client.DefaultWebClient$DefaultRequestBodyUriSpec.uri(DefaultWebClient.java:182)
	  at org.springframework.web.reactive.function.client.DefaultWebClient$DefaultRequestBodyUriSpec.uri(DefaultWebClient.java:151)
	  at huaminglin.demo.encoding.client.EncodingClientWebClientDemo.main(EncodingClientWebClientDemo.java:15)

## org.springframework.web.util.UriBuilderFactory and TEMPLATE_AND_VALUES

org.springframework.web.reactive.function.client.DefaultWebClient
	private final UriBuilderFactory uriBuilderFactory;

org.springframework.web.reactive.function.client.DefaultWebClient
    DefaultWebClient()
		this.uriBuilderFactory = (factory != null ? factory : new DefaultUriBuilderFactory());

org.springframework.web.util.DefaultUriBuilderFactory
	private EncodingMode encodingMode = EncodingMode.TEMPLATE_AND_VALUES;

org.springframework.web.util.DefaultUriBuilderFactory.DefaultUriBuilder
		private UriComponentsBuilder initUriComponentsBuilder(String uriTemplate) {

			if (encodingMode.equals(EncodingMode.TEMPLATE_AND_VALUES)) {
				result.encode();
			}
		}

## Conclusion: WebClient supports to encode reserved characters.
