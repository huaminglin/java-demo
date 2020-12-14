package huaminglin.demo.spring.typeconversion;

public class MyAge {
  private String age;

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "MyAge{" +
        "age='" + age + '\'' +
        '}';
  }
}
