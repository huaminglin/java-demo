package huaminglin.demo.quartz.spring;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    System.out.println("SampleJob: " + new Date() + ", " + this + ", " + jobExecutionContext.getTrigger());
  }
}
