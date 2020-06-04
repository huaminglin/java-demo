package huaminglin.demo.jdk8.stream;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StreamParallelDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long firstNum = 1;
        long lastNum = 10;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());
        {
            System.out.println("stream(): ");
            Long aLong = aList.stream().peek(a -> System.out.println(Thread.currentThread() + "/" + Thread.currentThread().getId() + ": " + a)).reduce(0L, Long::sum);
            System.out.println(aLong);
        }
        {
            System.out.println("parallelStream(): ");
            Long aLong = aList.parallelStream().peek(a -> System.out.println(Thread.currentThread() + "/" + Thread.currentThread().getId() + ": " + a)).reduce(0L, Long::sum);
            System.out.println(aLong);
        }
        {
            System.out.println("parallelStream() round 2: ");// Check that the background thread pool is the one used above.
            Long aLong = aList.parallelStream().peek(a -> System.out.println(Thread.currentThread() + "/" + Thread.currentThread().getId() + ": " + a)).reduce(0L, Long::sum);
            System.out.println(aLong);
        }
        {
            System.out.println("parallelStream() in customized ForkJoinPool: ");
            ForkJoinPool customThreadPool = new ForkJoinPool(2);
            Long aLong = customThreadPool.submit(
                    () -> aList.parallelStream().peek(a -> System.out.println(Thread.currentThread() + "/" + Thread.currentThread().getId() + ": " + a)).reduce(0L, Long::sum)
            ).get();
            System.out.println(aLong);
        }
    }

    /*
stream():
Thread[main,5,main]/1: 1
Thread[main,5,main]/1: 2
Thread[main,5,main]/1: 3
Thread[main,5,main]/1: 4
Thread[main,5,main]/1: 5
Thread[main,5,main]/1: 6
Thread[main,5,main]/1: 7
Thread[main,5,main]/1: 8
Thread[main,5,main]/1: 9
Thread[main,5,main]/1: 10
55
parallelStream():
Thread[main,5,main]/1: 7
Thread[main,5,main]/1: 6
Thread[ForkJoinPool.commonPool-worker-7,5,main]/15: 2
Thread[ForkJoinPool.commonPool-worker-7,5,main]/15: 1
Thread[ForkJoinPool.commonPool-worker-9,5,main]/16: 8
Thread[ForkJoinPool.commonPool-worker-9,5,main]/16: 4
Thread[ForkJoinPool.commonPool-worker-7,5,main]/15: 5
Thread[ForkJoinPool.commonPool-worker-3,5,main]/13: 3
Thread[ForkJoinPool.commonPool-worker-5,5,main]/14: 9
Thread[ForkJoinPool.commonPool-worker-3,5,main]/13: 10
55
parallelStream() round 2:
Thread[main,5,main]/1: 7
Thread[ForkJoinPool.commonPool-worker-9,5,main]/16: 8
Thread[ForkJoinPool.commonPool-worker-13,5,main]/18: 5
Thread[ForkJoinPool.commonPool-worker-15,5,main]/19: 6
Thread[ForkJoinPool.commonPool-worker-7,5,main]/15: 1
Thread[ForkJoinPool.commonPool-worker-3,5,main]/13: 3
Thread[ForkJoinPool.commonPool-worker-15,5,main]/19: 4
Thread[ForkJoinPool.commonPool-worker-11,5,main]/17: 2
Thread[ForkJoinPool.commonPool-worker-9,5,main]/16: 10
Thread[ForkJoinPool.commonPool-worker-5,5,main]/14: 9
55
parallelStream() in customized ForkJoinPool:
Thread[ForkJoinPool-1-worker-3,5,main]/20: 7
Thread[ForkJoinPool-1-worker-1,5,main]/21: 3
Thread[ForkJoinPool-1-worker-3,5,main]/20: 6
Thread[ForkJoinPool-1-worker-1,5,main]/21: 5
Thread[ForkJoinPool-1-worker-3,5,main]/20: 9
Thread[ForkJoinPool-1-worker-1,5,main]/21: 4
Thread[ForkJoinPool-1-worker-3,5,main]/20: 10
Thread[ForkJoinPool-1-worker-1,5,main]/21: 2
Thread[ForkJoinPool-1-worker-3,5,main]/20: 8
Thread[ForkJoinPool-1-worker-1,5,main]/21: 1
55
     */
}
