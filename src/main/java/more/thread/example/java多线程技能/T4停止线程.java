package more.thread.example.java多线程技能;

/**
 * @ClassName T4停止线程
 * @Description Thread.stop();不建议使用,抛弃了的api
 *              Thread.interrupt();不会终止一个正在运行的线程,需要加一个标志位配合使用
 *
 *
 *              1:使用退出标志,使线程正常退出,也就是run方法完成后线程终止；
 *              2:使用stop方法强行终止线程=====No
 *              3:使用interrupt()方法中断线程
 *
 * @Author root
 * @Date 18-10-24 下午8:00
 * @Version 1.0
 **/
public class T4停止线程 {

    /**
     *
     * 功能描述: 在进行多线程测试时,不要使用Junit注解测试,会有些误差在
     *
     *          此代码中在启动了线程之后, 停止了main线程1s,1s后执行了thread线程中断方法,但是并没有像预期一样,马上停止输出,而是正常的输出了50W行
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 下午8:04
     */
//    public static void main (String args[]) {
//        MyThread4 myThread4 = new MyThread4();
//        Thread thread = new Thread(myThread4);
//        thread.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();
//    }

    /**
     *
     * 功能描述: 判断线程是否是停止状态
     *          Thread.interrupted(); 测试当前线程是否已经中断,
     *          this.isInterrupted(); 测试线程是否已经中断
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 下午8:11
     */
    public static void main (String args[]) {
        MyThread4 myThread4 = new MyThread4();
        myThread4.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread4.interrupt();
//        Thread.currentThread().interrupt();
//        System.out.println("=============================是否停止1:" + Thread.interrupted());
//        System.out.println("=============================是否停止2:" + Thread.interrupted());


        MyThread4_1 myThread4_1 = new MyThread4_1();
        myThread4_1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread4_1.interrupt();
    }



}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 下午8:50
 * @Description: 通过异常捕获的方式中断线程
 */
class MyThread4 extends Thread {
    @Override
    public void run() {

        super.run();
        try {
            for (int i = 0; i < 10000; i++) {

                if (this.isInterrupted()) {
                    System.out.println("MyThread4 当前线程已经被停止了");
                    throw new InterruptedException();
                }
                System.out.println(i);
            }

            System.out.println("MyThread4 如果此处被输出,则证明线程未被正确停止!!!");
        } catch (InterruptedException e) {
            System.out.println("MyThread4 此时,线程被完全停止");
            e.printStackTrace();
        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 下午8:54
 * @Description: 通过return的方式中断线程
 */
class MyThread4_1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++) {

            if (this.isInterrupted()) {
                System.out.println("MyThread4_1 当前线程已经被停止了");
                return;
            }
            System.out.println(i);
        }
        System.out.println("MyThread4_1 如果此处被输出,则证明线程未被正确停止!!!");
    }
}



