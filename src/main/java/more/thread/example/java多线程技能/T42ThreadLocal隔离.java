package more.thread.example.java多线程技能;

import java.util.Date;

/**
 * @ClassName T42ThreadLocal隔离
 * @Description
 * @Author root
 * @Date 18-11-6 下午2:15
 * @Version 1.0
 **/
public class T42ThreadLocal隔离 {
    public static void main(String args[]) {
        MyThread42 myThread42 = new MyThread42();
        myThread42.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread42_1 myThread42_1 = new MyThread42_1();
        myThread42_1.start();
    }
}

class Tools42 {
    public static ThreadLocal<Date> local = new ThreadLocal<Date>();
}

class MyThread42 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 20; i++) {
            if (Tools42.local.get() == null) {
                Tools42.local.set(new Date());
            }
            System.out.println("MyThread42 "+ i +": " + Tools42.local.get().getTime());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread42_1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 20; i++) {
            if (Tools42.local.get() == null) {
                Tools42.local.set(new Date());
            }
            System.out.println("MyThread42_1 "+ i +": " + Tools42.local.get().getTime());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

