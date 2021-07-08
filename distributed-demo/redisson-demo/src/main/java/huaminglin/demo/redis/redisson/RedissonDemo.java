package huaminglin.demo.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonDemo {

  private static void demoKeys(RedissonClient client) {
    System.out.println("demoKeys()");
    RKeys keys = client.getKeys();
    System.out.println("Count: " + keys.count());
    for (String key : keys.getKeys()) {
      System.out.println(key);
    }
  }

  private static void demoAtomicLong(RedissonClient client) {
    System.out.println("demoAtomicLong()");
    RAtomicLong myLong = client.getAtomicLong("rlong");
    myLong.set(6);
    boolean compareAndSet = myLong.compareAndSet(6, 27);
    System.out.println(compareAndSet);
    compareAndSet = myLong.compareAndSet(6, 27);
    System.out.println(compareAndSet);
  }

  private static void demoRBucket(RedissonClient client) {
    System.out.println("demoRBucket()");
    {
      RBucket<MyEntity> bucket = client.getBucket("rbucket");
      MyEntity myEntity = new MyEntity();
      bucket.set(myEntity);
      myEntity.setName("myname"); // This update is not sent to Redis server
    }
    {
      RBucket<MyEntity> bucket = client.getBucket("rbucket");
      MyEntity myEntity = bucket.get();
      System.out.println(myEntity.getName());
    }
    {
      RBucket<MyEntity> bucket = client.getBucket("rbucket");
      MyEntity myEntity = bucket.get();
      myEntity.setName("myname");
      bucket.set(myEntity);// This update is sent to Redis server
    }
    {
      RBucket<MyEntity> bucket = client.getBucket("rbucket");
      MyEntity myEntity = bucket.get();
      System.out.println(myEntity.getName());
    }
  }

  private static void demoLock(RedissonClient client) {
    client.getLock("mylock");
  }

  public static void main(String[] args) throws InterruptedException {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    RedissonClient client = Redisson.create(config);
    demoKeys(client);
    demoAtomicLong(client);
    demoRBucket(client);
    demoLock(client);
    client.shutdown();
  }
}
