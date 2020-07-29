package huaminglin.demo.spring.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class MySocketTextHandler extends TextWebSocketHandler {

    public MySocketTextHandler() {

    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {

        String courierId = message.getPayload();
        session.sendMessage(new TextMessage("Getting location: " + courierId));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    session.sendMessage(new TextMessage("Update location: " + courierId));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
    }

}

