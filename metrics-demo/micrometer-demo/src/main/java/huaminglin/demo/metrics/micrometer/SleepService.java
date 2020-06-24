package huaminglin.demo.metrics.micrometer;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Component;

@Component
public class SleepService {

  @Timed("micrometer.demo.timed.sleep")
  public long sleep() {
    long seconds = (long) (Math.random() * 1000 * 5);
    try {
      Thread.sleep(seconds) ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return seconds;
  }
}
