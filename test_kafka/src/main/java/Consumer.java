import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    private final KafkaConsumer<String, String> consumer;
    private final int consumerId;
    private final Duration timeOut = Duration.ofSeconds(10);
    public Consumer(int consumerId) {
        this.consumerId = consumerId;
        Properties properties = new Properties();
        properties.put("client.id", "client-" + consumerId);
        properties.put("bootstrap.servers", "192.168.125.13:19092,192.168.125.13:19093");
        properties.put("group.id", "test-group");
        properties.put("zookeeper.session.timeout.ms", "4000");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("enable.auto.commit", "false");
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置分区策略
        properties.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("topic-test","my-topic"));
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void consume() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeOut);
            System.out.println("records count:" + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("client-id = %d , topic = %s, partition = %d , offset = %d, key = %s, value = %s%n",
                        this.consumerId,record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
            consumer.commitSync();
        }
    }

    public static void main(String[] args) {
        int threadSize = 2;
        // 创建多个线程，每个线程创建一个 KafkaConsumer
        for (int i = 0; i < threadSize; i++) {
            int id = i;
            new Thread(() -> new Consumer(id).consume()).start();
        }
    }
}
