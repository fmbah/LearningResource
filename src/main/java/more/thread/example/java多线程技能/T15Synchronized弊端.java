package more.thread.example.java多线程技能;

/**
 * @ClassName T15Synchronized弊端
 * @Description 因为锁的性质是允许一个线程占用,如果一个方法占用的时间比较久,那么就会出现多个线程等待一个锁的情况!
 * @Author root
 * @Date 18-10-26 上午11:16
 * @Version 1.0
 **/
public class T15Synchronized弊端 {

    public static void main (String args[]) {
        Task15 task15 = new Task15();
        MyThread15_1 myThread15_1 = new MyThread15_1(task15);
        MyThread15_2 myThread15_2 = new MyThread15_2(task15);

        myThread15_1.start();
        myThread15_2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = CommonUtils.beginTime1;
        if (beginTime > CommonUtils.beginTime2) {
            beginTime = CommonUtils.beginTime2;
        }
        long endTime = CommonUtils.endTime2;
        if (endTime < CommonUtils.endTime1) {
            endTime = CommonUtils.endTime1;
        }
        System.out.println("耗时:" + (endTime-beginTime)/1000 + "s");
    }

}

class Task15 {
    private String getData1;
    private String getData2;

    //synchronized 加在方法上,共耗时6s
    public void doLongTimeTask() {
//        synchronized (this) {//加在此处,共耗时6s
            System.out.println("begin Task");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String privateGetData1 = "长时间处理任务后返回的值 1 , threadName=" + Thread.currentThread().getName();
            String privateGetData2 = "长时间处理任务后返回的值 2 , threadName=" + Thread.currentThread().getName();
            synchronized (this) {//加在此处,耗时3s,提高了许多,
                getData1 = privateGetData1;
                getData2 = privateGetData2;
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end Task");
//        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-26 上午11:42
 * @Description: 内存中共享一个变量
 */
class CommonUtils {
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

class MyThread15_1 extends Thread {
    private Task15 task15;
    public MyThread15_1(Task15 task15) {
        this.task15 = task15;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task15.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}

class MyThread15_2 extends Thread {
    private Task15 task15;
    public MyThread15_2(Task15 task15) {
        this.task15 = task15;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task15.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}
