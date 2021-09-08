package huaminglin.demo.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public final class KafkaConsumerDemo {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "2");

    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    TopicPartition topicPartition = new TopicPartition("my-topic", 0);
    consumer.assign(Collections.singletonList(topicPartition));
    consumer.seekToBeginning(Collections.singletonList(topicPartition));
    System.out.println(consumer.assignment());
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
    System.out.println("Got records: " + records.count());
    for (ConsumerRecord<String, String> record : records) {
      System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(),
          record.value() + '\n');
    }
    consumer.commitSync();
    consumer.close();
  }

}
