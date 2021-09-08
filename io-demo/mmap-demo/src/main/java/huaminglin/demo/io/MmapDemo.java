package huaminglin.demo.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public final class MmapDemo {

  public static void main(String[] args) throws IOException, InterruptedException {
    String filePath = System.getProperty("user.home") + "/.m2/repository/org/apache/ant/ant/1.10.9/ant-1.10.9.pom";
    File file = new File(filePath);
    FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
    MappedByteBuffer byteBuffer = fileChannel
        .map(FileChannel.MapMode.READ_ONLY, 10, 20);
    System.out.println(byteBuffer.getChar());
    Thread.sleep(1000 * 60);
    fileChannel.close();
  }
}
