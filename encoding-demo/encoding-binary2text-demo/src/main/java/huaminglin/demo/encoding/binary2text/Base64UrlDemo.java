package huaminglin.demo.encoding.binary2text;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class Base64UrlDemo {
  public static byte[] getBytesFromUUID(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }

  public static UUID getUUID4Slash() {
    while (true) {
      UUID randomUUID = UUID.randomUUID();
      byte[] bytes = getBytesFromUUID(randomUUID);
      String base64value = Base64.getEncoder().encodeToString(bytes);
      if (base64value.contains("/")) {
        return randomUUID;
      }
    }
  }

  public static void main(String[] args) {
    UUID uuid4Slash = getUUID4Slash();
    byte[] bytes = getBytesFromUUID(uuid4Slash);
    System.out.println("getEncoder: " + Base64.getEncoder().encodeToString(bytes));
    System.out.println("getUrlEncoder: " + Base64.getUrlEncoder().encodeToString(bytes));
  }
}
