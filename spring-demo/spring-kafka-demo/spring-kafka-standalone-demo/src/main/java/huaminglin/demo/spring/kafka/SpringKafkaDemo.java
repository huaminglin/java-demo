package huaminglin.demo.spring.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

public class SpringKafkaDemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringKafkaDemo.class);

    public static void main(String[] args) throws Exception {
        new SpringKafkaDemo().testAutoCommit();
    }

    public void testAutoCommit() throws Exception {
        final CountDownLatch latch = new CountDownLatch(4);
        MessageListener<Integer, String> listener = new MessageListener<Integer, String>() {

            @Override
            public void onMessage(ConsumerRecord<Integer, String> message) {
                logger.info("received: " + message);
                latch.countDown();
			}

        };
        KafkaMessageListenerContainer<Integer, String> container = createConsumer(listener);
        container.start();
        Thread.sleep(1000); // wait a bit for the container to start

        KafkaTemplate<Integer, String> publisher = createPublisher();
        publisher.setDefaultTopic("test");
        logger.info("Sending 4 messages ...");
        publisher.sendDefault(0, "message1");
        publisher.sendDefault(2, "message2");
        publisher.sendDefault(0, "message3");
        publisher.sendDefault(2, "m3ssage4");
        publisher.flush();
        latch.await(60, TimeUnit.SECONDS);

        container.stop();
        logger.info("Stop auto");
    }

    private KafkaMessageListenerContainer<Integer, String> createConsumer(MessageListener<Integer, String> listener) {
        ContainerProperties containerProps = new ContainerProperties("test");
        containerProps.setMessageListener(listener);
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        DefaultKafkaConsumerFactory<Integer, String> cf =
                                new DefaultKafkaConsumerFactory<Integer, String>(props);
        KafkaMessageListenerContainer<Integer, String> container =
                                new KafkaMessageListenerContainer<>(cf, containerProps);
        container.setBeanName("testAuto");
        return container;
    }

    private KafkaTemplate<Integer, String> createPublisher() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<Integer, String> pf =
                  new DefaultKafkaProducerFactory<Integer, String>(props);
        KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
        return template;
    }
}
