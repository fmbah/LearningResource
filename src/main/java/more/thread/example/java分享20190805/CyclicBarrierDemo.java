package more.thread.example.java分享20190805;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        int threadNum = 5;
        AtomicInteger threadSum = new AtomicInteger();
        cyclicBarrier = new CyclicBarrier(5, () -> System.out.println(Thread.currentThread().getName() + ",完成聚合任务"));

        for (int i = 0; i < threadNum; i++) {
            final int sleepSeconds = i;
            new Thread(() -> {
                try {
                    Thread.sleep(sleepSeconds * 1000);
                    System.out.println(Thread.currentThread().getName()+"到达栅栏");
                    threadSum.getAndIncrement();
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"冲破栅栏" + threadSum.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    // TODO:  适合场景  多个线程异步计算，完成后聚合计算结果

    // TODO： 和CountDownLatch的区别？

    // TODO： CyclicBarrier可循环使用(一组线程相互之间等待，达到一个共同点)，CountDownLatch是一次性的（某线程等待其他线程完成）
}
