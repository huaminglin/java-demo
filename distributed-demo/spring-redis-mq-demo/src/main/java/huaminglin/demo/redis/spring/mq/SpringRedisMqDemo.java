package huaminglin.demo.redis.spring.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@SpringBootApplication
public class SpringRedisMqDemo {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringRedisMqDemo.class, args);
    SpringRedisMqService springRedisMqService = context.getBean(SpringRedisMqService.class);
    springRedisMqService.publish("Hello world");
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

  @Bean
  RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory,
      MessageListener messageListener) {
    RedisMessageListenerContainer container
        = new RedisMessageListenerContainer();
    container.setConnectionFactory(redisConnectionFactory);
    container.addMessageListener(messageListener, topic());
    return container;
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("messageQueue");
  }
}
