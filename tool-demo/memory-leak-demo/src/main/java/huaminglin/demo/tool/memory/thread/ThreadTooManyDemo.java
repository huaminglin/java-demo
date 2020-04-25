package huaminglin.demo.tool.memory.thread;

public class ThreadTooManyDemo {
    private static void runThread(int index) {
        Thread thread = new Thread(() -> {
            System.out.println(index);
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("thread-" + index);
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        int count;
        int delay;
        if (args.length < 2) {
            count = 20000;
            delay = 10;
        } else {
            count = Integer.parseInt(args[0]);
            delay = Integer.parseInt(args[1]);
        }
        for (int i = 0; i < count; i++) {
            runThread(i);
            Thread.sleep(delay);
        }
    }
}
