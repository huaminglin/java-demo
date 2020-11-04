package huaminglin.demo.tool.memory;

public class NativeMemoryDemo {

  public static void main(String[] args) throws InterruptedException {
    int count;
    int delay;
    if (args.length < 1) {
      count = 1;
      delay = 10;
    } else {
      count = Integer.parseInt(args[0]);
      delay = Integer.parseInt(args[1]);
    }
    for (int i = 0; i < count; i++) {
      new StackOverFlowThread().start();
      Thread.sleep(delay);
    }
  }

  public static class StackOverFlowThread extends Thread {

    public int depth = 0;

    public void dive() {
      depth++;
      try {
        dive();
      } catch (StackOverflowError error) {
//                error.printStackTrace();
        System.out.println("Maximum depth: " + depth);
        try {
          while (true) {
            Thread.sleep(10000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    public void run() {
      dive();
    }
  }
}
