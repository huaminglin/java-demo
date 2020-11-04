package huaminglin.demo.spring.boot.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("publisher")
@Component
public class MessagePublisher {

  private final AmqpAdmin amqpAdmin;
  private final AmqpTemplate amqpTemplate;

  @Autowired
  public MessagePublisher(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
    this.amqpAdmin = amqpAdmin;
    this.amqpTemplate = amqpTemplate;
  }

  public void publish(String queue, String message) {
    amqpTemplate.convertAndSend(queue, message);
    System.out.println("Publish " + message + " to " + queue);
  }

}
