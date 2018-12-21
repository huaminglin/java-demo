package huaminglin.demo.redis.jedis;

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Response;

public class JedisDemo {
    private static void demoString(Jedis jedis) {
        jedis.set("key1", "value1");
        String value = jedis.get("key1");
        System.out.println(value);
    }

    private static void demoList(Jedis jedis) {
        jedis.lpush("q", "item1");
        jedis.lpush("q", "item2");
        String value = jedis.rpop("q");
        System.out.println(value);
    }

    private static void demoSet(Jedis jedis) {
        jedis.sadd("s", "item1");
        jedis.sadd("s", "item2");
        Set<String> members = jedis.smembers("s");
        System.out.println(members);
    }

    private static void demoHash(Jedis jedis) {
        jedis.hset("h", "item1", "value1");
        jedis.hset("h", "item2", "value2");
        Map<String, String> fields = jedis.hgetAll("h");
        System.out.println(fields);
    }

    private static void demoSortedSet(Jedis jedis) {
        jedis.zadd("z", 1.5, "item1");
        jedis.zadd("z", 0.5, "item2");
        jedis.zadd("z", 2.5, "item3");
        Set<String> members = jedis.zrevrange("z", 0, 1);
        System.out.println(members);
    }

    private static void demoTransaction(Jedis jedis) {
        jedis.watch("a"); // WATCH inside MULTI is not allowed
        Transaction t = jedis.multi();
        t.zadd("zt", 0.5, "item2");
        t.zadd("zt", 2.5, "item3");
        Object result = t.exec();
        System.out.println(result);
    }

    private static void demoPipeline(Jedis jedis) {
        jedis.watch("a"); // WATCH inside MULTI is not allowed
        Pipeline p = jedis.pipelined();
        p.zadd("zp", 0.5, "item2");
        Response<Set<String>> pipedMembers = p.zrevrange("zp", 0, 1);
        p.zadd("zp", 2.5, "item3");
        p.sync();
        System.out.println(pipedMembers.get());
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        demoString(jedis);
        demoList(jedis);
        demoSet(jedis);
        demoHash(jedis);
        demoSortedSet(jedis);
        demoTransaction(jedis);
        demoPipeline(jedis);
    }
}
