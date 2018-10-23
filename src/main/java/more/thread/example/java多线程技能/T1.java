package more.thread.example.java多线程技能;

/**
 * @ClassName T1
 * @Description java多线程实现方式, 待后续补充
 * @Author root
 * @Date 18-10-23 下午5:24
 * @Version 1.0
 **/
public class T1 {

    public static void main (String args[]) {
        System.out.println(Thread.currentThread().getName());//一个jvm进程至少包含一个线程,此处名称虽为main,但是与main方法无关

        MoreThread1 moreThread1_0 = new MoreThread1();
        MoreThread1 moreThread1_1 = new MoreThread1();


        MoreThread2 moreThread2_0 = new MoreThread2();
        MoreThread2 moreThread2_1 = new MoreThread2();

        moreThread1_0.start();
        moreThread2_0.run();
        moreThread1_1.start();
        moreThread2_1.run();

        //记录下输出结果,此处,试过很多次,证明了线程的运行顺序人为不可控
//        main
//        class more.thread.example.java多线程技能.MoreThread2
//        class more.thread.example.java多线程技能.MoreThread1
//        class more.thread.example.java多线程技能.MoreThread2
//        class more.thread.example.java多线程技能.MoreThread1
    }

}


/**
 * @Auther: Fmbah
 * @Date: 18-10-23 下午5:30
 * @Description: 多线程实现方式1: 继承自Thread
 */
class MoreThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(this.getClass());
        super.run();

    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-23 下午5:31
 * @Description: 多线程实现方式2: 实现Runnable接口
 */
class MoreThread2 implements Runnable {

    public void run() {
        System.out.println(this.getClass());
    }
}
