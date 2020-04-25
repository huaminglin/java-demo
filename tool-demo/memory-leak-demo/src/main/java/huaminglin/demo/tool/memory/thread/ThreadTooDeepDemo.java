package huaminglin.demo.tool.memory.thread;




public class ThreadTooDeepDemo {

    private static void printDepth(int i) {
        long result = i;
        long result2 = result + i;
        long result3 = result + i;
        long result4 = result + i;
        long result5 = result + i;
        long result6 = result + i;
        System.out.println(i);
        printDepth(i + 1);
    }

    private static void runThread() {
        Thread thread = new Thread(() -> {
            printDepth(1);
        });
        thread.start();
    }

    public static void main(String[] args) {
        int count;
        if (args.length < 1) {
            count = 1;
        } else {
            count = Integer.parseInt(args[0]);
        }
        for( int i = 0; i < count; i++) {
            runThread();
        }
    }
}
