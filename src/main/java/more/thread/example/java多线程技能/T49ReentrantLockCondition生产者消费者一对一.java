package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T49ReentrantLockCondition生产者消费者
 * @Description
 * @Author root
 * @Date 18-11-7 上午11:50
 * @Version 1.0
 **/
public class T49ReentrantLockCondition生产者消费者一对一 {

    public static void main (String argsp[]) {
        MyReentrantLockConditionService49 myReentrantLockConditionService49 = new MyReentrantLockConditionService49();

        MyReentrantLockThread49Product myReentrantLockThread49Product = new MyReentrantLockThread49Product(myReentrantLockConditionService49);

        MyReentrantLockThread49Customer myReentrantLockThread49Customer = new MyReentrantLockThread49Customer(myReentrantLockConditionService49);


        myReentrantLockThread49Product.start();
        myReentrantLockThread49Customer.start();

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}

class MyReentrantLockConditionService49 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set () {
        try {
            lock.lock();
            while (hasValue == true) {
                condition.await();
            }
            System.out.println("打印  ******五角星******");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get () {
        try {
            lock.lock();

            while (hasValue == false) {
                condition.await();
            }

            System.out.println("打印  ******空星******");
            hasValue = false;
            condition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyReentrantLockThread49Product extends Thread {
    private MyReentrantLockConditionService49 myReentrantLockConditionService49;
    public MyReentrantLockThread49Product (MyReentrantLockConditionService49 myReentrantLockConditionService49) {
        this.myReentrantLockConditionService49 = myReentrantLockConditionService49;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myReentrantLockConditionService49.set();
        }
    }
}

class MyReentrantLockThread49Customer extends Thread {
    private MyReentrantLockConditionService49 myReentrantLockConditionService49;
    public MyReentrantLockThread49Customer (MyReentrantLockConditionService49 myReentrantLockConditionService49) {
        this.myReentrantLockConditionService49 = myReentrantLockConditionService49;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myReentrantLockConditionService49.get();
        }
    }
}