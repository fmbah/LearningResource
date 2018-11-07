package more.thread.example.java多线程技能;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T52ReentrantLock常用方法
 * @Description
 *              ReentrantLock.getHoldCount()方法  获取当前ReentrantLock类正在锁的数量!!!!
 *              ReentrantLock.getQueueLength()方法  获取正在等待锁释放的线程的数量
 *              ReentrantLock.getWaitQueueLength(Condition) 获取正在等待的线程的数量
 * @Author root
 * @Date 18-11-7 下午7:47
 * @Version 1.0
 **/
public class T52ReentrantLock常用方法 {

    public static void main (String args[]) {
//        MyReentrantLockConditionService52 myReentrantLockConditionService52 = new MyReentrantLockConditionService52();
//        myReentrantLockConditionService52.testMethod1();//这个是输出两个,因为前一个锁没有释放
//        myReentrantLockConditionService52.testMethod2();//这个是上面的方法执行完成之后,只有当前方法一个锁被锁住,所以输出一个


//        final MyReentrantLockConditionService52_1 myReentrantLockConditionService52_1 = new MyReentrantLockConditionService52_1();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                myReentrantLockConditionService52_1.testMethod();
//            }
//        };
//
//        Thread[] threads = new Thread[10];
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(runnable);
//        }
//
//        for (int i = 0; i < threads.length; i++) {
//            threads[i].start();
//        }
//
//        System.out.println("估计线程中,正在等待锁释放的线程数量: " + myReentrantLockConditionService52_1.lock.getQueueLength());


        final MyReentrantLockConditionService52_2 myReentrantLockConditionService52_2 = new MyReentrantLockConditionService52_2();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myReentrantLockConditionService52_2.waitMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i ++) {
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionService52_2.notifyMethod();
    }

}

class MyReentrantLockConditionService52 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod1() {
        try {
            lock.lock();
            System.out.println("lock.toString(): " + lock.toString() + " testMethod1 holdCount:" + lock.getHoldCount());
            testMethod2();
        } finally {
            lock.unlock();
        }
    }

    public void testMethod2() {
        try {
            lock.lock();
            condition.await(3, TimeUnit.SECONDS);
            System.out.println("lock.toString(): " + lock.toString() + " testMethod2 holdCount:" + lock.getHoldCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-11-7 下午8:34
 * @Description:
 */
class MyReentrantLockConditionService52_1 {
    public ReentrantLock lock = new ReentrantLock();

    public void testMethod() {
        try {
            lock.lock();

            Thread.sleep(300);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyReentrantLockConditionService52_2 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod () {
        try {
            lock.lock();

            condition.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod () {
        try {
            lock.lock();

            System.out.println("当前有多少个线程正在等待:" + lock.getWaitQueueLength(condition));
        }  finally {
            lock.unlock();
        }
    }

}