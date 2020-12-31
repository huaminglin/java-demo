package huaminglin.demo.logging;

import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

@Plugin(name = "CustomLayout", category = "Core", elementType = "layout", printObject = true)
public class CustomLayout extends AbstractStringLayout {
  private final String type;

  protected CustomLayout(String type) {
    super(StandardCharsets.UTF_8);
    this.type = type;
  }

  @PluginFactory
  public static CustomLayout createLayout(@PluginAttribute(value = "type", defaultString = "") String type) {
    return new CustomLayout(type);
  }

  @Override
  public String toSerializable(LogEvent event) {
    CustomMessage message = (CustomMessage) event.getMessage();
    return "CustomLayout: " + type + ':' + message;
  }
}
