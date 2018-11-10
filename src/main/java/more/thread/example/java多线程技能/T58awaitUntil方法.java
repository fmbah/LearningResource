package more.thread.example.java多线程技能;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T58awaitUntil方法
 * @Description
 * @Author root
 * @Date 18-11-8 下午7:57
 * @Version 1.0
 **/
public class T58awaitUntil方法 {

    public static void main (String args[]) {
        MyReentrantLockConditionService58 myReentrantLockConditionService58 = new MyReentrantLockConditionService58();
        myReentrantLockConditionService58.waitMethod();

        myReentrantLockConditionService58.notifyMethod();
    }

}

class MyReentrantLockConditionService58 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod () {
        try {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.SECOND, 10);

            lock.lock();

            long s = System.currentTimeMillis();
            System.out.println("wait begin time:" + s);

            condition.awaitUntil(instance.getTime());

            long e = System.currentTimeMillis();
            System.out.println("wait end time:" + System.currentTimeMillis());
            System.out.println("wait 耗时:" + (e - s) / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod () {
        try {
            lock.lock();
            long l = System.currentTimeMillis();
            System.out.println("notify begin time:" + l);
            condition.signalAll();
            long s = System.currentTimeMillis();
            System.out.println("notify end time:" + System.currentTimeMillis());
            System.out.println("wait 耗时:" + (l - s) / 1000);
        } finally {
            lock.unlock();
        }
    }
}