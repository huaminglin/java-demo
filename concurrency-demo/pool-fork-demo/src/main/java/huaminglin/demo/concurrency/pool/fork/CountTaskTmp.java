package huaminglin.demo.concurrency.pool.fork;

import java.util.concurrent.RecursiveTask;

public class CountTaskTmp extends RecursiveTask<Integer> {
  private static final int THRESHOLD = 2;
  private String name;
  private int start;
  private int end;

  public CountTaskTmp(String name, int start, int end) {
    this.name = name;
    this.start = start;
    this.end = end;
  }

  protected Integer compute() {
    System.out.println(Thread.currentThread().getName() + '/' + name + " start: [ " + start + ", " + end + " ]");
    int sum = 0;
    boolean canCompute = (end - start) <= THRESHOLD;
    if (canCompute) {
      for (int i = start; i <= end; i++)
        sum += i;
    } else {
      int mid = (start + end) / 2;
      CountTaskTmp leftTask = new CountTaskTmp(name, start, mid);
      CountTaskTmp rightTask = new CountTaskTmp(name, mid + 1, end);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      leftTask.fork();
      rightTask.fork();

      int leftResult = (int) leftTask.join();
      int rightResult = (int) rightTask.join();

      sum = leftResult + rightResult;
    }
    System.out.println(Thread.currentThread().getName() + '/' + name + " result: " + sum);
    return sum;
  }
}
