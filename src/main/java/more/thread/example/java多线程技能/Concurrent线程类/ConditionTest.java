package more.thread.example.java多线程技能.Concurrent线程类;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionTest
 * @Description
 * @Author root
 * @Date 19-2-18 下午3:26
 * @Version 1.0
 **/
public class ConditionTest {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方
    private Condition condition = lock.newCondition();
    public static void main(String[] args)  {
        final ConditionTest test = new ConditionTest();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<50000000;i++) {
                arrayList.add(i);
            }
//            condition.await(3, TimeUnit.SECONDS);

        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}

