package huaminglin.demo.bytebuddy;

import net.bytebuddy.asm.Advice;

public class HelloAdvice {
    public static String name = "name";

    public static void hello() {
      System.out.println(HelloAdvice.class + ".hello()");
    }

    @Advice.OnMethodEnter
    static void invokeBeforeEnterMethod(@Advice.Origin String method) {
    System.out.println("Before " + name);
  }

    @Advice.OnMethodExit
    static void invokeAfterExitMethod(@Advice.Origin String method) {
    System.out.println("After " + name);
  }
}
