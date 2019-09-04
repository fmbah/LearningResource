package more.thread.concurrent;

import javax.swing.plaf.TableHeaderUI;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonThread());
//        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束了.....");
    }

    public static class DaemonThread implements Runnable {

        @Override
        public void run() {
            System.out.println("子线程是否是守护线程？" + Thread.currentThread().isDaemon());
            while (true);
        }
    }

}
