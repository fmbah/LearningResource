package more.thread.example.java多线程技能;

/**
 * @ClassName T16Synchronized同步代码块
 * @Description 同步代码块内部的代码在多线程的情况下,是有顺序的执行的,注意:都是对于多个线程共享一个对象的情况
 *
 *              针对同一个对象里的方法,线程获得了a方法内的同步代码块锁,则获得了对象监控器的锁,也就是其它线程不能正常获得b方法或a方法的锁直到前一个线程释放锁
 * @Author root
 * @Date 18-10-26 上午11:40
 * @Version 1.0
 **/
public class T16Synchronized同步代码块 {

//    public static void main (String args[]) {
//        MyTask16 myTask16 = new MyTask16();
//        MyThread16_1 myThread16_1 = new MyThread16_1(myTask16);
//        MyThread16_2 myThread16_2 = new MyThread16_2(myTask16);
//        myThread16_1.start();
//        myThread16_2.start();
////        MyTask16 myTask16 = new MyTask16();
////        MyTask16 myTask16_1 = new MyTask16();
////        MyThread16_1 myThread16_1 = new MyThread16_1(myTask16);
////        MyThread16_2 myThread16_2 = new MyThread16_2(myTask16_1);
////        myThread16_1.start();
////        myThread16_2.start();
//    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-26 下午7:27
     */
    public static void main (String args[]) {
        MyExample16 myExample16 = new MyExample16();
        MyThread16_3 myThread16_3 = new MyThread16_3(myExample16);
        MyThread16_4 myThread16_4 = new MyThread16_4(myExample16);
        myThread16_3.start();
        myExample16.c();//此段代码的加入,证明了同步代码块锁的是当前对象中的所有锁对象,不会对当前对象中非锁对象进行控制
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread16_4.start();
    }
}

class MyTask16 {
    public void doLongTimeTask() {
        for (int i = 0; i< 100; i++) {
            System.out.println("no synchronized threadName = " + Thread.currentThread().getName() + ", i=" + (i + 1));
        }

        System.out.println("");

        synchronized (this) {
            for (int i = 0; i< 100; i++) {
                System.out.println("synchronized threadName = " + Thread.currentThread().getName() + ", i=" + (i + 1));
            }
        }
    }
}

class MyThread16_1 extends Thread {
    private MyTask16 myTask16;

    public MyThread16_1 (MyTask16 myTask16) {
        this.myTask16 = myTask16;
    }

    @Override
    public void run() {
        super.run();
        myTask16.doLongTimeTask();
    }
}

class MyThread16_2 extends Thread {
    private MyTask16 myTask16;

    public MyThread16_2 (MyTask16 myTask16) {
        this.myTask16 = myTask16;
    }

    @Override
    public void run() {
        super.run();
        myTask16.doLongTimeTask();
    }
}

class MyExample16 {
     public void a () {
        synchronized (this) {
            System.out.println("MyExample16 a starting........");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyExample16 a ended........");
        }
     }

     synchronized public void b () {
         System.out.println("MyExample16 b starting........");
         System.out.println("MyExample16 b ended........");
     }

     public void c () {
         System.out.println("MyExample16 c starting........");
         System.out.println("MyExample16 c ended........");
     }
}

class MyThread16_3 extends Thread {
    private MyExample16 myExample16;

    public MyThread16_3 (MyExample16 myExample16) {
        this.myExample16 = myExample16;
    }

    @Override
    public void run() {
        super.run();
        myExample16.a();
    }
}

class MyThread16_4 extends Thread {
    private MyExample16 myExample16;

    public MyThread16_4 (MyExample16 myExample16) {
        this.myExample16 = myExample16;
    }

    @Override
    public void run() {
        super.run();
        myExample16.b();
    }
}