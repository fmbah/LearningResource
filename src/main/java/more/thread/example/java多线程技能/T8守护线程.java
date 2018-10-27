package more.thread.example.java多线程技能;

/**
 * @ClassName T8守护线程
 * @Description 守护线程如:GC守护线程
 *              非守护线程没有了,守护线程也就自动销毁了
 * @Author root
 * @Date 18-10-25 下午4:14
 * @Version 1.0
 **/
public class T8守护线程 {
    public static void main (String args[]) {
        MyThread8 myThread8 = new MyThread8();
        myThread8.setDaemon(true);//将此线程设置为守护线程
        myThread8.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //main线程停止了(非守护线程停止了),jvm中将查看是否还有其它非守护线程运行,如果没有了,则jvm中的守护线程也自动销毁
        System.out.println("我离开了,myThread8对象也不再打印了,也就是停止了!");
    }
}

class MyThread8 extends Thread {
    private int count = 0;
    public int getCount() {
        return this.count;
    }
    @Override
    public void run() {
        super.run();
        while (true) {
            System.out.println(this.count++);
        }
    }
}
