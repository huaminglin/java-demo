package huaminglin.demo.redis.jedis;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Response;
import redis.clients.jedis.JedisPubSub;

public class JedisDemo {

    private static void demoInteger(Jedis jedis) {
        System.out.println("demoInteger()");
        jedis.incr("int1");
        String value = jedis.get("int1");
        System.out.println(value);
    }

    private static void demoFloat(Jedis jedis) {
        System.out.println("demoFloat()");
        jedis.incrByFloat("float1", 123.123456789123456789);
        String value = jedis.get("float1");
        System.out.println(value);
    }

    private static void demoString(Jedis jedis) {
        System.out.println("demoString()");
        jedis.set("str1", "value1");
        String value = jedis.get("str1");
        System.out.println(value);
    }

    private static void demoBytes(Jedis jedis) {
        System.out.println("demoBytes()");
        {
            byte[] bytes = new byte[4];
            bytes[0] = 0b001_0101;
            bytes[1] = -0b0010_0101;
            bytes[2] = 0b0110_0101;
            bytes[3] = -0b0110_0101;
            jedis.set(bytes, bytes);
            byte[] value = jedis.get(bytes);
            BigInteger bi = new BigInteger(1, value);
            System.out.println(String.format("%0" + (bytes.length << 1) + "X", bi));
        }
        {
            byte[] bytes = new byte[4];
            bytes[0] = (byte)122;
            bytes[1] = (byte)122;
            bytes[2] = (byte)122;
            bytes[3] = (byte)122;
            jedis.lpush(bytes, bytes);
            byte[] value = jedis.rpop(bytes);
            BigInteger bi = new BigInteger(1, value);
            System.out.println(String.format("%0" + (bytes.length << 1) + "X", bi));
        }
    }

    private static void demoList(Jedis jedis) {
        System.out.println("demoList()");
        jedis.lpush("q", "item1");
        jedis.lpush("q", "item2");
        String value = jedis.rpop("q");
        System.out.println(value);
    }

    private static void demoSet(Jedis jedis) {
        System.out.println("demoSet()");
        jedis.sadd("s", "item1");
        jedis.sadd("s", "item2");
        Set<String> members = jedis.smembers("s");
        System.out.println(members);
    }

    private static void demoHash(Jedis jedis) {
        System.out.println("demoHash()");
        jedis.hset("h", "item1", "value1");
        jedis.hset("h", "item2", "value2");
        Map<String, String> fields = jedis.hgetAll("h");
        System.out.println(fields);
    }

    private static void demoSortedSet(Jedis jedis) {
        System.out.println("demoSortedSet()");
        jedis.zadd("z", 1.5, "item1");
        jedis.zadd("z", 0.5, "item2");
        jedis.zadd("z", 2.5, "item3");
        Set<String> members = jedis.zrevrange("z", 0, 1);
        System.out.println(members);
    }

    private static void demoHyperLogLog(Jedis jedis) {
        System.out.println("demoHyperLogLog()");
        {
            String key = "hll-small-v3";
            int count = 100;
            for (int i = 0; i < count; i++) {
                jedis.pfadd(key, key + i);
            }
            for (int i = 0; i < count; i++) {// duplicate key test
                jedis.pfadd(key, key + i);
            }
            long pfcount = jedis.pfcount(key);
            System.out.println(pfcount);
        }
        {
            String key = "hll-huge-v3";
            int count = 10000;
            for (int i = 0; i < count; i++) {
                jedis.pfadd(key, key + i);
            }
            long pfcount = jedis.pfcount(key);
            System.out.println(pfcount);
        }
    }

    private static void demoPipeline(Jedis jedis) {
        System.out.println("demoPipeline()");
        Pipeline p = jedis.pipelined();
        Response<Long> pi = p.incr("pi");
        Response<String> piValue = p.get("pi");

        // jedis.get("a"); // Exception in thread "main" redis.clients.jedis.exceptions.JedisDataException: Cannot use Jedis when in Pipeline. Please use Pipeline or reset jedis state .
        try {// Sleep and check the server log to verify when the commands is sent to the server.
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.sync(); // After sync(), the commands are sent to the server.
        System.out.println(pi.get());
        System.out.println(piValue.get());
    }

    private static void demoTransaction(Jedis jedis) {
        System.out.println("demoTransaction()");
        jedis.watch("a"); // WATCH inside MULTI is not allowed
        Transaction t = jedis.multi();
        t.zadd("zt", 0.5, "item2");
        t.zadd("zt", 2.5, "item3");
        Object result = t.exec();
        System.out.println(result);
    }

    private static void demoCas(Jedis jedis, Jedis jedis2) {
        System.out.println("demoCas()");
        jedis.set("a", "a1");
        jedis.watch("a"); // WATCH inside MULTI is not allowed
        Transaction t = jedis.multi();
        t.zadd("zt", 0.5, "item2");
        t.zadd("zt", 2.5, "item3");

        jedis2.set("a", "a2");

        Object result = t.exec(); // null indicates a failure.
        System.out.println(result);
    }

    private static void demoSubscriber(Jedis jedis) {
        System.out.println("demoSubscriber()");
        String channel = "channel1";
        System.out.println("Subscribe channel on main thread, wait for the message \"quit\" to exit.");
        // subscribe() is a blocking method.
        JedisPubSub subscriber = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Thread/" + Thread.currentThread() + "Got message from channel/" + channel + ": " + message);
                if ("quit".equals(message)) {
                    this.unsubscribe(channel);
                }
            }
        };
        jedis.publish(channel, "Hello world");
        jedis.publish(channel, "quit");
        jedis.subscribe(subscriber, channel);// This is a blocking call.
        // The messages published before subscription are not sent to the new subscriber.
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        demoInteger(jedis);
        demoFloat(jedis);
        demoString(jedis);
        demoBytes(jedis);

        demoList(jedis);
        demoSet(jedis);
        demoHash(jedis);
        demoSortedSet(jedis);
//        demoHyperLogLog(jedis);

        demoPipeline(jedis);
        demoTransaction(jedis);
        {
            Jedis jedis2 = new Jedis("127.0.0.1", 6379);
            demoCas(jedis, jedis2);
        }

        demoSubscriber(jedis);
    }
}
