import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("UniqueConsumerGroupName");
        defaultMQPushConsumer.setNamesrvAddr("192.168.32.228:9876");
        // defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);

        defaultMQPushConsumer.subscribe("TEST", "*");
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.printf("%s:%s %n", new String(msg.getBody()), context.getMessageQueue().getBrokerName());
            }
            //System.out.println("--------------------------------------------------------------------");
            //System.out.printf("%s Receive New Messages:%s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        defaultMQPushConsumer.start();
        System.out.println("Consumer started.");
    }
}
