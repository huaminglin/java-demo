package huaminglin.demo.redis.jedis;

import redis.clients.jedis.Jedis;

public class HyperLogLogDemo {

  private static void demoCardinalityUnEqual(Jedis jedis) {
    String key = "hll-huge-v7-3865";
    jedis.del(key);
    for (int i = 1; i < 4; i++) {
      jedis.pfadd(key, key + "-" + i);
    }
    long pfcount = jedis.pfcount(key); // the precise count is 3, got 2.
    System.out.println(key + ": " + pfcount);
  }

  public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    // findFirstCardinalityUnEqual(10000);
    demoCardinalityUnEqual(jedis);
  }

  private void findFirstCardinalityUnEqual(Jedis jedis, int loopCount) {
    System.out.println("findFirstCardinalityUnEqual()");
    for (int rand = 0; rand < loopCount; rand++) {
      String key = "hll-huge-v7-" + rand;
      for (int i = 1; i < Integer.MAX_VALUE; i++) {
        jedis.pfadd(key, key + "-" + i);
        long pfcount = jedis.pfcount(key);
        if (pfcount != i) {
          if (i < 10) {
            System.out.println(i + " - " + pfcount + ": " + key);
          }
          break;
        }
      }
      jedis.del(key);
    }

  }
}
