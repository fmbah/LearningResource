package more.thread.example.java多线程技能;

/**
 * @ClassName T31wait指定时间后自动唤醒
 * @Description wait(3000):3s内无其它线程唤醒,则自动唤醒并运行
 * @Author root
 * @Date 18-10-31 上午10:24
 * @Version 1.0
 **/
public class T31wait指定时间后自动唤醒 {

    public static void main(String args[]) {
        new Thread(new MyThread31()).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(MyThread31.myThread32).start();
    }
}

class MyThread31 implements Runnable {
    static private Object lock = new Object();
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("begin ThreadName:" + Thread.currentThread().getName());
                lock.wait(5000);
                System.out.println("end ThreadName:" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public Runnable myThread32 = new Runnable() {
        public void run() {
            synchronized (lock) {
                lock.notify();
            }
        }
    };
}
