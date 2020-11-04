package huaminglin.demo.jdk.jmx;

public class Calculator implements CalculatorMBean {

  @Override
  public String getName() {
    return "Calculator";
  }

  @Override
  public int add(int x, int y) {
    return x + y;
  }
}
