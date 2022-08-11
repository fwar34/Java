import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("UniqueProducerGroupName");
        defaultMQProducer.setNamesrvAddr("192.168.32.228:9876");
        defaultMQProducer.start();

        String[] tags = new String[] {"tagA", "tagB", "tagC"};

        List<OrderStep> orderStepList = new OrderProducer().buildOrders();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        for (int i = 0; i < 10; i++) {
            String body = dateStr + " Hello RocketMQ " + orderStepList.get(i);
            Message msg = new Message("TEST_FL", tags[i % tags.length], "KEY" + i, body.getBytes());

            SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Long id = (Long) arg;
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderStepList.get(i).getOrderId());
        }
        defaultMQProducer.shutdown();
    }

    private  static class OrderStep {
        private long orderId;
        private String desc;

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "OrderStep{" +
                    "orderId=" + orderId +
                    ", desc=" + desc + '\'' + '}';
        }
    }

    private List<OrderStep> buildOrders() {
        List<OrderStep> orderStepList = new ArrayList<>();

        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(15103111039L);
        orderStep.setDesc("创建");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111065L);
        orderStep.setDesc("创建");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111039L);
        orderStep.setDesc("付款");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103117235L);
        orderStep.setDesc("创建");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111065L);
        orderStep.setDesc("付款");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103117235L);
        orderStep.setDesc("付款");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111065L);
        orderStep.setDesc("完成");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111039L);
        orderStep.setDesc("推送");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103117235L);
        orderStep.setDesc("完成");
        orderStepList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(15103111039L);
        orderStep.setDesc("完成");
        orderStepList.add(orderStep);

        return orderStepList;
    }
}



