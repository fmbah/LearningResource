package more.thread.example.java多线程技能.LockCondition生产者与消费者;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName OPOC
 * @Description
 * @Author root
 * @Date 19-2-19 下午2:05
 * @Version 1.0
 **/
public class NPOC {

    public static void main(String[] args) {
//        PAC1 pac = new PAC1();
//        new Thread(){
//            @Override
//            public void run() {
//                while(true)
//                    pac.oc();
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                while(true)
//                    pac.op();
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                while(true)
//                    pac.op();
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                while(true)
//                    pac.op();
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                while(true)
//                    pac.op();
//            }
//        }.start();


        DeadTest deadTest = new DeadTest();
        new Thread(()->{
            deadTest.t1();
        }).start();
        new Thread(()->{
            deadTest.t2();
        }).start();
    }

}

class PAC1{
    private Lock lock = new ReentrantLock();
    private Condition conditionP = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private volatile List<Integer> data = new ArrayList<>();
    private volatile int i = 0;

    public void op() {
        try {
            lock.lock();
            if (!data.isEmpty()) {
                conditionP.await();
            }
            Thread.sleep(500);
            data.add(i++);
            conditionC.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void oc() {
        try {
            lock.lock();
            if (data.isEmpty()) {
                conditionC.await();
            }
            Thread.sleep(500);
            System.out.println(data.remove(0));
            conditionP.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


class DeadTest{

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void t1() {
        try {
            lock.lock();
            t2();
        } finally {
            lock.unlock();
        }
    }
    public void t2() {
        try {
            lock.lock();
            t1();
        } finally {
            lock.unlock();
        }
    }
}
