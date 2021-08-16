package huaminglin.demo.spring.boot;

import java.util.Date;

public class MyValue {
  private final Date date;

  public MyValue(String date) {
    this.date = new Date(Integer.parseInt(date) * 1000);
  }

  @Override
  public String toString() {
    return "MyValue{" +
        "date=" + date +
        '}';
  }
}
