package more.thread.example.java多线程技能;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T55ReentrantLockCondition常用方法4
 * @Description
 *              new ReentrantLock(true)==>lock.isFair()=true   判断当前锁的是否为公平锁,  默认情况下为非公平锁
 *
 *              ReentrantLock.isHeldByCurrentThread() 判断当前锁是否被锁定
 *
 *              ReentrantLock.isLocked() 查询此锁定是否由任意线程保持
 *
 * @Author root
 * @Date 18-11-8 下午3:44
 * @Version 1.0
 **/
public class T55ReentrantLockCondition常用方法4 {

    public static void main (String args[]) {
        final MyReentrantLockConditionService55 myReentrantLockConditionService55 = new MyReentrantLockConditionService55(false);
        System.out.println(myReentrantLockConditionService55.lock.isFair());


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myReentrantLockConditionService55.testMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(200);
            System.out.println("当前锁定是否由任意线程保持?  " + myReentrantLockConditionService55.lock.isLocked());
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前锁定是否由任意线程保持?  " + myReentrantLockConditionService55.lock.isLocked());
    }

}

class MyReentrantLockConditionService55 {
    public ReentrantLock lock;

    public MyReentrantLockConditionService55 (boolean isFair) {
        lock = new ReentrantLock(isFair);
    }
    public void testMethod () {
        try {
            System.out.println("锁定前,  当前锁是否锁定:" + lock.isHeldByCurrentThread());
            lock.lock();
            Thread.sleep(3000);
            System.out.println("锁定后,  当前锁是否锁定:" + lock.isHeldByCurrentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("解除锁定后,  当前锁是否锁定:" + lock.isHeldByCurrentThread());
        }
    }
}