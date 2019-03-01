package more.thread.example.java多线程技能.同步锁;

/**
 * @ClassName SynchronizedThreadTest
 * @Description
 * @Author root
 * @Date 19-2-15 下午2:24
 * @Version 1.0
 **/
public class SynchronizedThreadTest {

    public static void main(String[] args) {
        final SynchronizedClass s = new SynchronizedClass();

        Thread thread1 = new Thread(() -> {
            s.print1();
        });
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.start();

        new Thread(() -> {
            s.print2();
        }).start();

        Thread thread = new Thread(() -> {
            s.print3();
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }


}


class SynchronizedClass{
    protected synchronized void print1() {
        System.out.println("1");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected synchronized void print2() {
        System.out.println("2");
    }

    protected void print3() {
        System.out.println("3");
    }
}