package more.thread.example.java分享20190805;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    private static Object object = new Object();

    public static void main(String[] args) {

//        waitNotifyDemo();
        lockSupportDemo();
    }

    private static void waitNotifyDemo() {
        final Object object = new Object();
        for (int j = 0; j < 3; j++) {
            Thread thread = new Thread(() -> {
                int i = 0;
                for (; i < 10; i++) {
                }
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }


            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (object) {
            object.notifyAll();
        }

    }

    private static  void lockSupportDemo() {
        final Object object = new Object();
        Thread thread= new Thread(() -> {
            int i = 0;
            for (; i < 10; i++) {
            }
            LockSupport.park(object);
            System.out.println(i);
        });
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        LockSupport.unpark(thread);

    }





    // TODO: 相比wait、notify 面向线程而非对象，不会释放线程持有的锁，执行的顺序不强制要求

    //Todo: java.util.concurrent.FutureTask.get()
}
