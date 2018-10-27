package more.thread.example.java多线程技能;

/**
 * @ClassName T20多线程的死锁线程间互相等待
 * @Description 演示下线程间由于加锁后,又想互相调用对方线程已获取方法锁的方法,这样导致,双方都在等待对方释放锁,====>死锁
 *              后续展示下通过jdk的方式查找程序是否有死锁
 *                      1. jps 查找到当前线程id
 *                      2. jstack pid查看线程的运行情况====>Found 1 deadlock   来喽
 *
 * @Author root
 * @Date 18-10-27 上午10:54
 * @Version 1.0
 **/
public class T20多线程的死锁线程间互相等待 {

    public static void main (String args[]) {
        DeadThread20 deadThread20 = new DeadThread20();
        Thread deadThread20_a = new Thread(deadThread20);
        deadThread20.setFlag("a");
        deadThread20_a.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread deadThread20_b = new Thread(deadThread20);
        deadThread20.setFlag("b");
        deadThread20_b.start();
    }

}

class DeadThread20 extends Thread{
    private String flag;
    public void setFlag(String flag) {
        this.flag = flag;
    }
    Object o1 = new Object();
    Object o2 = new Object();

    @Override
    public void run() {
        super.run();
        if (flag.equals("a")) {
            synchronized (o1) {
                System.out.println("a --->  o1");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("a --->  o2");
                }
            }
        }
        if (flag.equals("b")) {
            synchronized (o2) {
                System.out.println("b --->  o2");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("b --->  o1");
                }
            }
        }
    }
}
