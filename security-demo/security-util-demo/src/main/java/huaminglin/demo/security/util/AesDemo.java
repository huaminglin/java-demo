package huaminglin.demo.security.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesDemo {

  private static final byte[] keys = new byte[32];

  private static String encrypt(String plainText)
      throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    SecretKey secretKey = new SecretKeySpec(keys, "AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    byte[] p = plainText.getBytes(StandardCharsets.UTF_8);
    return new String(Base64.getEncoder().encode(cipher.doFinal(p)), StandardCharsets.UTF_8);
  }

  private static String decrypt(String encryptedMessage)
      throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    SecretKey secretKey = new SecretKeySpec(keys, "AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    byte[] p = Base64.getDecoder().decode(encryptedMessage.getBytes(StandardCharsets.UTF_8));
    return new String(cipher.doFinal(p), StandardCharsets.UTF_8);
  }

  private static void aesCipherStrength() {
    try {
      int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
      System.out.println("Max AES key length = " + maxKeyLen);
    } catch (Exception e) {
      System.out.println("FAILED: No AES found!");
    }
  }

  public static void main(String[] args)
      throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    aesCipherStrength();

    String encrypted = encrypt("plainText");
    System.out.println(encrypted);
    System.out.println(decrypt(encrypted));
  }
}
