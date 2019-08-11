package huaminglin.demo.spring.boot.amqp;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;

@Profile("consumer")
@Component
public class MessageConsumer {

	@RabbitListener(queues = "myQueue")
	public void processMessage(String content) {
		System.out.println("processMessage: " + content);
	}

}
