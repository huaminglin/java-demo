package huaminglin.demo.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public final class LoggerHierarchyDemo {

  public static void main(String[] args) {
    {
      Logger logger = LogManager.getLogger("a");
      logger.info("a");
    }
    {
      Logger logger = LogManager.getLogger("a.b.c");
      logger.info("a.b.c");
    }
    {
      Logger logger = LogManager.getLogger("a.b");
      logger.info("a.b");

    }
    {
      Logger logger1 = LogManager.getLogger("a");
      System.out.println(logger1.getLevel());
      Logger logger2 = LogManager.getLogger("a.b");
      System.out.println(logger2.getLevel());
      Logger logger3 = LogManager.getLogger("a.b.c");
      System.out.println(logger3.getLevel());
      // Set a breakpoint and check:
      // logger1.privateConfig.loggerConfigLevel: INFO
      // logger1.privateConfig.loggerConfig: LoggerConfig@2425
      // logger2.privateConfig.loggerConfigLevel: INFO
      // logger2.privateConfig.loggerConfig: LoggerConfig@2425
      // logger3.privateConfig.loggerConfigLevel: INFO
      // logger3.privateConfig.loggerConfig: LoggerConfig@2425

    }
    Configurator.setLevel("a.b", Level.DEBUG);
    System.out.println("Level changed.");
    {
      Logger logger1 = LogManager.getLogger("a");
      System.out.println(logger1.getLevel());
      Logger logger2 = LogManager.getLogger("a.b");
      System.out.println(logger2.getLevel());
      Logger logger3 = LogManager.getLogger("a.b.c");
      System.out.println(logger3.getLevel());
      // Set a breakpoint and check:
      // logger1.privateConfig.loggerConfigLevel: INFO
      // logger1.privateConfig.loggerConfig: LoggerConfig@2425
      // logger2.privateConfig.loggerConfigLevel: DEBUG
      // logger2.privateConfig.loggerConfig: LoggerConfig@2461
      // logger3.privateConfig.loggerConfigLevel: DEBUG
      // logger3.privateConfig.loggerConfig: LoggerConfig@2461
    }
  }
}
