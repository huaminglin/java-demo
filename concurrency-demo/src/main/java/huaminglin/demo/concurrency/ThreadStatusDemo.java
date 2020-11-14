package huaminglin.demo.concurrency;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStatusDemo {
  static final Object synchronizedObject = new Object();
  static final Lock lock = new ReentrantLock();
  static final Lock conditionLock = new ReentrantLock();
  static final Condition condition = conditionLock.newCondition();


  static class RunningThread extends Thread {

    @Override
    public void run() {
      System.out.println("RunningThread.run()");
      while(true) {

      }
      // System.out.println("RunningThread.run() exit");
    }
  }


  static class SleepingThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("SleepingThread.run()");
        Thread.sleep(60 * 60 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("SleepingThread.run() exit");
    }
  }

  static class WaitingThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println("WaitingThread.run()");
        synchronized (this) {
          wait();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("WaitingThread.run() exit");
    }
  }

  static class SynchronizedThread extends Thread {

    @Override
    public void run() {
      System.out.println("SynchronizedThread.run()");
      synchronized (synchronizedObject) {
        System.out.println("SynchronizedThread.run() synchronized");
      }
      System.out.println("SynchronizedThread.run() exit");
    }
  }

  static class LockThread extends Thread {

    @Override
    public void run() {
      System.out.println("LockThread.run()");
      try {
        lock.lock();
        System.out.println("LockThread.run() lock()");
      } finally {
        lock.unlock();
      }
      System.out.println("LockThread.run() exit");
    }
  }

  static class ConditionAwaitThread extends Thread {

    @Override
    public void run() {
      System.out.println("ConditionAwaitThread.run()");
      try {
        conditionLock.lock();
        condition.await();
        System.out.println("ConditionAwaitThread.run() await()");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        conditionLock.unlock();
      }
      System.out.println("ConditionAwaitThread.run() exit");
    }
  }

  static class ReadInThread extends Thread {

    @Override
    public void run() {
      System.out.println("ReadInThread.run()");
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("ReadInThread.run() exit");
    }
  }

  public static void main(String[] args) {
    {
      Thread thread = new RunningThread();
      thread.setName("RunningThreadName01");
      thread.start();
    }
    {
      Thread thread = new SleepingThread();
      thread.setName("SleepingThreadName01");
      thread.start();
    }
    {
      Thread thread = new ReadInThread();
      thread.setName("ReadInThreadName01");
      thread.start();
    }
    {
      Thread thread = new WaitingThread();
      thread.setName("WaitingThreadName01");
      thread.start();
    }

    {
      lock.lock();
      Thread thread = new LockThread();
      thread.setName("LockThreadName01");
      thread.start();
    }
    {
      Thread thread = new ConditionAwaitThread();
      thread.setName("ConditionAwaitThreadName01");
      thread.start();
    }

    synchronized (synchronizedObject) {
      Thread thread = new SynchronizedThread();
      thread.setName("SynchronizedThreadName01");
      thread.start();

      try {
        Thread.sleep(60 * 60 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
