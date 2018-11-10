package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T59Condition顺序执行
 * @Description
 * @Author root
 * @Date 18-11-8 下午8:15
 * @Version 1.0
 **/
public class T59Condition顺序执行 {

    public static void main (String args[]) {

    }

}

class F59 {
    volatile public static int nextPrintWho = 1;
}

class Run59 {
    volatile private static int nextPrintWho = 1;
    final static private ReentrantLock lock = new ReentrantLock();
    final static private Condition condition_a = lock.newCondition();
    final static private Condition condition_b = lock.newCondition();
    final static private Condition condition_c = lock.newCondition();

    public static void main(String args[]) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    lock.lock();

                    while (nextPrintWho != 1) {
                        condition_a.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadA: " + i);
                    }
                    nextPrintWho = 2;
                    condition_b.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        condition_b.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadB: " + i);
                    }
                    nextPrintWho = 3;
                    condition_c.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread () {
            @Override
            public void run() {
                super.run();
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        condition_c.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("threadC: " + i);
                    }
                    nextPrintWho = 1;
                    condition_a.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread[] threadAs = new Thread[5];
        Thread[] threadBs = new Thread[5];
        Thread[] threadCs = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threadAs[i] = new Thread(threadA);
            threadBs[i] = new Thread(threadB);
            threadCs[i] = new Thread(threadC);
            threadAs[i].start();
            threadBs[i].start();
            threadCs[i].start();
        }
    }
}

