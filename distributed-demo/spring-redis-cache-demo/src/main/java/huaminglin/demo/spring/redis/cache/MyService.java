package huaminglin.demo.spring.redis.cache;

import java.time.Instant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  @Cacheable("instant")
  public Instant getInstant() {
    return Instant.now();
  }
}
