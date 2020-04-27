package huaminglin.demo.tool.memory.thread;




public class ThreadTooDeepDemo {

    private static void printDepth(int i, int targetDepth) {
        if (i == targetDepth) {
            try {
                System.out.println("sleep ...");
                Thread.sleep(10 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            printDepth(i + 1, targetDepth);
        }
    }

    private static void runThread(int depth) {
        Thread thread = new Thread(() -> {
            printDepth(1, depth);
        });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        int count;
        int depth;
        int sleep = -1;
        if (args.length < 1) {
            count = 1;
            depth = Integer.MAX_VALUE;
        } else if (args.length < 2) {
            count = Integer.parseInt(args[0]);
            depth = Integer.MAX_VALUE;
        } else {
            count = Integer.parseInt(args[0]);
            depth = Integer.parseInt(args[1]);
            sleep = 100;
        }
        for( int i = 0; i < count; i++) {
            runThread(depth);
            if (sleep > 0) {
                Thread.sleep(sleep);
            }
        }
    }
}
