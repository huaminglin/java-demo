package huaminglin.demo.hazelcastclient.mq;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastClientListener implements MessageListener {
  @Autowired
  private HazelcastInstance hazelcastInstance;

  @Override
  public void onMessage(Message message) {
    Object messageObject = message.getMessageObject();
    System.out.println(messageObject);
    if (messageObject.equals("quit")) {
      System.out.println("Shut down Hazelcast client instance on quit message.");
      hazelcastInstance.shutdown();
    }
  }
}
