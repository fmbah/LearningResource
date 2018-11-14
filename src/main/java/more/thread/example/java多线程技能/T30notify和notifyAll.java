package more.thread.example.java多线程技能;

/**
 * @ClassName T30notify随机通知一个线程
 * @Description
 * @Author root
 * @Date 18-10-31 上午10:00
 * @Version 1.0
 **/
public class T30notify和notifyAll {

    public static void main(String args[]) {
        Object object = new Object();
        MyThread30 myThread30 = new MyThread30(object);
        myThread30.start();
        MyThread30 myThread30_1 = new MyThread30(object);
        myThread30_1.start();
        MyThread30 myThread30_2 = new MyThread30(object);
        myThread30_2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyNotifyOneThread myNotifyOneThread = new MyNotifyOneThread(object);
        myNotifyOneThread.start();

//        MyNotifyAllThread myNotifyAllThread = new MyNotifyAllThread(object);
//        myNotifyAllThread.start();
    }

}

class MyService30 {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait, ThreadName:" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait, ThreadName:" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread30 extends Thread {
    private Object object;
    public MyThread30(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        MyService30 myService30 = new MyService30();
        myService30.testMethod(object);
    }
}

class MyThread30_1 extends Thread {
    private Object object;
    public MyThread30_1(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        MyService30 myService30 = new MyService30();
        myService30.testMethod(object);
    }
}

class MyThread30_2 extends Thread {
    private Object object;
    public MyThread30_2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        MyService30 myService30 = new MyService30();
        myService30.testMethod(object);
    }
}

class MyNotifyOneThread extends Thread {
    private Object object;
    public MyNotifyOneThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        synchronized (object) {
            object.notify();

//            object.notify();
//            object.notify();
//            object.notify();
//            object.notify();

        }
    }
}

class MyNotifyAllThread extends Thread {
    private Object object;
    public MyNotifyAllThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        synchronized (object) {
            object.notifyAll();
        }
    }
}