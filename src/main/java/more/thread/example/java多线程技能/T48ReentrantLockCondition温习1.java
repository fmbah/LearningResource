package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T48ReentrantLockCondition温习1
 * @Description
 * @Author root
 * @Date 18-11-15 上午10:55
 * @Version 1.0
 **/
public class T48ReentrantLockCondition温习1 {

    private static class MyService48 {
        private Lock lock = new ReentrantLock();
        private Condition conditionA = lock.newCondition();
        private Condition getConditionB = lock.newCondition();

        public void methodA () {
            try {
                lock.lock();
                System.out.println("methodA await() start....");
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void methodA_Signal () {
            try {
                lock.lock();
                System.out.println("methodA signal() start....");
                conditionA.signal();
            } finally {
                lock.unlock();
            }
        }

        public void methodB () {
            try {
                lock.lock();
                System.out.println("methodB await() start....");
                getConditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void methodB_Signal () {
            try {
                lock.lock();
                System.out.println("methodB signal() start....");
                getConditionB.signal();
            } finally {
                lock.unlock();
            }
        }
    }
    private static class MyReentrantLockConditionThread48 extends Thread{
        private MyService48 myService48;
        public MyReentrantLockConditionThread48 (MyService48 myService48) {
            this.myService48 = myService48;
        }
        @Override
        public void run() {//下面这种方式,执行的时候,是有问题的, 它是在一个线程里的执行关系,前一个方法阻塞了,后一个方法不会被执行到
            super.run();
            myService48.methodA();
            myService48.methodB();
        }
    }
    private static class MyReentrantLockConditionThread48_1 extends Thread{
        private MyService48 myService48;
        public MyReentrantLockConditionThread48_1 (MyService48 myService48) {
            this.myService48 = myService48;
        }
        @Override
        public void run() {
            super.run();
            myService48.methodA_Signal();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myService48.methodB_Signal();
        }
    }

    public static void main (String argsp[]) {
        MyService48 myService48 = new MyService48();

        MyReentrantLockConditionThread48 myReentrantLockConditionThread48 = new MyReentrantLockConditionThread48(myService48);
        MyReentrantLockConditionThread48_1 myReentrantLockConditionThread48_1 = new MyReentrantLockConditionThread48_1(myService48);

        myReentrantLockConditionThread48.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myReentrantLockConditionThread48_1.start();
    }
}
