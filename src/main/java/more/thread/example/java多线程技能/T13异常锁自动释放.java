package more.thread.example.java多线程技能;

/**
 * @ClassName T13异常锁自动释放
 * @Description 当多个线程访问一个锁对象时,,其中一个线程占用了方法,其它线程等待的过程中,如果方法内部出现异常,则当前线程拥有的锁自动释放给其它线程使用!
 * @Author root
 * @Date 18-10-26 上午10:11
 * @Version 1.0
 **/
public class T13异常锁自动释放 {

    public static void main (String args[]) {
        final MyExceptionExample myExceptionExample = new MyExceptionExample();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                myExceptionExample.pringRandomNum();
            }
        };
        thread.setName("a");
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myExceptionExample.pringRandomNum();
    }

}

class MyExceptionExample {
    synchronized public void pringRandomNum () {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("我已经跑了哦");
            while (true) {
                if ((Math.random() + "").substring(0, 8).equals("0.123456")) {
                    System.out.println(Thread.currentThread().getName() + "===exception Time:" + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ", 我来了!");
        }
    }
}