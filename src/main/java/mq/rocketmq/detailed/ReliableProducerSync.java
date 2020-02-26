package mq.rocketmq.detailed;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author a8079
 * @title: ReliableProducerSync
 * @projectName nio
 * @description: 可靠同步发送
 * @date 2020/2/2610:12
 */
public class ReliableProducerSync {

    @Test
    public void test0() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        //        this.createTopicKey = "TBW102";
        //        this.defaultTopicQueueNums = 4;
        //        this.sendMsgTimeout = 3000;
        //        this.compressMsgBodyOverHowmuch = 4096;
        //        this.retryTimesWhenSendFailed = 2;
        //        this.retryTimesWhenSendAsyncFailed = 2;
        //        this.retryAnotherBrokerWhenNotStoreOK = false;
        //        this.maxMessageSize = 4194304;
        //        this.traceDispatcher = null;
        DefaultMQProducer producer = new DefaultMQProducer("reliable_group");
        producer.setNamesrvAddr("192.168.56.105:9876");
        producer.start();

        for (int i = 60; i < 70; i++) {
            Message message = new Message();

            message.setTopic("reliable_topic");
            message.setTags("reliable_tags");

            // 默认消息最大容量为4M
//            byte[] bytes = new byte[1024 * 1024 * 4 * 10];
//            message.setBody(bytes);
            message.setBody(("message_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));


            SendResult sendResult = producer.send(message);

            System.out.printf("sendResult: %s%n ", JSONObject.toJSONString(sendResult));
        }

//        Thread.sleep(100000000);


        // switch(this.serviceState) {
        //        case RUNNING:
        //            this.mQClientFactory.unregisterProducer(this.defaultMQProducer.getProducerGroup());
        //            if (shutdownFactory) {
        //                this.mQClientFactory.shutdown();
        //            }
        //
        //            this.log.info("the producer [{}] shutdown OK", this.defaultMQProducer.getProducerGroup());
        //            this.serviceState = ServiceState.SHUTDOWN_ALREADY;
        //        case CREATE_JUST:
        //        case START_FAILED:
        //        case SHUTDOWN_ALREADY:
        //        default:
        //        }
        producer.shutdown();

    }

}
