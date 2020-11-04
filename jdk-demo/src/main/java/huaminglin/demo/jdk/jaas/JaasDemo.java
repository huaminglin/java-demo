package huaminglin.demo.jdk.jaas;

import java.security.Principal;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JaasDemo {

  public static void main(String[] args) throws LoginException {
    System.setProperty(
        "java.security.auth.login.config",
        ClassLoader.getSystemClassLoader().getResource("demo-login.config").toString()
    );
    LoginContext lc = new LoginContext("JaasDemoLoginModule", new JaasDemoCallbackHandler("user1"));
    lc.login();
    Subject subject = lc.getSubject();
    Set<Principal> principals = subject.getPrincipals();
    System.out.println("principals: " + principals.size());
    principals.forEach(a -> System.out.println(a));
  }
}
