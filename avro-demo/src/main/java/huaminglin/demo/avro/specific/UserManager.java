package huaminglin.demo.avro.specific;

import example.avro.User;
import java.io.IOException;
import java.nio.ByteBuffer;

public class UserManager {

  public static byte[] getUserBytes(User user) throws IOException {
    ByteBuffer bb = user.toByteBuffer();
    byte[] b = new byte[bb.remaining()];
    bb.get(b);
    return b;
  }

  public static User parseUserFromBytes(byte[] bytes) throws IOException {
    ByteBuffer buffer = ByteBuffer.wrap(bytes);
    User user = User.fromByteBuffer(buffer);
    return user;
  }
}
