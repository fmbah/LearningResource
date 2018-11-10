package more.thread.example.java多线程技能;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T56ReentrantLockCondition常用方法5
 * @Description
 *              ReentrantLock.lockInterruptibly();和ReentrantLock.lock()方法；在Thread.interrupt()中断的时候,会出现异常
 *
 * @Author root
 * @Date 18-11-8 下午4:04
 * @Version 1.0
 **/
public class T56ReentrantLockCondition常用方法5 {
//    public static void main (String args[]) {
//        final MyReentrantConditionService56 myReentrantConditionService56 = new MyReentrantConditionService56();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                myReentrantConditionService56.testMethod();
//            }
//        };
//
//        Thread thread_0 = new Thread(runnable);
//        thread_0.setName("A");
//        thread_0.start();
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Thread thread_1 = new Thread(runnable);
//        thread_1.setName("B");
//        thread_1.start();
//
////        thread_1.interrupt();//打标记,无非就是想复现 ReentrantLock.lockInterruptibly()方法在现在被中断的情况会出现异常
//        System.out.println("main end!");
//
//    }

    public static void main (String args[]) {
        final MyReentrantLockConditionService56_1 myReentrantLockConditionService56_1 = new MyReentrantLockConditionService56_1();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                myReentrantLockConditionService56_1.testMethod();
                myReentrantLockConditionService56_1.testMethod1();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("A");

        Thread thread_1 = new Thread(runnable);
        thread_1.setName("B");

        thread.start();
        thread_1.start();
    }
}

class MyReentrantConditionService56 {
    private ReentrantLock lock = new ReentrantLock();
    public void testMethod () {
        try {
            lock.lockInterruptibly();
//            lock.lock();
            System.out.println("lock begin time :" + System.currentTimeMillis() + ", ThreadName : " + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                Math.random();
                new String();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("lock end time :" + System.currentTimeMillis() + ", ThreadName : " + Thread.currentThread().getName());
        }
    }
}


class MyReentrantLockConditionService56_1 {
    private ReentrantLock lock = new ReentrantLock();
    public void testMethod () {
        try {
            if (lock.tryLock()) {
                System.out.println("获取到了锁,当前线程名: " + Thread.currentThread().getName());
            } else {
                System.out.println("未获取到了锁,当前线程名: " + Thread.currentThread().getName());
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void testMethod1 () {
        try {
            if (lock.tryLock(3L, TimeUnit.SECONDS)) {
                System.out.println("获取到了锁,当前线程名: " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } else {
                System.out.println("未获取到了锁,当前线程名: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

