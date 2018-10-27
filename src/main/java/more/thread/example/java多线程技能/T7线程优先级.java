package more.thread.example.java多线程技能;

/**
 * @ClassName T7线程优先级
 * @Description 线程的优先级有利于线程规划器确定下一次哪一个线程来执行任务
 *              线程的优先级最小为1,最大为10,超出范围报错
 *              线程的优先级可以继承,即b继承自a,则b的优先级和a的优先级相同
 *
 *              优先级具有规律性,总是让优先级高的线程先执行完
 *              优先级具有随机性,执行的顺序不能用优先级来衡量
 * @Author root
 * @Date 18-10-25 下午3:38
 * @Version 1.0
 **/
public class T7线程优先级 {

//    /**
//     *
//     * 功能描述: 演示线程优先级的设置具有继承性
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-25 下午3:49
//     */
//    public static void main (String args[]) {
//        System.out.println("main before 的优先级为:" + Thread.currentThread().getPriority());
////        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//        System.out.println("main after 的优先级为:" + Thread.currentThread().getPriority());
//        MyThread7 myThread7 = new MyThread7();
//        myThread7.start();
//    }


    /**
     *
     * 功能描述: 演示两个线程设置了优先级后,共同跑了4s后,比较数值的大小,
     *          按理说,时间越上测试越准确!线程规划器总是选择优先级高的线程进行跑,
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-25 下午4:09
     */
    public static void main (String args[]) {
        MyThread7_1 myThread7_1 = new MyThread7_1();
//        myThread7_1.setPriority(Thread.NORM_PRIORITY - 3);
        myThread7_1.setPriority(Thread.MIN_PRIORITY);
        myThread7_1.start();

        MyThread7_2 myThread7_2 = new MyThread7_2();
//        myThread7_2.setPriority(Thread.NORM_PRIORITY + 3);
        myThread7_2.setPriority(Thread.MAX_PRIORITY);
        myThread7_2.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThread7_1.stop();
        myThread7_2.stop();

        System.out.println("myThread7_1 count:" + myThread7_1.getCount());
        System.out.println("myThread7_2 count:" + myThread7_2.getCount());
    }
}


class MyThread7 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread7 的优先级为:" + this.getPriority());
    }
}


class MyThread7_1 extends Thread {
    private int count = 0;
    public int getCount() {
        return this.count;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            this.count++;
        }
    }
}
class MyThread7_2 extends Thread {
    private int count = 0;
    public int getCount() {
        return this.count;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            this.count++;
        }
    }
}