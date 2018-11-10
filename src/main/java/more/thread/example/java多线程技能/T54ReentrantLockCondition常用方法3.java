package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T54ReentrantLockCondition常用方法3
 * @Description
 * @Author root
 * @Date 18-11-8 下午3:32
 * @Version 1.0
 **/
public class T54ReentrantLockCondition常用方法3 {

    public static void main (String args[]) {
        final MyReentrantLockConditionService54 myReentrantLockConditionService54 = new MyReentrantLockConditionService54();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myReentrantLockConditionService54.waitMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionService54.notifyMethod();
    }

}

class MyReentrantLockConditionService54 {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
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
            System.out.println("有没有与此锁有关的newCondition: " + lock.hasWaiters(condition) +
            ", 数量多少? " + lock.getWaitQueueLength(condition));
            condition.signalAll();
            System.out.println("有没有与此锁有关的newCondition: " + lock.hasWaiters(condition) +
                    ", 数量多少? " + lock.getWaitQueueLength(condition));
        } finally {
            lock.unlock();
        }
    }


}