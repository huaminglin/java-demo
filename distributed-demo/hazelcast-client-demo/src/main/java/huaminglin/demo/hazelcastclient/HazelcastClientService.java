package huaminglin.demo.hazelcastclient;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastClientService {

  @Autowired
  private HazelcastInstance hazelcastInstance;

  public void sendMessage(String topic, String message) {
    ITopic<Object> iTopic = hazelcastInstance.getTopic(topic);
    iTopic.publish(message);
  }
}
