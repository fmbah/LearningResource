package more.ftf;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有3个独立的线程，一个只会输出A，一个只会输出L，一个只会输出I 。在三个线程同时启动的情况下，请用合理的方式让他们按顺序打印ALIALI
 *
 * 用多线程去处理"abc"，"def"，“ghi”这个三个字符串，让它们以"adg"，"beh"，“cfi”这种形式输出
 *
 *
 *
 */
public class F7 {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
//        Thread a = new Thread(new PrintALIThread("A", lock, 0));
//        Thread l = new Thread(new PrintALIThread("L", lock, 1));
//        Thread i = new Thread(new PrintALIThread("I", lock, 2));

        Thread a = new Thread(new PrintALIThread("abc", lock, 0));
        Thread l = new Thread(new PrintALIThread("def", lock, 1));
        Thread i = new Thread(new PrintALIThread("ghi", lock, 2));

        a.start();
        l.start();
        i.start();









//        try {
//            for (int c = 0; c < 100; c++) {
//
//                Thread a = new Thread(() -> {
//                    System.out.println("A");
//                    try {
//                        Thread.sleep(400);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//                a.start();
//                a.join();
//                Thread l = new Thread(() -> {
//                    System.out.println("L");
//                });
//                l.start();
//                l.join();
//                Thread i = new Thread(() -> {
//                    System.out.println("I");
//                });
//                i.start();
//                i.join();
//
//
//            }
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

}

class PrintALIThread implements Runnable {

    private String name;
    private Lock lock;
    private int flag;
    public static int count = 0;

    public PrintALIThread(String name, Lock lock, int flag) {
        this.name = name;
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (count % 3 == flag) {
//                System.out.println(name);
                System.out.println(name.charAt(count / 3));
                count++;
            }
            lock.unlock();
        }
    }
}
