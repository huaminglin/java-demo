package huaminglin.demo.hazelcastclient.queue;

import com.hazelcast.collection.IQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldOffer {

  @Autowired
  private IQueue<WorkerItem> queue;

  public void offer(String name , String value) throws InterruptedException {
    WorkerItem item = new WorkerItem();
    item.setName(name);
    item.setValue(value);
    queue.put(item);
  }
}
