package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T50ReentrantLockCondition生产者消费者多对多
 * @Description
 * @Author root
 * @Date 18-11-7 下午7:18
 * @Version 1.0
 **/
public class T50ReentrantLockCondition生产者消费者多对多 {

    /**
     *
     * 功能描述: 当唤醒方法 signal()的时候,会出现假死的情况,
     *          改为signalAll()方法的时候,则可以正常运行,但是会出现两个相同的输出,因为唤醒的时候,是唤醒全部(可能是生产者,也可能是消费者,当唤醒了同类型的时候,则会打印两个相同的输出)
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-7 下午7:27
     */
    public static void main (String args[]) {
        MyReentrantLockConditionService50 myReentrantLockConditionService50 = new MyReentrantLockConditionService50();

        MyReentrantLockThread50Product[] myReentrantLockThread50Products = new MyReentrantLockThread50Product[10];
        MyReentrantLockThread50Customer[] myReentrantLockThread50Customers = new MyReentrantLockThread50Customer[10];

        for (int i = 0; i < 10; i++) {
            myReentrantLockThread50Products[i] = new MyReentrantLockThread50Product(myReentrantLockConditionService50);
            myReentrantLockThread50Customers[i] = new MyReentrantLockThread50Customer(myReentrantLockConditionService50);

            myReentrantLockThread50Products[i].start();
            myReentrantLockThread50Customers[i].start();
        }
    }

}

class MyReentrantLockConditionService50 {
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
            condition.signalAll();
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
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyReentrantLockThread50Product extends Thread {
    private MyReentrantLockConditionService50 myReentrantLockConditionService50;
    public MyReentrantLockThread50Product (MyReentrantLockConditionService50 myReentrantLockConditionService50) {
        this.myReentrantLockConditionService50 = myReentrantLockConditionService50;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myReentrantLockConditionService50.set();
        }
    }
}

class MyReentrantLockThread50Customer extends Thread {
    private MyReentrantLockConditionService50 myReentrantLockConditionService50;
    public MyReentrantLockThread50Customer (MyReentrantLockConditionService50 myReentrantLockConditionService50) {
        this.myReentrantLockConditionService50 = myReentrantLockConditionService50;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myReentrantLockConditionService50.get();
        }
    }
}