package huaminglin.demo.hazelcastclient.queue;

import com.hazelcast.collection.IQueue;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldWorker implements Runnable {

  private Thread thread;

  private volatile boolean active = true;

  @Autowired
  private IQueue<WorkerItem> queue;

  @PostConstruct
  public void initialize() {
    thread = new Thread(this);
    thread.setName("HelloWorldWorker");
    thread.start();
  }

  @Override
  public void run() {
    while (active) {
      WorkerItem item;
      try {
        item = queue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
      System.out.println(item);
    }
    System.out.println("Exit");
  }

  public boolean isActive() {
    return active;
  }

  public void shutdown() {
    active = false;
    thread.interrupt();
  }
}
