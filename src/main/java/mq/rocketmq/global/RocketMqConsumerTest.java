package mq.rocketmq.global;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author fmbah
 * @title: RocketMqProducerTest
 * @projectName LearningResource
 * @description: RocketMq消费者示例
 * @date 2019/12/22 15:01
 */
public class RocketMqConsumerTest {


    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("reliable_push_consumer");

        // Specify name server addresses.
//        consumer.setNamesrvAddr("106.13.81.173:9876");
        consumer.setNamesrvAddr("192.168.56.105:9876");

        // Subscribe one more more topics to consume.
        try {
            consumer.subscribe(
                    // 订阅相应消息类型
                    "reliable_topic"
                    // 过滤消息通配符；例如TageA || TagB皆可
                    , "*"
                    );
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {

                try {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    e.printStackTrace();
                    // 消息重试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");

    }

}
