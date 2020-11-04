package huaminglin.demo.metrics.micrometer;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicrometerDemo {

  public static void main(String[] args) {
    SpringApplication.run(MicrometerDemo.class, args);
    System.out.println(
        "Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread()
            .getName());
  }

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    // Enable processing of @Timed
    return new TimedAspect(registry);
  }

}
