package huaminglin.demo;

import hudson.Extension;
import hudson.model.Node;
import hudson.model.Queue;
import hudson.model.Queue.BuildableItem;
import hudson.model.queue.CauseOfBlockage;
import hudson.model.queue.QueueTaskDispatcher;
import javax.annotation.CheckForNull;

@Extension
public class HelloWorldDispatcher extends QueueTaskDispatcher {

  @Override
  public @CheckForNull
  CauseOfBlockage canTake(Node node, BuildableItem item) {
    System.out.println("HelloWorldDispatcher.canTake(): " + node + ", " + item);
    return null;
  }

  @Override
  public @CheckForNull
  CauseOfBlockage canRun(Queue.Item item) {
    System.out.println("HelloWorldDispatcher.canRun(): " + item);
    return null;
  }
}
