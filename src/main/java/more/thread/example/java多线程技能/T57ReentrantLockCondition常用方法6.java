package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T57ReentrantLockCondition常用方法6
 * @Description
 * @Author root
 * @Date 18-11-8 下午4:34
 * @Version 1.0
 **/
public class T57ReentrantLockCondition常用方法6 {

    public static void main (String args[]) {
        MyReentrantLockConditionService57 myReentrantLockConditionService57 = new MyReentrantLockConditionService57();
        MyReentrantLockConditionThread57 myReentrantLockConditionThread57 = new MyReentrantLockConditionThread57(myReentrantLockConditionService57);
        myReentrantLockConditionThread57.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionThread57.interrupt();//和Condition.await()方法冲突,会报错
    }

}

class MyReentrantLockConditionService57 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void Service() {
        try {
            System.out.println("begin ....");
            lock.lock();
//            condition.await();
            condition.awaitUninterruptibly();
            System.out.println("end ....");
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        finally {
//            lock.unlock();
        }
    }
}

class MyReentrantLockConditionThread57 extends Thread {
    private MyReentrantLockConditionService57 myReentrantLockConditionService57;
    public MyReentrantLockConditionThread57 (MyReentrantLockConditionService57 myReentrantLockConditionService57) {
        this.myReentrantLockConditionService57 = myReentrantLockConditionService57;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService57.Service();
    }
}