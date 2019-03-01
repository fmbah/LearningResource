package more.thread.example.java多线程技能.Concurrent线程类;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockTest
 * @Description
 * @Author root
 * @Date 19-2-18 上午11:06
 * @Version 1.0
 **/
public class ReentrantLockTest {

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                lockTest.test();
                lockTest.test1();
            }).start();
        }
    }


}


class LockTest{
    private Lock lock = new ReentrantLock();
    public synchronized void test() {
        System.out.println(Thread.currentThread().getName() + "test...1111..start" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "test...111111..end" + System.currentTimeMillis());
        }
    }

    public void test1() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "test1...2222222..start" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "test1..222222...end" + System.currentTimeMillis());
        }
        lock.unlock();
    }

}