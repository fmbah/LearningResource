package more.thread.example.java多线程技能.暂停线程;

/**
 * @ClassName SuspendThreadTest
 * @Description
 * @Author root
 * @Date 19-2-15 下午1:50
 * @Version 1.0
 **/
public class SuspendThreadTest {
    public static void main(String[] args) {
//        SuspendTest s = new SuspendTest();
//        s.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(s.i);
//        s.suspend();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(s.i);
//        s.resume();

        YieldTest y = new YieldTest();
        long l1 = System.currentTimeMillis();
        y.start();
        System.out.println("yield=>" + (System.currentTimeMillis() - l1));
        UnYieldTest u = new UnYieldTest();
        l1 = System.currentTimeMillis();
        u.start();
        System.out.println("unyield=>" + (System.currentTimeMillis() - l1));
    }
}
class SuspendTest extends Thread {
    int i = 0;
    @Override
    public void run() {
        for (;;i++) {
            try {
                Thread.sleep(100);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class YieldTest extends Thread {
    int i = 0;

    @Override
    public void run() {
        for (;i<100000;i++) {
            Thread.yield();
        }
    }
}

class UnYieldTest extends Thread {
    int i = 0;

    @Override
    public void run() {
        for (;i<100000;i++) {
        }
    }
}