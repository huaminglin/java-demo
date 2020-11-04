package huaminglin.demo.redis.spring.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class SpringRedisMqService {

  @Autowired
  StringRedisTemplate redisTemplate;

  @Autowired
  RedisMessageListenerContainer container;

  @Autowired
  ChannelTopic topic;

  public void publish(String message) {
    redisTemplate.convertAndSend(topic.getTopic(), message);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    container.stop();
  }
}
