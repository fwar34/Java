package com.test.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;

public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");
        producer.setNamesrvAddr("192.168.125.13:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TEST", "tag" + i, "key" + i, ("Hello" + i).getBytes());
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%s:%s-queue:%s %n",
                            new String(msg.getBody()),
                            sendResult.getMessageQueue().getBrokerName(),
                            sendResult.getMessageQueue().getQueueId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.printf("error:%s %n", throwable.getMessage());
                }
            });
        }
    }
}
