import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class OrderdProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("UniqueProducerGroupName");
        producer.setNamesrvAddr("192.168.32.228:9876");
        producer.start();

        String[] tags = new String[]{"tagA", "tagB", "tagC", "tagD"};
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TEST_FL", tags[i % tags.length], "Key" + i, ("Hello" + i).getBytes());
            SendResult result = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, i);
            System.out.printf("%s%n", result);
        }

        producer.shutdown();
    }
}
