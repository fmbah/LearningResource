package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T48多个Condition错误与正确用法
 * @Description
 * @Author root
 * @Date 18-11-7 上午11:18
 * @Version 1.0
 **/
public class T48多个Condition正确用法 {

    public static void main (String argsp[]) {
        MyReentrantLockConditionService48 myReentrantLockConditionService48 = new MyReentrantLockConditionService48();

        MyReentrantLockConditionThread48_A myReentrantLockConditionThread48_a = new MyReentrantLockConditionThread48_A(myReentrantLockConditionService48);
        myReentrantLockConditionThread48_a.setName("A");

        MyReentrantLockConditionThread48_B myReentrantLockConditionThread48_b = new MyReentrantLockConditionThread48_B(myReentrantLockConditionService48);
        myReentrantLockConditionThread48_b.setName("B");

        myReentrantLockConditionThread48_a.start();
        myReentrantLockConditionThread48_b.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionService48.signalAll_A();//唤醒当前锁中的所有对象

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionService48.signalAll_B();


//        begin awaitA, time = 1541562175446, thread Name = A
//        begin awaitB, time = 1541562175447, thread Name = B
//        begin signalAll_A, time = 1541562178447, thread Name = main
//        end awaitA, time = 1541562178447, thread Name = A
//        begin signalAll_B, time = 1541562181447, thread Name = main
//        end awaitB, time = 1541562181447, thread Name = B
    }

}

class MyReentrantLockConditionService48 {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            conditionA.await();
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
            conditionB.await();
            System.out.println("end awaitB, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A () {
        try {
            lock.lock();
            System.out.println("begin signalAll_A, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B () {
        try {
            lock.lock();
            System.out.println("begin signalAll_B, time = " + System.currentTimeMillis() + ", thread Name = " + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

class MyReentrantLockConditionThread48_A extends Thread {
    private MyReentrantLockConditionService48 myReentrantLockConditionService48;
    public MyReentrantLockConditionThread48_A (MyReentrantLockConditionService48 myReentrantLockConditionService48) {
        this.myReentrantLockConditionService48 = myReentrantLockConditionService48;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService48.awaitA();
    }
}

class MyReentrantLockConditionThread48_B extends Thread {
    private MyReentrantLockConditionService48 myReentrantLockConditionService48;
    public MyReentrantLockConditionThread48_B (MyReentrantLockConditionService48 myReentrantLockConditionService48) {
        this.myReentrantLockConditionService48 = myReentrantLockConditionService48;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService48.awaitB();
    }
}