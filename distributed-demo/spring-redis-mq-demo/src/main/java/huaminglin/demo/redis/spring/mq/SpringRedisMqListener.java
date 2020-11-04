package huaminglin.demo.redis.spring.mq;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRedisMqListener implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    System.out.println("Message received: " + message);
  }
}
