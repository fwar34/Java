import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("UniqueProducerGroupName");
        defaultMQProducer.setNamesrvAddr("192.168.32.228:9876");
        defaultMQProducer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TEST_FL", ("hello rocketmq" + i).getBytes());
            SendResult sendResult = defaultMQProducer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        defaultMQProducer.shutdown();
    }
}
