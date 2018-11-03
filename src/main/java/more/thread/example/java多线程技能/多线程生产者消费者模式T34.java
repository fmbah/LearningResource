package more.thread.example.java多线程技能;

import io.netty.util.internal.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName 多线程生产者消费者模式T34
 * @Description
 * @Author root
 * @Date 18-11-2 下午4:18
 * @Version 1.0
 **/
public class 多线程生产者消费者模式T34 {

    /**
     *
     * 功能描述: 生产者生成数组,当数组数量超过4个,不再生产
     *          消费者消费数组,当数组超过4个开始消费,小于4个即暂停消费
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-2 下午4:20
     */
    public static void main (String args[]) {
        String clock = new String("clock");
        String plock = new String("plock");
        P34 p34 = new P34(plock);
        C34 c34 = new C34(plock);
        ThreadP34[] threadP34s = new ThreadP34[10];
        ThreadC34[] threadC34s = new ThreadC34[10];
        for (int i = 0; i < 3; i++) {
            threadP34s[i] = new ThreadP34(p34);
            threadC34s[i] = new ThreadC34(c34);
            threadP34s[i].setName("threadP34s[" + i + "]");
            threadC34s[i].setName("threadC34s[" + i + "]");
            threadP34s[i].start();
            threadC34s[i].start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (plock) {
            plock.notify();
        }
    }

}

class Array34Object {
    public static String value = "";
    public static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
    public static ArrayList<Integer> list = new ArrayList<Integer>();
}

class P34 {
    private String lock;
    public P34(String lock) {
        this.lock = lock;
    }

    public void addOrders() {
        try {
            synchronized (lock) {
//                while (!Array34Object.value.equals("")) {
//                while (!Array34Object.concurrentHashMap.isEmpty()) {
//                while (!Array34Object.list.isEmpty()) {
                while (Array34Object.list.size() == 1) {
                    System.out.println("生产=========================休眠");
                    lock.wait();
                }
                System.out.println("生产成功");
//                Array34Object.value = "1111";
//                Array34Object.concurrentHashMap.put("a", "vb");
                Array34Object.list.add(1);
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class C34 {
    private String lock;
    public C34(String lock) {
        this.lock = lock;
    }

    public void reduceOrders() {
        try {
            synchronized (lock) {
//                while (Array34Object.value.equals("")) {
//                while (Array34Object.concurrentHashMap.isEmpty()) {
//                while (Array34Object.list.isEmpty()) {
                while (Array34Object.list.size() == 0) {
                    System.out.println("消费=========================休眠");
                    lock.wait();
                }
                System.out.println("消费成功");
//                Array34Object.value = "";
//                Array34Object.concurrentHashMap.clear();
//                Array34Object.list.clear();
                Array34Object.list.remove(0);
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadP34 extends Thread{
    private P34 p34;
    public ThreadP34 (P34 p34) {
        this.p34 = p34;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            p34.addOrders();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadC34 extends Thread {
    private C34 c34;
    public ThreadC34 (C34 c34) {
        this.c34 = c34;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            c34.reduceOrders();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
