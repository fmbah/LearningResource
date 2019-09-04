package more.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    /**
     * 可重入锁：可重入锁是指同一个线程可以多次获得同一把锁；
     *      ReentrantLock和关键字Synchronized都是可重入锁
     *
     * 可中断锁：可中断锁时只线程在获取锁的过程中，是否可以相应线程中断操作。
     *          synchronized是不可中断的，ReentrantLock是可中断的
     *
     * 公平锁和非公平锁：公平锁是指多个线程尝试获取同一把锁的时候，
     *          获取锁的顺序按照线程到达的先后顺序获取，而不是随机插队的方式获取。
     *          synchronized是非公平锁，而ReentrantLock是两种都可以实现，不过默认是非公平锁
     * @param args
     */
    public static void main(String[] args) {
//        for (int i = 0; i < 3; i++) {
//            new Thread(new ReentrantLockThread(), "t" + i).start();
//        }
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(num);


//        new ReentrantLockThread1("t1", 1).start();
//        ReentrantLockThread1 t2 = new ReentrantLockThread1("t2", 2);
//        t2.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t2.interrupt();

        ReentrantLockThread2 t1 = new ReentrantLockThread2("t1");
        ReentrantLockThread2 t2 = new ReentrantLockThread2("t2");
        t1.start();
        t2.start();

    }

    static int num = 0;
//    static ReentrantLock lock = new ReentrantLock();
    static ReentrantLock lock = new ReentrantLock(true);
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    static ReentrantLock lock3 = new ReentrantLock();
    public static class ReentrantLockThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i< 100; i++) {
                try {
                    // 重入锁
                    lock.lock();
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ", 获得锁");
                    num++;
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }
    }

    public static class ReentrantLockThread1 extends Thread {

        int type;
        public ReentrantLockThread1(String name, int type) {
            super(name);
            this.type = type;
        }
        @Override
        public void run() {
            try {
                if (1 == type) {
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(2);
                    lock2.lockInterruptibly();
                } else if(2 == type) {
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(2);
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }
    }

    public static class ReentrantLockThread2 extends Thread {

        public ReentrantLockThread2(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            try {
                if (lock3.tryLock(3L, TimeUnit.SECONDS)) {
                    TimeUnit.SECONDS.sleep(5L);
                    System.out.println(Thread.currentThread().getName() + "，执行完成.....");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock3.isHeldByCurrentThread()) {
                    lock3.unlock();
                }
            }

        }

    }
}
