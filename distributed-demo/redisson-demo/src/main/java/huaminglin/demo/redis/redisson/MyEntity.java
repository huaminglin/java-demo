package huaminglin.demo.redis.redisson;

import java.io.Serializable;

public class MyEntity implements Serializable {

  private static final long serialVersionUID = 5952812854939081811L;

  private String name;

  public MyEntity() {
    name = "entity1";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
