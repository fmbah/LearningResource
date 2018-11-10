package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T53ReentrantLock常用方法2
 * @Description
 *              ReentrantLock.hasQueuedThread(thread) 当前线程是否正在等待获取此锁
 *              ReentrantLock.hasQueuedThreads() 是否线程正在等待获取此锁
 * @Author root
 * @Date 18-11-8 下午1:45
 * @Version 1.0
 **/
public class T53ReentrantLock常用方法2 {

    public static void main (String args[]) {
        final MyReentrantLockConditionService53 myReentrantLockConditionService53 = new MyReentrantLockConditionService53();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myReentrantLockConditionService53.testMethod();
            }
        };

        Thread thread_0 = new Thread(runnable);
        thread_0.setName("A");
        thread_0.start();
        Thread thread_1 = new Thread(runnable);
        thread_1.setName("B");
        thread_1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread_0: " + myReentrantLockConditionService53.lock.hasQueuedThread(thread_0));
        System.out.println("thread_1: " + myReentrantLockConditionService53.lock.hasQueuedThread(thread_1));

        System.out.println("" + myReentrantLockConditionService53.lock.hasQueuedThreads());
    }

}

class MyReentrantLockConditionService53 {
    public ReentrantLock lock = new ReentrantLock();
    public void testMethod () {
        try {
            lock.lock();
            System.out.println("ThreadName :" + Thread.currentThread().getName());

            Thread.sleep(Integer.MAX_VALUE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}