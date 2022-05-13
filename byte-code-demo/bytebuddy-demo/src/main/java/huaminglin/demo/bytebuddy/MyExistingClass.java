package huaminglin.demo.bytebuddy;

public final class MyExistingClass {
  public static final String NAME = "name";

  public static void hello() {
    System.out.println(MyExistingClass.class.getName() + ".hello()");
  }
}
