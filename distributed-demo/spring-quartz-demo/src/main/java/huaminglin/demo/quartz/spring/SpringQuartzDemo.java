package huaminglin.demo.quartz.spring;

import javax.sql.DataSource;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@SpringBootApplication
public class SpringQuartzDemo {

  @Bean
  public JobDetail jobDetail() {
    return JobBuilder.newJob().ofType(SampleJob.class)
        .storeDurably()
        .withIdentity("sample_job")
        .withDescription("Sample job")
        .build();
  }

  @Bean
  public SimpleTriggerFactoryBean trigger(JobDetail job) {
    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setRepeatInterval(60000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    return trigger;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringQuartzDemo.class, args);
    context.getBean(DataSource.class);
    System.out.println("Main Thread exits: " + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
  }
}
