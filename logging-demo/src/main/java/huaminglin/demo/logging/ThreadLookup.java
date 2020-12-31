package huaminglin.demo.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "thread", category = StrLookup.CATEGORY)
public class ThreadLookup implements StrLookup {
  private static String formatThreadName(String name) {
    return "[ " + name + " ]";
  }

  @Override
  public String lookup(String key) {
    return formatThreadName(Thread.currentThread().getName());
  }

  @Override
  public String lookup(LogEvent event, String key) {
    return formatThreadName(event.getThreadName() == null ? Thread.currentThread().getName() : event.getThreadName());
  }

}

