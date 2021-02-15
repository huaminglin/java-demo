package huaminglin.demo.concurrency.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ConditionDemo {

  public static void main(String[] args) throws InterruptedException {
    Lock lock = new ReentrantLock();
    lock.lock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    List<Thread> threads = new ArrayList<>();
    int count = 2;
    for (int i = 0; i < count; i++){
      final int j = i;
      Thread thread = new Thread(() -> {
        System.out.println("Thread notFull " + j + " run()");
        lock.lock();
        System.out.println("Thread notFull " + j + " lock()");
        try {
          notFull.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
        System.out.println("Thread notFull " + j + " exit()");
      });
      threads.add(thread);
      thread.start();
    }
    for (int i = 0; i < count; i++){
      final int j = i;
      Thread thread = new Thread(() -> {
        System.out.println("Thread notEmpty " + j + " run()");
        lock.lock();
        System.out.println("Thread notEmpty " + j + " lock()");
        try {
          notEmpty.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
        System.out.println("Thread notEmpty " + j + " exit()");
      });
      threads.add(thread);
      thread.start();
    }
    lock.unlock();

    System.out.println("Release the lock so the target threads can get lock and call await().");
    Thread.sleep(1000);

    lock.lock();
    System.out.println("notFull.signalAll()");
    notFull.signalAll();
    System.out.println("notEmpty.signalAll()");
    notEmpty.signalAll();
    lock.unlock();
    for(Thread thread : threads) {
      thread.join();
    }
    System.out.println("main() exit.");
  }
}
