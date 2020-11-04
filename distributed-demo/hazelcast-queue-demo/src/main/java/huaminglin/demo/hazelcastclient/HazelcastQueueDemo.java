package huaminglin.demo.hazelcastclient;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import huaminglin.demo.hazelcastclient.queue.HelloWorldOffer;
import huaminglin.demo.hazelcastclient.queue.HelloWorldWorker;
import huaminglin.demo.hazelcastclient.queue.WorkerItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HazelcastQueueDemo {

  public static void main(String[] args) throws InterruptedException {
    ConfigurableApplicationContext context = SpringApplication.run(HazelcastQueueDemo.class, args);
    HelloWorldOffer offer = context.getBean(HelloWorldOffer.class);
    // It seems that world01 is lost for unknown reason.
    // Try to sleep to avoid the lost, but fail.
    offer.offer("hello", "world01");
    Thread.sleep(1000);
    offer.offer("hello", "world02");
    Thread.sleep(1000);
    offer.offer("hello", "world03");
    Thread.sleep(1000);
    offer.offer("hello", "world04");
    Thread.sleep(1000);
    context.getBean(HelloWorldWorker.class).shutdown();
    context.getBean(HazelcastInstance.class).shutdown();
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

  @Bean
  public IQueue<WorkerItem> queue(HazelcastInstance hazelcastInstance) {
    return hazelcastInstance.<WorkerItem>getQueue("queue1");
  }
}
