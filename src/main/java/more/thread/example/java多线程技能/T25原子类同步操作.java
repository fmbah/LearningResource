package more.thread.example.java多线程技能;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName T25原子类同步操作
 * @Description
 * @Author root
 * @Date 18-10-29 上午11:22
 * @Version 1.0
 **/
public class T25原子类同步操作 {
    public static void main (String args[]) {
        MyAutoIntegerThread25 myAutoIntegerThread25 = new MyAutoIntegerThread25();

//        Thread thread_1 = new Thread(myAutoIntegerThread25);
//        Thread thread_2 = new Thread(myAutoIntegerThread25);
//        Thread thread_3 = new Thread(myAutoIntegerThread25);
//        Thread thread_4 = new Thread(myAutoIntegerThread25);
//        Thread thread_5 = new Thread(myAutoIntegerThread25);
//        thread_1.setName("1");
//        thread_1.start();
//        thread_2.start();
//        thread_3.start();
//        thread_4.start();
//        thread_5.start();
        for (int i = 0; i < 80; i++) {
            new Thread(myAutoIntegerThread25).start();
        }
    }
}

class MyAutoIntegerThread25 extends Thread {
    private AtomicInteger count = new AtomicInteger(0);
//    private int count = 0;

    @Override
    public void run() {
        super.run();
//        if (Thread.currentThread().getName().equals("1")) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println(Thread.currentThread().getName() + ", 这次的count值是多少?????" + count);
//        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
//            count = count + 1;
//        }
//        System.out.println(Thread.currentThread().getName() + "====>" + count.get());
//        System.out.println(Thread.currentThread().getName() + "====>" + count);
    }
}