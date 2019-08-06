package more.thread.example.java分享20190805;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            // TODO: 每个线程sleep时间不一样，第i个线程sleep i秒
            final int sleepSeconds = i;
            final int taskNo = i;
            new Thread(() -> {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(sleepSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("任务:" + taskNo + ",耗时:" + (System.currentTimeMillis() - start));
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务总耗时：" + (System.currentTimeMillis() - begin));
    }

    // TODO: 2019-08-05 适合场景  主线程等待 各个子线程完成任务
}
