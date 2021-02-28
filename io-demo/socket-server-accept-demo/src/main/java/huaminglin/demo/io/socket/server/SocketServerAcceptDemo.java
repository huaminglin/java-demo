package huaminglin.demo.io.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public final class SocketServerAcceptDemo {

  public static void main(String[] args) throws InterruptedException, IOException {
    SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(address, 2);

    System.out.println("accept() ...");
    final Socket clientSocket = serverSocket.accept();
    int seconds = 10;
    if (args.length > 0) {
      seconds = Integer.parseInt(args[0]);
    }

    System.out.println("\n Sleep " + seconds + " seconds ...");
    Thread.sleep(1000 * seconds);

    clientSocket.close();

    serverSocket.close();

    System.out.println("\n Sleep " + seconds + " seconds ...");
    Thread.sleep(seconds * seconds);
  }
}
