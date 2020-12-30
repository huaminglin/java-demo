package huaminglin.demo.logging;

import org.apache.logging.log4j.message.Message;

public class CustomMessage implements Message {
  private final MyLogItem item;

  public CustomMessage(MyLogItem item) {
    this.item = item;
  }

  @Override
  public String getFormattedMessage() {
    return "custom: " + item;
  }

  @Override
  public String getFormat() {
    return null;
  }

  @Override
  public Object[] getParameters() {
    return new Object[0];
  }

  @Override
  public Throwable getThrowable() {
    return null;
  }

}
