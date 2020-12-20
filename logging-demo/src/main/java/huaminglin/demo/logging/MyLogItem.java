package huaminglin.demo.logging;

public class MyLogItem {
  private String value;

  public MyLogItem(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "MyLogItem{" +
        "value='" + value + '\'' +
        '}';
  }
}
