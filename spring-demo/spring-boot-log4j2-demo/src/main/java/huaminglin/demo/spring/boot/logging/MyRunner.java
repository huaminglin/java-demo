package huaminglin.demo.spring.boot.logging;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
  private static Logger LOGGER = LoggerFactory.getLogger(MyRunner.class);

  @Override
  public void run(String... args) throws Exception {
    ThreadContext.put("id", UUID.randomUUID().toString());
    LOGGER.info("MyRunner: run()");
    ThreadContext.clearAll();
  }
}
