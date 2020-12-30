package huaminglin.demo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggingDemo {

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(LoggingDemo.class);
    MyLogItem item = new MyLogItem("myitem");
    CustomMessage message = new CustomMessage(item);
    logger.error(message);
  }
}
