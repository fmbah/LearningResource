package more.thread.example.java多线程技能;

/**
 * @ClassName T2
 * @Description 实例变量与线程安全
 * @Author root
 * @Date 18-10-23 下午5:40
 * @Version 1.0
 **/
public class T2 {

    public static void main (String args[]) {
        MyThread threadA = new MyThread("A");
        MyThread threadB = new MyThread("B");
        MyThread threadC = new MyThread("C");

        threadA.start();
        threadB.start();
        threadC.start();

        //记录下MyThread,控制台
//        由A计算, count = 4
//        由A计算, count = 3
//        由A计算, count = 2
//        由A计算, count = 1
//        由A计算, count = 0
//        由B计算, count = 4
//        由B计算, count = 3
//        由B计算, count = 2
//        由B计算, count = 1
//        由B计算, count = 0
//        由C计算, count = 4
//        由C计算, count = 3
//        由C计算, count = 2
//        由C计算, count = 1
//        由C计算, count = 0


        MyThread1 myThread1 = new MyThread1();
        Thread a = new Thread(myThread1, "A");
        Thread b = new Thread(myThread1, "B");
        Thread c = new Thread(myThread1, "C");
        Thread d = new Thread(myThread1, "D");

        a.start();
        b.start();
        c.start();
        d.start();

        //MyThread1,控制台
//        由Thread-0计算, count = 4
//        由Thread-0计算, count = 2
//        由Thread-0计算, count = 2
//        由Thread-0计算, count = 1

    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-23 下午5:48
 * @Description:  线程安全的情况,未共享一个成员变量,也就是每个线程都拥有一个对象,操作的也是其线程拥有的对象,不存在线程不安全的情况
 */
class MyThread extends Thread {
    private int count = 5;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();

        while (count > 0) {
            count--;
            System.out.println("由" + this.getName() + "计算, count = " + count);
        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-23 下午5:55
 * @Description:  线程非安全的情况,此处共享了一个对象,然后分别新创建了4个线程来对同一个对象进行操作,
 *                例如:通常一个对象的减法的操作流程:1获取对象 2自减 3赋值；所以当多个线程同时访问一个对象的时候,会出现非线程安全问题
 *                ,目前简单的解决方式:在同步代码块上加上 synchronized 同步锁关键字,用来限制一个方法或一个类只能有一个线程操作(大部分情况,会有极少情况出现同时有多个线程获得了锁)
 */
class MyThread1 extends Thread {
    private int count = 5;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由" + this.getName() + "计算, count = " + count);
    }
}
