package huaminglin.demo.hazelcastclient.scheduler;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import java.io.Serializable;
import java.util.Date;

public class HelloWorldTask implements Runnable, Serializable, HazelcastInstanceAware {
  static final long serialVersionUID = 234L;

  private String topic;
  private String message;

  private transient HazelcastInstance hazelcastInstance;

  @Override
  public void run() {
    System.out.println(new Date() + ": Public " + message + " to topic: " + topic);
    hazelcastInstance.getTopic(topic).publish(message);
  }

  @Override
  public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
    this.hazelcastInstance = hazelcastInstance;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
