package mq.rocketmq.detailed;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author a8079
 * @title: ReliableConsumerSync
 * @projectName nio
 * @description: 可靠消息消费
 * @date 2020/2/2611:44
 */
public class ReliableConsumerSync {

    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<MessageQueue, Long>();


    @Test
    public void test0() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 消息延迟与忙等待
        DefaultMQPullConsumer mqPullConsumer = new DefaultMQPullConsumer("reliable_pull_consumer");
        mqPullConsumer.setNamesrvAddr("192.168.56.105:9876");
        mqPullConsumer.setInstanceName("reliable_pull_consumer_instance_0");

        mqPullConsumer.start();

        Set<MessageQueue> messageQueues = mqPullConsumer.fetchSubscribeMessageQueues("reliable_topic");
        for (MessageQueue mq : messageQueues) {
            System.out.printf("从消息队列中消费：%s%n", mq);

            SINGLE_MQ:
            while (true) {
                PullResult pullResult = mqPullConsumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);

                System.out.printf("mqPullConsumer.pullBlockIfNotFound: %s%n", pullResult);

                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());

                switch (pullResult.getPullStatus()) {
                    case FOUND:

                        List<MessageExt> msgFoundList = pullResult.getMsgFoundList();

                        for (MessageExt msg : msgFoundList) {
                            System.out.printf("%s%n", msg);
                        }


                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case NO_NEW_MSG:
                        break SINGLE_MQ;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }
            }
        }

        mqPullConsumer.shutdown();

    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSE_TABLE.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSE_TABLE.put(mq, offset);
    }




    @Test
    public void test1() throws MQClientException {
        // 慢消费问题
        DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer("reliable_push_consumer");
        mqPushConsumer.setNamesrvAddr("192.168.56.105:9876");

//        mqPushConsumer.fetchSubscribeMessageQueues("reliable_topic");
        mqPushConsumer.subscribe("reliable_topic", "*");
//        mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        mqPushConsumer.start();
        System.out.printf("Consumer Started.%n");
    }


    public static void main(String[] args) throws MQClientException {
        // 慢消费问题
        DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer("reliable_push_consumer");
        mqPushConsumer.setNamesrvAddr("192.168.56.105:9876");

//        mqPushConsumer.fetchSubscribeMessageQueues("reliable_topic");
        mqPushConsumer.subscribe("reliable_topic", "*");
//        mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

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

        mqPushConsumer.start();
        System.out.printf("Consumer Started.%n");



    }

}
