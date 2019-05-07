package more.thread.example.java多线程技能;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();




    public static void main(String[] args) {

        LockConditionDemo demo = new LockConditionDemo();



        new Thread(()->{
//            demo.mA();


            demo.mB();
        }).start();

        new Thread(()->{
//            demo.mB();


            demo.mA();
        }).start();
    }

    public void mA() {

        try {
            lock.lock();


            System.out.println("mA");


            Thread.sleep(0);


            /**
             * 此处会自动释放对象锁，所以你会看到mA mB一起输出
             */
            condition.await(0, TimeUnit.MILLISECONDS);




        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }




    }


    public void mB() {
        lock.lock();


        System.out.println("mB");
        try {
            Thread.sleep(500);

            condition.await(50, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





        lock.unlock();
    }





}
