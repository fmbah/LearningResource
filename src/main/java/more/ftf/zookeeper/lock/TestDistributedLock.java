package more.ftf.zookeeper.lock;

import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

/**
 * @author a8079
 * @title: TestDistributedLock
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1715:24
 */
public class TestDistributedLock {

    public static void main(String[] args) {

        final ZkClientExt zkClientExt1 = new ZkClientExt("192.168.56.104:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex1 = new SimpleDistributedLockMutex(zkClientExt1, "/sort");

        final ZkClientExt zkClientExt2 = new ZkClientExt("192.168.56.104:2181", 5000, 5000, new BytesPushThroughSerializer());
        final SimpleDistributedLockMutex mutex2 = new SimpleDistributedLockMutex(zkClientExt2, "/sort");

        try {
            mutex1.acquire();
            System.out.println("Client1 locked");
            Thread client2Thd = new Thread(() -> {
                try {
                    mutex2.acquire();
                    System.out.println("Client2 locked");
                    mutex2.release();
                    System.out.println("Client2 released lock");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            client2Thd.start();
            Thread.sleep(5000);
            mutex1.release();
            System.out.println("Client1 released lock");

            client2Thd.join();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
