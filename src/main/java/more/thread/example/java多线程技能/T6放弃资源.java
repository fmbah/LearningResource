package more.thread.example.java多线程技能;

/**
 * @ClassName T6放弃资源
 * @Description Thread.yield();放弃当前的CPU资源,将它让给其它任务去占用CPU执行时间,但放弃的时间不确定,有可能刚刚放弃,马上又获得了CPU时间片
 * @Author root
 * @Date 18-10-25 上午10:19
 * @Version 1.0
 **/
public class T6放弃资源 {

    public static void main (String args[]) {
        MyThread6 myThread6 = new MyThread6();
        myThread6.start();
    }

}

class MyThread6 extends Thread {

    @Override
    public void run() {
        super.run();

        long s = System.currentTimeMillis();

        int count = 0;
        for (int i = 0; i < 500000; i++) {
            Thread.yield();
            count = count + i;
        }

        long e = System.currentTimeMillis();

        System.out.println("耗时:" + (e-s) + "毫秒");
    }
}