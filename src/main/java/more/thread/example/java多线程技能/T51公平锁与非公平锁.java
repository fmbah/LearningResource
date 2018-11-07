package more.thread.example.java多线程技能;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T50公平锁与非公平锁
 * @Description 公平锁: 获取锁的顺序按照加锁的顺序来分配,即先来先得的FIFO先进先出顺序
 *              非公平锁: 获取锁的抢占机制,是随机获得锁的,先来的不一定能得到锁,这个方式可能某些线程一直拿不到锁
 * @Author root
 * @Date 18-11-7 下午7:30
 * @Version 1.0
 **/
public class T51公平锁与非公平锁 {
    public static void main (String args[]) {
        //当是公平锁,线程打印的顺序是有序的
        //当是非公平锁,线程打印的数需是乱的,并不是start了,就一定会执行!
        final MyReentrantLockConditionService51 myReentrantLockConditionService51 = new MyReentrantLockConditionService51(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadName = " + Thread.currentThread().getName() + ",运行了");
                myReentrantLockConditionService51.testMethod();
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}

class MyReentrantLockConditionService51 {
    private ReentrantLock lock;
    public MyReentrantLockConditionService51 (Boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void testMethod () {
        try {
            lock.lock();
            System.out.println("ThreadName = " + Thread.currentThread().getName() + ",获得了锁");
        } finally {
            lock.unlock();
        }
    }
}