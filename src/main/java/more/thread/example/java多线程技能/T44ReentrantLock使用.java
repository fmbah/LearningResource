package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T44ReentrantLock使用
 * @Description
 * @Author root
 * @Date 18-11-7 上午9:47
 * @Version 1.0
 **/
public class T44ReentrantLock使用 {

    public static void main (String argsp[]) {
        MyReentrantLock44Service myReentrantLock44Service = new MyReentrantLock44Service();

        MyReentrantLock44Thread myReentrantLock44Thread_0 = new MyReentrantLock44Thread(myReentrantLock44Service);
        MyReentrantLock44Thread myReentrantLock44Thread_1 = new MyReentrantLock44Thread(myReentrantLock44Service);
        MyReentrantLock44Thread myReentrantLock44Thread_2 = new MyReentrantLock44Thread(myReentrantLock44Service);
        MyReentrantLock44Thread myReentrantLock44Thread_3 = new MyReentrantLock44Thread(myReentrantLock44Service);
        MyReentrantLock44Thread myReentrantLock44Thread_4 = new MyReentrantLock44Thread(myReentrantLock44Service);

        myReentrantLock44Thread_0.start();
        myReentrantLock44Thread_1.start();
//        myReentrantLock44Thread_2.start();
//        myReentrantLock44Thread_3.start();
//        myReentrantLock44Thread_4.start();
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-11-7 上午10:03
 * @Description: 两种锁的方式,
 */
class MyReentrantLock44Service {
    private Lock lock = new ReentrantLock();//持有了Lock lock = new ReentrantLock()对象锁,相当于是synchronized对象锁

    public void testMethod () {
        lock.lock();//获取锁
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("testMethod = " + Thread.currentThread().getName() + "======>" + i);
        }
        System.out.println();
        System.out.println();
        lock.unlock();//释放锁
    }

    synchronized
    public  void testMethodSynchronized () {
        for (int i = 0; i < 5; i++) {
            System.out.println("testMethodSynchronized = " + Thread.currentThread().getName() + "======>" + i);
        }
        System.out.println();
        System.out.println();
    }
}

class MyReentrantLock44Thread extends Thread {
    private MyReentrantLock44Service myReentrantLock44Service;
    public MyReentrantLock44Thread (MyReentrantLock44Service myReentrantLock44Service) {
        this.myReentrantLock44Service = myReentrantLock44Service;
    }

    @Override
    public void run() {
        super.run();
        myReentrantLock44Service.testMethod();
        myReentrantLock44Service.testMethodSynchronized();
    }
}

