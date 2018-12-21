package huaminglin.demo.redis.jedis;
import redis.clients.jedis.Jedis;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("key1", "value1");
        String value = jedis.get("key1");
        System.out.println(value);
    }
}
