package huaminglin.demo.spring.boot.health;

import java.util.Random;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class RandomHealthIndicator implements HealthIndicator {
    private Random rand = new Random();

    @Override
	public Health health() {
		if (rand.nextInt(50) > 25) {
			return Health.down().withDetail("Error Code", "my error code").build();
		}
		return Health.up().build();
	}
}
