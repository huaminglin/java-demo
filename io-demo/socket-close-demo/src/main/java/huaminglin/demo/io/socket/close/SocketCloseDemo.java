package huaminglin.demo.io.socket.close;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public final class SocketCloseDemo {

  private static void noClose() throws InterruptedException {
    Thread serverThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
      ServerSocket serverSocket = null;
      try {
        serverSocket = new ServerSocket();
        serverSocket.bind(address, 2);

        System.out.println("accept() ...");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("accept(): " + clientSocket);
        serverSocket.close();
        System.out.println("serverSocket.close()");
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("serverThread exit");
    });
    Thread clientThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
      final Socket clientSocket = new Socket();
      try {
        Thread.sleep(100);
        clientSocket.connect(address, 1000 * 60);
        System.out.println("connect(): " + clientSocket);
        System.out.println("connect() read() ...");
        final int read = clientSocket.getInputStream().read();
        System.out.println("connect() read() got: " + read);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("clientThread exit");
    });
    clientThread.start();
    serverThread.start();
    clientThread.join();
    serverThread.join();
  }

  private static void closeOnServerSide() throws InterruptedException {
    Thread serverThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
      ServerSocket serverSocket;
      try {
        serverSocket = new ServerSocket();
        serverSocket.bind(address, 2);

        System.out.println("accept() ...");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("accept(): " + clientSocket);
        clientSocket.close();
        serverSocket.close();
        System.out.println("serverSocket.close()");
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("serverThread exit");
    });
    Thread clientThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
      final Socket clientSocket = new Socket();
      try {
        Thread.sleep(100);
        clientSocket.connect(address, 1000 * 60);
        System.out.println("connect(): " + clientSocket);
        System.out.println("connect() read() ...");
        final int read = clientSocket.getInputStream().read();
        System.out.println("connect() read() got: " + read);
        if (read < 0) {
          System.out.println("read < 0 means server side starts to close(); sleep 1 minute");
          Thread.sleep(1000 * 60);
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("clientThread exit");
    });
    clientThread.start();
    serverThread.start();
    clientThread.join();
    serverThread.join();
  }

  private static void closeOnClientSide() throws InterruptedException {
    Thread serverThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
      ServerSocket serverSocket = null;
      try {
        serverSocket = new ServerSocket();
        serverSocket.bind(address, 2);

        System.out.println("accept() ...");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("accept(): " + clientSocket);
        System.out.println("accept() read() ...");
        final int read = clientSocket.getInputStream().read();
        System.out.println("accept() read() got: " + read);
        if (read < 0) {
          System.out.println("read < 0 means client side starts to close(); sleep 1 minute");
          Thread.sleep(1000 * 60);
        }
        serverSocket.close();
        System.out.println("serverSocket.close()");
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("serverThread exit");
    });
    Thread clientThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
      final Socket clientSocket = new Socket();
      try {
        Thread.sleep(100);
        clientSocket.connect(address, 1000 * 60);
        System.out.println("connect(): " + clientSocket);
        Thread.sleep(100);
        clientSocket.close();
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("clientThread exit");
    });
    clientThread.start();
    serverThread.start();
    clientThread.join();
    serverThread.join();
  }

  private static void closeOnBothSide() throws InterruptedException {
    Thread serverThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
      ServerSocket serverSocket = null;
      try {
        serverSocket = new ServerSocket();
        serverSocket.bind(address, 2);

        System.out.println("accept() ...");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("accept(): " + clientSocket);
        System.out.println("accept() read() ...");
        final int read = clientSocket.getInputStream().read();
        System.out.println("accept() read() got: " + read);
        if (read < 0) {
          System.out.println("read < 0 means client side starts to close(); sleep 1 minute");
          Thread.sleep(1000 * 60);
        }
        clientSocket.close();
        serverSocket.close();
        System.out.println("serverSocket.close()");
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("serverThread exit");
    });
    Thread clientThread = new Thread(() -> {
      SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
      final Socket clientSocket = new Socket();
      try {
        Thread.sleep(100);
        clientSocket.connect(address, 1000 * 60);
        System.out.println("connect(): " + clientSocket);
        Thread.sleep(100);
        clientSocket.close();
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("clientThread exit");
    });
    clientThread.start();
    serverThread.start();
    clientThread.join();
    serverThread.join();
  }

  public static void main(String[] args) throws InterruptedException {
//    noClose();
//    closeOnServerSide();
//    closeOnClientSide();
      closeOnBothSide();
  }
}
