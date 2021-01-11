package huaminglin.demo.aspectj;

public class AspectJDemo {

  public static void main(String[] args) {
    String value = new BusinessLogic().myMethod("123");
    System.out.println(value);
  }
}
