package more.ftf.zookeeper.queue;

/**
 * @author a8079
 * @title: DistributedBlockingQueue
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1717:05
 */

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 阻塞分布式队列
 * 扩展自简单分布式队列，在拿不到队列数据时，进行阻塞直到拿到数据
 */
public class DistributedBlockingQueue<T> extends DistributedSimpleQueue<T>{


    public DistributedBlockingQueue(ZkClient zkClient, String root) {
        super(zkClient, root);

    }


    @Override
    public T poll() throws Exception {

        while (true){ // 结束在latch上的等待后，再来一次

            final CountDownLatch    latch = new CountDownLatch(1);
            final IZkChildListener childListener = new IZkChildListener() {
                public void handleChildChange(String parentPath, List<String> currentChilds)
                        throws Exception {
                    latch.countDown(); // 队列有变化，结束latch上的等待
                }
            };
            zkClient.subscribeChildChanges(root, childListener);
            try{
                T node = super.poll(); // 获取队列数据
                if ( node != null ){
                    return node;
                } else {
                    latch.await(); // 拿不到队列数据，则在latch上await
                }
            } finally {
                zkClient.unsubscribeChildChanges(root, childListener);
            }

        }
    }

}
