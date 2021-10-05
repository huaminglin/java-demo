package huaminglin.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ServerSocketChannelDemo  {

  private static Selector prepareSelector() throws IOException {
    return Selector.open();
  }

  private static ServerSocketChannel prepareServerSocketChannel() throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(9999));
    serverSocketChannel.configureBlocking(false);
    return serverSocketChannel;
  }

  private static void acceptConnection(Selector selector, ServerSocketChannel serverSocketChannel,
      Map<SocketAddress, SocketChannel> connections) throws IOException {
    SocketChannel socketChannel = serverSocketChannel.accept();
    socketChannel.configureBlocking(false);
    socketChannel.register(selector, SelectionKey.OP_WRITE);
    connections.putIfAbsent(socketChannel.getRemoteAddress(), socketChannel);
  }

  private static void shutdown(Map<SocketAddress, SocketChannel> connections, ByteBuffer writeBuff)
      throws IOException {
    writeBuff.put("Bye Guest.\n".getBytes(StandardCharsets.UTF_8));
    writeBuff.flip();
    for (SocketChannel channel : connections.values()) {
      writeBuff.rewind();
      channel.write(writeBuff);
      System.out.println("Close " + channel.getRemoteAddress());
      channel.close();
    }
    writeBuff.flip();
  }

  private static String readMessage(SelectionKey key, SocketChannel socketChannel,
      ByteBuffer readBuff) throws IOException {
    readBuff.clear();
    socketChannel.read(readBuff);

    readBuff.flip();
    String message = new String(readBuff.array(), StandardCharsets.UTF_8).trim();
    System.out.println("received : " + message);
    key.interestOps(SelectionKey.OP_WRITE);
    return message;
  }

  private static void writeMessage(SelectionKey key, SocketChannel socketChannel,
      ByteBuffer writeBuff, String message)
      throws IOException {
    writeBuff.put(message.getBytes(StandardCharsets.UTF_8));
    writeBuff.flip();
    socketChannel.write(writeBuff);
    writeBuff.flip();
    key.interestOps(SelectionKey.OP_READ);
  }

  public static void main(String[] args) {
    ServerSocketChannel serverSocketChannel;
    try {
      Selector selector = prepareSelector();
      serverSocketChannel = prepareServerSocketChannel();

      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

      ByteBuffer readBuff = ByteBuffer.allocate(1024);
      ByteBuffer writeBuff = ByteBuffer.allocate(128);
      Map<SocketAddress, SocketChannel> connections = new HashMap<>();
      System.out.println("Wait for a client to say 'bye'...");
      boolean active = true;
      while (active) {
        int readyNum = selector.select();
        if (readyNum == 0) {
          continue;
        }

        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectedKeys.iterator();

        while(it.hasNext()) {
          SelectionKey key = it.next();

          if(key.isAcceptable()) {
            acceptConnection(selector, serverSocketChannel, connections);
          } else if (key.isReadable()) {
            String message = readMessage(key, (SocketChannel) key.channel(), readBuff);
            if (message.equalsIgnoreCase("bye")) {
              active = false;
              shutdown(connections, writeBuff);
            }
          } else if (key.isWritable()) {
            writeMessage(key, (SocketChannel) key.channel(), writeBuff, "Hello Guest.\n");
          }

          it.remove();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
