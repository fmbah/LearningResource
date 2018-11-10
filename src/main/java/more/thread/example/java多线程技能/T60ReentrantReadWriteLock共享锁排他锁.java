package more.thread.example.java多线程技能;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName T60ReentrantReadWriteLock共享锁排他锁
 * @Description
 *              同一时间读可以多个线程      共享锁
 *              同一时间写只能有一个线程    排他锁
 * @Author root
 * @Date 18-11-9 上午11:01
 * @Version 1.0
 **/
public class T60ReentrantReadWriteLock共享锁排他锁 {

    public static void main (String args[]) {
        final MyReentrantReadWriteLockService60 myReentrantReadWriteLockService60 = new MyReentrantReadWriteLockService60();

        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                myReentrantReadWriteLockService60.read();
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                super.run();
                myReentrantReadWriteLockService60.write();
            }
        };

        Thread[] threadAs = new Thread[5];
        Thread[] threadBs = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threadAs[i] = new Thread(threadA);
            threadBs[i] = new Thread(threadB);

            threadAs[i].start();
            threadBs[i].start();
        }
    }

}

class MyReentrantReadWriteLockService60 {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void read () {
        try {
            reentrantReadWriteLock.readLock().lock();
            System.out.println("获得读锁, " + Thread.currentThread().getName() + "=====" + System.currentTimeMillis());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void write () {
        try {
            reentrantReadWriteLock.writeLock().lock();
            System.out.println("获得写锁, " + Thread.currentThread().getName() + "=====" + System.currentTimeMillis());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}

