package huaminglin.demo.jdk8.stream;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CommonForkJoinPoolDemo {
    public static void main(String[] args) {
        System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
        System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());


        long firstNum = 1;
        long lastNum = 10;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        long start = System.nanoTime();
        Long reduce = aList.parallelStream().peek(a -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).reduce(0L, Long::sum);
        System.out.println("Processed in " + (System.nanoTime() - start) /1000/1000 + " milliseconds, result: " + reduce);
    }
}
