package huaminglin.demo.test.avro.specific;

import example.avro.User;
import huaminglin.demo.avro.specific.UserManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserManagerUnitTest {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Test
    public void testUserBytes() throws IOException {
        User user = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();
        byte[] userBytes = UserManager.getUserBytes(user);
        String hex = bytesToHex(userBytes);
        assertEquals(userBytes.length, 25);
        String rawString = new String(userBytes);
        assertTrue(rawString.contains("Charlie"));// 436861726C6965
        assertTrue(rawString.contains("blue"));// 626C7565
        assertEquals(hex, "C301B2D1D8D3DE2833CE0E436861726C6965020008626C7565");
        Path path = Paths.get("/tmp/user.avro");
        Files.write(path, userBytes);
    }


    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

    @Test
    public void testParseUserFromBytes() throws IOException {
        InputStream stream = UserManagerUnitTest.class.getClassLoader().getResourceAsStream("user.avro");
        byte[] bytes = getBytesFromInputStream(stream);
        User user = UserManager.parseUserFromBytes(bytes);
        assertNotNull(user);
        assertEquals(user.getName().toString(), "Charlie");
    }
}
