package huaminglin.demo.spring.boot.stomp;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @MessageMapping("courier")
  @SendTo("/topic/messages")
  public CourierLocation send(CourierLocation location) throws Exception {
    String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
    CourierLocation locationOut = new CourierLocation();
    locationOut.setCourierId(location.getCourierId());
    locationOut.setLocation(time);
    return locationOut;
  }
}
