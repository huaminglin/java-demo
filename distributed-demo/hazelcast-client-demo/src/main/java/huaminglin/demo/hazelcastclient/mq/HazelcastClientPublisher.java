package huaminglin.demo.hazelcastclient.mq;

import com.hazelcast.topic.ITopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastClientPublisher {

  @Autowired
  private ITopic topic;

  public void sendMessage(String message) {
    topic.publish(message);
  }
}
