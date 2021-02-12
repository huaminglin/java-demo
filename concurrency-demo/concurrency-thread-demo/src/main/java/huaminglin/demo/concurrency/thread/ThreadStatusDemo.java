package huaminglin.demo.concurrency.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ThreadStatusDemo {
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

  static class ServerSocketThread extends Thread {

    @Override
    public void run() {
      System.out.println("ServerSocketThread.run()");
      try {
        ServerSocket s = new ServerSocket(15863);
        while (true) {
          if (s.accept() == null) {
            break;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("ServerSocketThread.run() exit");
    }
  }

  static class ClientSocketThread extends Thread {

    @Override
    public void run() {
      System.out.println("ClientSocketThread.run()");
      try {
        Socket s = new Socket("localhost", 15863);
        s.getInputStream().read();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("ClientSocketThread.run() exit");
    }
  }


  static class ServerSocketChannelThread extends Thread {

    @Override
    public void run() {
      System.out.println("ServerSocketChannelThread.run()");
      ServerSocketChannel serverSocketChannel = null;
      try {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(128);
        writeBuff.put("received".getBytes());
        writeBuff.flip();
        while(true) {
          int readyNum = selector.select();
          if (readyNum == 0) {
            continue;
          }

          Set<SelectionKey> selectedKeys = selector.selectedKeys();
          Iterator<SelectionKey> it = selectedKeys.iterator();

          while(it.hasNext()) {
            SelectionKey key = it.next();

            if(key.isAcceptable()) {
              SocketChannel socketChannel = serverSocketChannel.accept();
              socketChannel.configureBlocking(false);
              socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {SocketChannel socketChannel = (SocketChannel) key.channel();
              readBuff.clear();
              socketChannel.read(readBuff);

              readBuff.flip();
              System.out.println("received : " + new String(readBuff.array()));
              key.interestOps(SelectionKey.OP_WRITE);
            } else if (key.isWritable()) {
              writeBuff.rewind();
              SocketChannel socketChannel = (SocketChannel) key.channel();
              socketChannel.write(writeBuff);
              key.interestOps(SelectionKey.OP_READ);
            }

            it.remove();
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }

      System.out.println("ServerSocketChannelThread.run() exit");
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
      Thread thread = new ServerSocketThread();
      thread.setName("ServerSocketThreadName01");
      thread.start();
    }
    {
      Thread thread = new ClientSocketThread();
      thread.setName("ClientSocketThreadName01");
      thread.start();
    }
    {
      Thread thread = new ServerSocketChannelThread();
      thread.setName("ServerSocketChannelThreadName01");
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
