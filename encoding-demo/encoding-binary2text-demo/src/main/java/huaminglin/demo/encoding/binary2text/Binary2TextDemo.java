package huaminglin.demo.encoding.binary2text;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;
import org.bitcoinj.core.Base58;

public class Binary2TextDemo {

    public static byte[] getBytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    public static byte[] get20Bytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[20]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putInt(123456);
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    public static void printBytes(byte[] bytes) {
        String base58value = Base58.encode(bytes);
        System.out.println(base58value + "/base58(" + bytes.length + " bytes):" + base58value.length());

        String base64value = Base64.getEncoder().encodeToString(bytes);
        System.out.println(base64value + "/base64(" + bytes.length + " bytes):" + base64value.length());
    }

    public static void main(String[] args) {
        UUID randomUUID = UUID.randomUUID();
        printBytes(getBytesFromUUID(randomUUID));
        printBytes(get20Bytes(randomUUID));
    }

}
