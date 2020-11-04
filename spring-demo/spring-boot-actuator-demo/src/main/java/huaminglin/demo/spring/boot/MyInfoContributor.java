package huaminglin.demo.spring.boot;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;


@Component
public class MyInfoContributor implements InfoContributor {

  @Override
  public void contribute(Info.Builder builder) {
    Map<String, Integer> users = new HashMap<>();
    users.put("active", 1);

    builder.withDetail("users", users);
  }
}
