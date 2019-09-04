package more.thread.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    // 内存可见
    private static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(new VolatileThread()).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
    }

    public static class VolatileThread implements Runnable {

        @Override
        public void run() {
            System.out.println("开始flag循环");
            while (flag) {

            }
            System.out.println("结束flag循环");
        }
    }
}
