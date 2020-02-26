package more.thread.example.java异步转同步;

import java.util.concurrent.CountDownLatch;

/**
 * @author a8079
 * @title: Demo6
 * @projectName nio
 * @description: TODO
 * @date 2020/2/2415:44
 */
public class Demo6 {


    final static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {


        // 异步获取数据，等待数秒后，调用coutDown方法，解除await等待状态

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





    }
}
