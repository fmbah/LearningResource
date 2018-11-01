package more.thread.example.java多线程技能;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName 多线程生产者消费者模式T33
 * @Description
 * @Author root
 * @Date 18-10-31 上午11:30
 * @Version 1.0
 **/
public class 多线程生产者消费者模式T33 {

    public static void main(String args[]) {

    }
}

//生产者消费者对应的数据
class ValueObjectT33 {
//    public static List<String> list = Arrays.asList(new String[]{});
    public static String value = "";
}

class PT33 {
    private Object lock;
    public PT33(Object lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObjectT33.value.equals("")) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " Wait了, ★");
                    lock.wait();
                }
                System.out.println("生产者" + Thread.currentThread().getName() + "RUNNABLE了");
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                ValueObjectT33.value = value;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CT33 {
    private Object lock;
    public CT33 (Object lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObjectT33.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " Wait了, ★");
                    lock.wait();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + "RUNNABLE了");
                ValueObjectT33.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreadP33 extends Thread {
    private PT33 p;
    public MyThreadP33(PT33 p) {
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            p.setValue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThreadC33 extends Thread {
    private CT33 c;
    public MyThreadC33(CT33 c) {
        this.c = c;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            c.getValue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}