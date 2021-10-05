package huaminglin.demo.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public final class KafkaProducerDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.RETRIES_CONFIG, 1);
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // Must set retries to non-zero when using the idempotent producer.
//    props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "myTransactionId1");
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
    Producer producer = new KafkaProducer(props);
    String topic = "my-topic";
    ProducerRecord record = new ProducerRecord(topic, Math.round(Math.random() * 100), "my-message");
    MyCallback callback = new MyCallback();
    Future future = producer.send(record, callback);
    RecordMetadata result = (RecordMetadata) future.get();
    long offset = result.offset();
    System.out.println(result.partition());
    System.out.println(offset);
    producer.close();
  }

}
