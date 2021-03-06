package more.thread.example.java多线程技能;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName T41ThreadLocal
 * @Description public static 修饰符 是使所有线程都共享同一个变量
 *              ThreadLocal 是一个盒子,用来存储每一个线程私有的变量
 *
 * @Author root
 * @Date 18-11-6 上午11:37
 * @Version 1.0
 **/
public class T41ThreadLocal {

    /**
     *
     * 功能描述: 演示ThreadLoacal类的方法
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-6 上午11:48
     */
    public static ThreadLocal t1 = new ThreadLocal();
//    public static void main(String args[]) {
//        if (t1.get() == null) {
//            System.out.println("从未放过值");
//            t1.set("我的值");
//        }
//        System.out.println(t1.get());
//        System.out.println(t1.get());
//
//        System.out.println("原ThreadLocalget()值为null,被覆盖了!" + Tools41.threadLocalExt.get());
//    }

    public static void main(String args[]) {
//        MyThread41A myThread41A = new MyThread41A();
//        myThread41A.start();
//        int i = 0;
//        for (; i < 100; i++) {
//            Tools41.threadLocal.set("main_" + i);
//            System.out.println("get main_" + i + ": " + Tools41.threadLocal.get());
//        }
//        MyThread41B myThread41B = new MyThread41B();
//        myThread41B.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("get main_" + i + ": " + Tools41.threadLocal.get());
//        System.out.println(Tools41.threadLocalExt.get());
//        Tools41.threadLocalExt.set("1");
//        Tools41.threadLocalExt.set("2");
        new Thread(()->{
            Tools41.threadLocalExt.set("3");
            System.out.println(Tools41.threadLocalExt.get());
        }).start();



        /**
         * A thread -> threadLocalMap
         * a1 threadLocal - > 自定义value值
         * a2 threadLocal -> 自定义value值
         * threadLocalMap -> 数组 entry 继承自 WeakReference，[<?> WeakReference, value]
         *
         *
         *
         *
         */
    }
}

class Tools41 {
    public static ThreadLocal threadLocal = new ThreadLocal();
    public static ThreadLocalExt threadLocalExt = new ThreadLocalExt();
}

class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "原空值被我覆盖了!!!";
    }
}

class MyThread41A extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            Tools41.threadLocal.set("MyThread41A_" + i);
            System.out.println("get MyThread41A_" + i + ": " + Tools41.threadLocal.get());
        }
    }
}
class MyThread41B extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 200; i < 300; i++) {
            Tools41.threadLocal.set("MyThread41B_" + i);
            System.out.println("get MyThread41B_" + i + ": " + Tools41.threadLocal.get());
        }
    }
}