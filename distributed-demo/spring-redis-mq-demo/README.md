## Demo Redis as Message Queue

## redis-demo/monitor.sh

```
xxx.156448 [0 172.21.0.1:36610] "SUBSCRIBE" "messageQueue"
xxx.194095 [0 172.21.0.1:36606] "PUBLISH" "messageQueue" "Hello world"
xxx.195869 [0 172.21.0.1:36610] "UNSUBSCRIBE" ""
```

## How RedisMessageListenerContainer works

org.springframework.data.redis.listener.RedisMessageListenerContainer
private @Nullable Executor subscriptionExecutor;
private @Nullable Executor taskExecutor;

```
onMessage:11, SpringRedisMqListener (huaminglin.demo.redis.spring.mq)
executeListener:250, RedisMessageListenerContainer (org.springframework.data.redis.listener)
processMessage:240, RedisMessageListenerContainer (org.springframework.data.redis.listener)
lambda$dispatchMessage$0:986, RedisMessageListenerContainer (org.springframework.data.redis.listener)
run:-1, 963522818 (org.springframework.data.redis.listener.RedisMessageListenerContainer$$Lambda$342)
run:834, Thread (java.lang)
```

```
"lettuce-nioEventLoop-4-2@4545" daemon prio=5 tid=0x15 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.data.redis.listener.RedisMessageListenerContainer$DispatchMessageListener.onMessage(RedisMessageListenerContainer.java:968)
	  at org.springframework.data.redis.connection.lettuce.LettuceMessageListener.message(LettuceMessageListener.java:43)
	  at org.springframework.data.redis.connection.lettuce.LettuceMessageListener.message(LettuceMessageListener.java:29)
	  at io.lettuce.core.pubsub.PubSubEndpoint.notifyListeners(PubSubEndpoint.java:185)
	  at io.lettuce.core.pubsub.PubSubEndpoint.notifyMessage(PubSubEndpoint.java:174)
	  at io.lettuce.core.pubsub.PubSubCommandHandler.doNotifyMessage(PubSubCommandHandler.java:214)
	  at io.lettuce.core.pubsub.PubSubCommandHandler.decode(PubSubCommandHandler.java:120)
	  at io.lettuce.core.protocol.CommandHandler.channelRead(CommandHandler.java:556)
	  at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	  at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	  at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
	  at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1421)
	  at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
	  at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
	  at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:930)
	  at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:163)
	  at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:697)
	  at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:632)
	  at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:549)
	  at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:511)
	  at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:918)
	  at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	  at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	  at java.lang.Thread.run(Thread.java:834)
```

org.springframework.data.redis.listener.RedisMessageListenerContainer$DispatchMessageListener

```
	private void dispatchMessage(Collection<MessageListener> listeners, final Message message, final byte[] pattern) {
		final byte[] source = (pattern != null ? pattern.clone() : message.getChannel());

		for (final MessageListener messageListener : listeners) {
			taskExecutor.execute(() -> processMessage(messageListener, message, source));
		}
	}
```

Note:

RedisMessageListenerContainer$DispatchMessageListener runs the target message listener with task executor.

DispatchMessageListener runs in the thread "lettuce-nioEventLoop-4-2@4545".

MessageListener runs in the thread "RedisContainer-2".
