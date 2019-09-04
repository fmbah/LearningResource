package more.thread.concurrent;

public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedThread synchronizedThread = new SynchronizedThread();
//        Thread t1 = new Thread(synchronizedThread);
//        Thread t2 = new Thread(synchronizedThread);
//        Thread t3 = new Thread(synchronizedThread);

        for (int i = 0; i< 30; i++) {
            num = 0;
//            Thread t1 = new Thread(synchronizedThread);
//            Thread t2 = new Thread(synchronizedThread);
//            Thread t3 = new Thread(synchronizedThread);

            Thread t1 = new Thread(new SynchronizedThread());
            Thread t2 = new Thread(new SynchronizedThread());
            Thread t3 = new Thread(new SynchronizedThread());

            t1.start();
            t2.start();
            t3.start();


            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(num);
        }
    }


    private static int num = 0;
    static Object obj = new Object();

    /**
     * 修饰实例方法，作用于当前实例，进入同步代码前需要先获取实例的锁
     *
     * 修饰静态方法，作用于类的Class对象，进入修饰的静态方法前需要先获取类的Class对象的锁
     *
     * 修饰代码块，需要指定加锁对象(记做lockobj)，在进入同步代码块前需要先获取lockobj的锁
     */


    public static void countNum() {
        for (int i = 0; i < 10000; i++) {
            num ++;
        }
    }
    public static class SynchronizedThread implements Runnable {

        @Override
        public void run() {
//            countNum();
            synchronized (obj) {
                for (int i = 0; i < 100; i++) {
                    num ++;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }

        }
    }
}
