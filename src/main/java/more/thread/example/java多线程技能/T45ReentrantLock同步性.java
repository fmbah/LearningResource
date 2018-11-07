package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T45ReentrantLock同步性
 * @Description 调用了lock.lock()方法的线程就拥有了'对象监视器',其它线程只有等待锁被再次释放才有机会争抢.效果上和synchronized一样.
 * @Author root
 * @Date 18-11-7 上午10:08
 * @Version 1.0
 **/
public class T45ReentrantLock同步性 {

    public static void main (String argsp[]) {
        MyReentrantLockService45 myReentrantLockService45 = new MyReentrantLockService45();

        MyReentrantLockThread45A myReentrantLockThread45A = new MyReentrantLockThread45A(myReentrantLockService45);
        myReentrantLockThread45A.setName("A");
        myReentrantLockThread45A.start();

        MyReentrantLockThread45AA myReentrantLockThread45AA = new MyReentrantLockThread45AA(myReentrantLockService45);
        myReentrantLockThread45AA.setName("AA");
        myReentrantLockThread45AA.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyReentrantLockThread45B myReentrantLockThread45B = new MyReentrantLockThread45B(myReentrantLockService45);
        myReentrantLockThread45B.setName("B");
        myReentrantLockThread45B.start();

        MyReentrantLockThread45BB myReentrantLockThread45BB = new MyReentrantLockThread45BB(myReentrantLockService45);
        myReentrantLockThread45BB.setName("BB");
        myReentrantLockThread45BB.start();
    }

}

class MyReentrantLockService45 {
    private Lock lock = new ReentrantLock();

    public void testMethodA() {
        try {
            lock.lock();
            System.out.println("testMethodA started, this threadName = " + Thread.currentThread().getName() + ", time =" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("testMethodA ended, this threadName = " + Thread.currentThread().getName() + ", time =" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void testMethodB() {
        try {
            lock.lock();
            System.out.println("testMethodB started, this threadName = " + Thread.currentThread().getName() + ", time =" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("testMethodB ended, this threadName = " + Thread.currentThread().getName() + ", time =" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyReentrantLockThread45A extends Thread {
    private MyReentrantLockService45 myReentrantLockService45;
    public MyReentrantLockThread45A(MyReentrantLockService45 myReentrantLockService45) {
        this.myReentrantLockService45 = myReentrantLockService45;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockService45.testMethodA();
    }
}

class MyReentrantLockThread45AA extends Thread {
    private MyReentrantLockService45 myReentrantLockService45;
    public MyReentrantLockThread45AA(MyReentrantLockService45 myReentrantLockService45) {
        this.myReentrantLockService45 = myReentrantLockService45;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockService45.testMethodA();
    }
}

class MyReentrantLockThread45B extends Thread {
    private MyReentrantLockService45 myReentrantLockService45;
    public MyReentrantLockThread45B(MyReentrantLockService45 myReentrantLockService45) {
        this.myReentrantLockService45 = myReentrantLockService45;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockService45.testMethodB();
    }
}

class MyReentrantLockThread45BB extends Thread {
    private MyReentrantLockService45 myReentrantLockService45;
    public MyReentrantLockThread45BB(MyReentrantLockService45 myReentrantLockService45) {
        this.myReentrantLockService45 = myReentrantLockService45;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLockService45.testMethodB();
    }
}