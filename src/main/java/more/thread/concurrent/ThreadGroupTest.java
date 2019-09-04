package more.thread.concurrent;

public class ThreadGroupTest {

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("ThreadGroup");
        System.out.println(group.getName());
        System.out.println(group.getParent().getName());
        System.out.println(group.getParent().getParent().getName());

        Thread t1 = new Thread(group, new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start");
                while(!Thread.currentThread().isInterrupted()) {

                }
                System.out.println("t1 end");
            }
        }, "t1");

        Thread t2 = new Thread(group, new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start");
                while(!Thread.currentThread().isInterrupted()) {

                }
                System.out.println("t2 end");
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        group.interrupt();
    }

}
