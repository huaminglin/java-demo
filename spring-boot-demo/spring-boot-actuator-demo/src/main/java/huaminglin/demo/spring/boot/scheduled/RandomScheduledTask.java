package huaminglin.demo.spring.boot.scheduled;

import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomScheduledTask {

  private Random rand = new Random();

  @Scheduled(fixedRate = 5000)
  public void randomId() {
    rand.nextInt(50);
    // System.out.println(rand.nextInt(50));
  }
}
