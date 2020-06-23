package huaminglin.demo.aspectj;

public class AspectJDemo  {
    public static void main(String[] args) {
        String value = new BusinessLogic().execute("123");
        System.out.println(value);
    }
}
