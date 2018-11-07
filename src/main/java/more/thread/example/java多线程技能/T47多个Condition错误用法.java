package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T47多个Condition错误与正确用法
 * @Description
 * @Author root
 * @Date 18-11-7 上午11:18
 * @Version 1.0
 **/
public class T47多个Condition错误用法 {

    public static void main (String argsp[]) {
        MyReentrantLockConditionService47 myReentrantLockConditionService47 = new MyReentrantLockConditionService47();

        MyReentrantLockConditionThread47_A myReentrantLockConditionThread47_a = new MyReentrantLockConditionThread47_A(myReentrantLockConditionService47);
        myReentrantLockConditionThread47_a.setName("A");

        MyReentrantLockConditionThread47_B myReentrantLockConditionThread47_b = new MyReentrantLockConditionThread47_B(myReentrantLockConditionService47);
        myReentrantLockConditionThread47_b.setName("B");

        myReentrantLockConditionThread47_a.start();
        myReentrantLockConditionThread47_b.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionService47.signalAll();//唤醒当前锁中的所有对象


//        begin awaitA, time = 1541561754549, thread Name = A
//        begin awaitB, time = 1541561754559, thread Name = B
//        begin signalAll, time = 1541561757559, thread Name = main
//        end awaitA, time = 1541561757559, thread Name = A
//        end awaitB, time = 1541561757559, thread Name = B
    }

}

class MyReentrantLockConditionService47 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitA, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitB, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll () {
        try {
            lock.lock();
            System.out.println("begin signalAll, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

class MyReentrantLockConditionThread47_A extends Thread {
    private MyReentrantLockConditionService47 myReentrantLockConditionService47;
    public MyReentrantLockConditionThread47_A (MyReentrantLockConditionService47 myReentrantLockConditionService47) {
        this.myReentrantLockConditionService47 = myReentrantLockConditionService47;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService47.awaitA();
    }
}

class MyReentrantLockConditionThread47_B extends Thread {
    private MyReentrantLockConditionService47 myReentrantLockConditionService47;
    public MyReentrantLockConditionThread47_B (MyReentrantLockConditionService47 myReentrantLockConditionService47) {
        this.myReentrantLockConditionService47 = myReentrantLockConditionService47;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService47.awaitB();
    }
}