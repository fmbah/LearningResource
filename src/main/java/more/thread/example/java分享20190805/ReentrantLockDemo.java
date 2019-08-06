package more.thread.example.java分享20190805;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock LOCK = new ReentrantLock();
    private static int num1 = 0;
    private static int num2 = 0;
    private static int num3 = 0;
    private static Object object = new Object();

    public static void main(String[] args) {

        noLock();
        reentrantLock();
        synchronizedLock();

    }

    private static void noLock() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        num1++;
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("无锁,num1=" + num1);
    }

    private static void reentrantLock() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                LOCK.lock();
                try {
                    for (int j = 0; j < 10000; j++) {
                        num2++;
                    }
                } finally {
                    countDownLatch.countDown();
                    LOCK.unlock();
                }

            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ReentrantLock加锁,num2=" + num2);
    }

    private static void synchronizedLock() {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (object) {
                    try {
                        for (int j = 0; j < 10000; j++) {
                            num3++;
                        }
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ReentrantLock加锁,num3=" + num3);
    }

}
