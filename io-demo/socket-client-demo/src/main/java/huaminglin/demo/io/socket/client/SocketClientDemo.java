package huaminglin.demo.io.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public final class SocketClientDemo {

  public static void main(String[] args) throws IOException {
    SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
    final Socket clientSocket = new Socket();
    clientSocket.connect(address);
    final PrintWriter in = new PrintWriter(clientSocket.getOutputStream(), true,
        StandardCharsets.UTF_8);
    final BufferedReader out = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
    for (int i = 0; i < 3; i++) {
      in.write("Client request " + i + '\n');
      in.flush(); // How to support auto flush?
      String line = out.readLine();
      System.out.println("Got " + line);
    }
    clientSocket.close();
//    clientSocket.shutdownOutput(); // For a TCP socket, any previously written data will be sent followed by TCP's normal connection termination sequence.
//    clientSocket.shutdownInput();
    System.out.println("main() exit");
  }
}
