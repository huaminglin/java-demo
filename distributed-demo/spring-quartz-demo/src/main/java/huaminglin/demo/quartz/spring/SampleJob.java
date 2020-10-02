package huaminglin.demo.quartz.spring;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("SampleJob begin: " + new Date() + ", " + this);
    try {
      Thread.sleep(30 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("SampleJob end: " + new Date() + ", " + this);
  }
}
