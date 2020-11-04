package huaminglin.demo.metrics.micrometer;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.LongTaskTimer.Sample;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class SleepService {

  private final Counter counter;
  private final Timer timer;
  private final Timer timerPercentile;
  private final LongTaskTimer longTaskTimer;
  private final DistributionSummary distributionSummary;
  private final DistributionSummary histogram;
  private long gaugeValue;

  public SleepService(MeterRegistry registry) {
    counter = Counter
        .builder("service.sleep.counter")
        .description("Sleep Service Counter")
        .tags("dev", "performance")
        .register(registry);
    timer = registry.timer("service.sleep.timer");
    timerPercentile = Timer.builder("service.sleep.percentile.timer").publishPercentileHistogram()
        .register(registry);
    longTaskTimer = LongTaskTimer
        .builder("service.sleep.long.task.timer")
        .register(registry);
    Gauge.builder("service.sleep.gauge", () -> gaugeValue)
        .register(registry);
    distributionSummary = DistributionSummary
        .builder("service.sleep.distribution.summary")
        .baseUnit("bytes")
        .register(registry);
    histogram = DistributionSummary
        .builder("service.sleep.histogram")
        .publishPercentileHistogram()
        .register(registry);
  }

  @Timed("micrometer.demo.timed.sleep")
  public long sleep() {
    Sample sample = longTaskTimer.start();
    long millis = (long) (Math.random() * 1000 * 5);
    timer.record(() -> {
      try {
        TimeUnit.MILLISECONDS.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    timerPercentile.record(millis, TimeUnit.MILLISECONDS);
    counter.increment(millis);
    sample.stop();
    gaugeValue = millis;
    distributionSummary.record(millis);
    histogram.record(millis);
    return millis;
  }
}
