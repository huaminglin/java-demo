package huaminglin.demo.spring.boot.cache;

import java.util.Random;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheIdProvider {

    private Random rand = new Random();

    @Cacheable(value = "ids", key = "#root.methodName")
    public int getId() {
        return rand.nextInt(50);
    }
}
