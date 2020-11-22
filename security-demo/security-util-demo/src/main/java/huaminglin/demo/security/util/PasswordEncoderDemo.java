package huaminglin.demo.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoderDemo {

  private static String encodeStandardPasswordEncoder(String secret, String rawPassword) {
    // "SHA-256"
    PasswordEncoder encoder = new StandardPasswordEncoder(secret);
    String value = encoder.encode(rawPassword);
    return value;
  }

  private static boolean matchStandardPasswordEncoder(String secret, CharSequence rawPassword, String encodedPassword) {
    // "SHA-256"
    PasswordEncoder encoder = new StandardPasswordEncoder(secret);
    return encoder.matches(rawPassword, encodedPassword);
  }

  private static String encodeBcryptPasswordEncoder(String rawPassword) {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    String value = encoder.encode(rawPassword);
    return value;
  }

  private static boolean matchBcryptPasswordEncoder(CharSequence rawPassword, String encodedPassword) {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(rawPassword, encodedPassword);
  }

  public static void main(String[] args) {
    {
      String hashedPassword = encodeStandardPasswordEncoder("secret", "123456");
      System.out.println(hashedPassword.length() + ":" + hashedPassword); // 80:dfcc376896865ee3157177dae89a88158253446edb45f344b7e45400423241fffcbf80a58db6252b
      boolean matched = matchStandardPasswordEncoder("secret", "123456", hashedPassword);
      System.out.println(matched); // true
    }
    {
      String hashedPassword = encodeBcryptPasswordEncoder("123456");
      System.out.println(hashedPassword.length() + ":" + hashedPassword); // 60:$2a$10$X.qx3O8iBM8UiELsXOaAhunYnKHauI9goJc0tsB6cIz4pTUzTcYDS
      boolean matched = matchBcryptPasswordEncoder("123456", hashedPassword);
      System.out.println(matched); // true
    }
  }
}
