package more.thread.example.java多线程技能.LockCondition生产者与消费者;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class OPOC {

    public static void main(String[] args) {
        PAC pac = new PAC();
        new Thread(){
            @Override
            public void run() {
                while(true)
                    pac.oc();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true)
                    pac.op();
            }
        }.start();

    }

}

class PAC{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile List<Integer> data = new ArrayList<>();
    private volatile int i = 0;

    public void op() {
        try {
            lock.lock();
            if (!data.isEmpty()) {
                condition.await();
            }
            Thread.sleep(500);
            data.add(i++);
            condition.signalAll();
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
                condition.await();
            }
            Thread.sleep(500);
            System.out.println(data.remove(0));
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
