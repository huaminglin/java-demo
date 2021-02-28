package huaminglin.demo.io.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.util.Set;

public final class SocketServerBindDemo {

  public static void main(String[] args) throws InterruptedException, IOException {
    SocketAddress address = new InetSocketAddress("0.0.0.0", 9191);
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.setSoTimeout(10 * 1000);
    serverSocket.setReceiveBufferSize(1024 * 1024);
    serverSocket.bind(address, 2);
    int seconds = 10;
    if (args.length > 0) {
      seconds = Integer.parseInt(args[0]);
    }
    final Set<SocketOption<?>> socketOptions = serverSocket.supportedOptions();
    for (SocketOption option : socketOptions) {
      System.out.println(option.type() + "/" + option.name());
    }

    System.out.println("\n Sleep " + seconds + " seconds ...");
    Thread.sleep(1000 * seconds);

    System.out.println("\n Sleep " + 60 + " seconds ...");
    Thread.sleep(60 * seconds);
  }
}
