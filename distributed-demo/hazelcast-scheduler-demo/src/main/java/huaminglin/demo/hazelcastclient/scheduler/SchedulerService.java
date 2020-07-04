package huaminglin.demo.hazelcastclient.scheduler;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.scheduledexecutor.IScheduledExecutorService;
import com.hazelcast.scheduledexecutor.IScheduledFuture;
import com.hazelcast.scheduledexecutor.TaskUtils;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
  @Autowired
  private HazelcastInstance hazelcastInstance;

  @PostConstruct
  public void initialize() {
    IScheduledExecutorService scheduledExecutorService = hazelcastInstance
        .getScheduledExecutorService("scheduler1");
    HelloWorldTask task = new HelloWorldTask();
    task.setTopic("topic1");
    task.setMessage("Hello world");
    Runnable named = TaskUtils.named("task1", task);
    IScheduledFuture<Object> scheduledFuture = scheduledExecutorService
        .scheduleAtFixedRate(named, 5, 30, TimeUnit.SECONDS);
    // scheduledFuture.cancel() // This is how we cancel a job
  }

}
