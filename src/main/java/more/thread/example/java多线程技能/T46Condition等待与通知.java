package more.thread.example.java多线程技能;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T46Condition等待与通知
 * @Description wait() notify() notifyAll()方法 唤醒方法是jvm中随机唤醒其中一个线程,一个synchronized中只能对应一个线程的等待通知操作,这样效率很低
 *              Condition和ReentrantLock结合使用,可做到选择性通知,这是非常重要的,而且是Condition默认拥有的功能
 *
 * @Author root
 * @Date 18-11-7 上午10:24
 * @Version 1.0
 **/
public class T46Condition等待与通知 {

    public static void main (String argsp[]) {
        MyReentrantLockConditionService myReentrantLockConditionService = new MyReentrantLockConditionService();

        MyReentrantConditionThreadA myReentrantConditionThreadA = new MyReentrantConditionThreadA(myReentrantLockConditionService);

        myReentrantConditionThreadA.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myReentrantLockConditionService.single();
    }

}

class MyReentrantLockConditionService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await () {
        try {

            //未加 lock.lock()的时候,会出现下面的异常, 原因:为获得当前对象锁
            lock.lock();
//        Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
//        at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
//        at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
//        at java.util.concurrent.locks.AbstractQueuedSynchronizer.fullyRelease(AbstractQueuedSynchronizer.java:1723)
//        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2036)
//        at more.thread.example.java多线程技能.MyReentrantLockConditionService.await(T46Condition等待与通知.java:34)
//        at more.thread.example.java多线程技能.MyReentrantConditionThreadA.run(T46Condition等待与通知.java:50)
            System.out.println("等待前,time = " + System.currentTimeMillis());
//            condition.await(3, TimeUnit.SECONDS);
            condition.await();
            System.out.println("等待完成,time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("锁释放");
        }
    }

    public void single () {
        try {
            lock.lock();
            System.out.println("single time = " + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class MyReentrantConditionThreadA extends Thread {
    private MyReentrantLockConditionService myReentrantLockConditionService;
    public MyReentrantConditionThreadA (MyReentrantLockConditionService myReentrantLockConditionService) {
        this.myReentrantLockConditionService = myReentrantLockConditionService;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockConditionService.await();
    }
}

