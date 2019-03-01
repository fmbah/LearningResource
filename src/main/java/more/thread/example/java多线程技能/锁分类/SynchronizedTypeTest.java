package more.thread.example.java多线程技能.锁分类;

/**
 * @ClassName SynchronizedTypeTest
 * @Description
 * @Author root
 * @Date 19-2-15 下午2:48
 * @Version 1.0
 **/
public class SynchronizedTypeTest {

    public static void main(String[] args) {
        S s = new S();
        new Thread(()->{
            s.print3();
        }).start();
        new Thread(()->{
            s.print1();
        }).start();

    }

}

class S {
    public synchronized void print() {//方法锁
        System.out.println("0");

    }

    public void print1() {//方法锁
        synchronized (this) {
            System.out.println("1");
        }
    }

    public void print2() {
        synchronized (this.getClass()) {//类锁
            System.out.println("2");
        }
    }

    public synchronized static void print3() {
        System.out.println("3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


