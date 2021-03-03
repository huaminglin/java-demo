package huaminglin.demo.io.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public final class SocketClientDemo {

  public static void main(String[] args) throws IOException {
    SocketAddress address = new InetSocketAddress("127.0.0.1", 9191);
    final Socket clientSocket = new Socket();
    final Set<SocketOption<?>> socketOptions = clientSocket.supportedOptions();
    for (SocketOption option : socketOptions) {
      System.out.println(option.type() + "/" + option.name());
    }
    clientSocket.setReuseAddress(true);
    clientSocket.setSoTimeout(1000 * 60);
    clientSocket.setOOBInline(true);
    clientSocket.setTcpNoDelay(true);
    clientSocket.setReceiveBufferSize(1024);
    clientSocket.setSendBufferSize(2048);
    clientSocket.setKeepAlive(true);
    clientSocket.setSoLinger(true, 60);
//    clientSocket.setTrafficClass(); // Sets traffic class or type-of-service octet in the IP header for packets sent from this Socket.
    clientSocket.connect(address, 1000 * 60);
    PrintWriter in = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8),true);
    final BufferedReader out = new BufferedReader( new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
    for (int i = 0; i < 3; i++) {
      // Unlike the PrintStream class, if automatic flushing is enabled it will be done only when one of the println, printf, or format methods is invoked, rather than whenever a newline character happens to be output.
      in.println("Client request " + i);
      String line = out.readLine();
      System.out.println("Got " + line);
    }
    clientSocket.close();
//    clientSocket.shutdownOutput(); // For a TCP socket, any previously written data will be sent followed by TCP's normal connection termination sequence.
//    clientSocket.shutdownInput();
    System.out.println("main() exit");
  }
}
