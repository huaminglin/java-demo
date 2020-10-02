package huaminglin.demo.quartz.spring;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
// @Profile("jobs")
public class JobConfiguration {

  @Bean
  @Qualifier("job1")
  public JobDetail jobDetail01() {
    return JobBuilder.newJob().ofType(SampleJob.class)
        .storeDurably()
        .withIdentity("sample_job_01")
        .withDescription("Sample job 01")
        .build();
  }

  @Bean
  @Qualifier("job2")
  public JobDetail jobDetail02() {
    return JobBuilder.newJob().ofType(SampleJob.class)
        .storeDurably()
        .withIdentity("sample_job_02")
        .withDescription("Sample job 02")
        .build();
  }

  @Bean
  public SimpleTriggerFactoryBean trigger01(@Qualifier("job1") JobDetail job) {
    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setRepeatInterval(60000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    return trigger;
  }

  @Bean
  public CronTriggerFactoryBean trigger02(@Qualifier("job2") JobDetail job) {
    CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
    trigger.setJobDetail(job);
    trigger.setCronExpression("0 0/3 * * * ?");
    return trigger;
  }
}
