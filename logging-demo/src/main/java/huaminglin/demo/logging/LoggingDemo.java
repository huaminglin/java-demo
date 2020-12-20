package huaminglin.demo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingDemo {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(LoggingDemo.class);
    MyLogItem item = new MyLogItem("myitem");
    logger.error("{}", item);
  }
}
