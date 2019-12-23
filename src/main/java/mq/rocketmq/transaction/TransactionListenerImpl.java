package mq.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);


    private AtomicInteger transactionUNKNOWIndex = new AtomicInteger(0);
    private AtomicInteger transactionCOMMIT_MESSAGEIndex = new AtomicInteger(0);
    private AtomicInteger transactionROLLBACK_MESSAGEIndex = new AtomicInteger(0);



    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);


        // 全部扔到回查本地事物状态方法中
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:

                    System.out.println("中间状态：" + transactionUNKNOWIndex.getAndIncrement());

                    // 中间状态，这意味着需要MQ进行检查以确定状态。
                    return LocalTransactionState.UNKNOW;
                case 1:

                    System.out.println("允许消费：" + transactionCOMMIT_MESSAGEIndex.getAndIncrement());

                    // 表示允许消费者使用此消息。
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:

                    System.out.println("删除消息：" + transactionROLLBACK_MESSAGEIndex.getAndIncrement());

                    // 回滚事务，表示邮件将被删除，不允许使用。
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}