package more.thread.example.java多线程技能;

/**
 * @ClassName T9方法内变量线程安全
 * @Description
 * @Author root
 * @Date 18-10-25 下午4:37
 * @Version 1.0
 **/
public class T9变量的线程安全与否 {

    /**
     *
     * 功能描述: 演示线程安全的情况,
     *          注释:2018-10-30   为什么线程安全,因为方法内的变量都是存在线程栈上的,属于线程私有,所以不会出现同步问题!
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-25 下午5:06
     */
    public static void main (String args[]) {
        ExampleBean9 exampleBean9 = new ExampleBean9();
        MyThread9_1 myThread9_1 = new MyThread9_1(exampleBean9);
        myThread9_1.start();
        MyThread9_2 myThread9_2 = new MyThread9_2(exampleBean9);
        myThread9_2.start();
    }

    /**
     *
     * 功能描述: 演示未上锁===>线程非安全的情况,
     *          如果上锁,同一个对象一把锁===>两个线程安全,
     *          如果上锁,多个对象多把锁===>两个线程非安全,
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-25 下午5:07
     */
//    public static void main (String args[]) {
//        ExampleBean9_1 exampleBean9_1 = new ExampleBean9_1();
//        MyThread9_1_1 myThread9_1_1 = new MyThread9_1_1(exampleBean9_1);
//        myThread9_1_1.start();
//        MyThread9_2_2 myThread9_2_1 = new MyThread9_2_2(exampleBean9_1);
//        myThread9_2_1.start();
//
//        ExampleBean9_1 exampleBean9_1 = new ExampleBean9_1();
//        ExampleBean9_1 exampleBean9_1_1 = new ExampleBean9_1();
//        MyThread9_1_1 myThread9_1_1 = new MyThread9_1_1(exampleBean9_1);
//        myThread9_1_1.start();
//        MyThread9_2_2 myThread9_2_1 = new MyThread9_2_2(exampleBean9_1_1);
//        myThread9_2_1.start();
//
//    }

}

class ExampleBean9 {
    public void setBean(String username) {
        int count = 1;
        if (username.equals("a")) {
            count = 2;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1设置完成");
        } else {
            count = 3;
            System.out.println("线程2设置完成");
        }
        System.out.println(username + "==" + count);
    }
}
class MyThread9_1 extends Thread {
    private ExampleBean9 exampleBean9;
    public MyThread9_1(ExampleBean9 exampleBean9){
        this.exampleBean9 = exampleBean9;
    }

    @Override
    public void run() {
        super.run();
        exampleBean9.setBean("a");
    }
}
class MyThread9_2 extends Thread {
    private ExampleBean9 exampleBean9;
    public MyThread9_2(ExampleBean9 exampleBean9){
        this.exampleBean9 = exampleBean9;
    }

    @Override
    public void run() {
        super.run();
        exampleBean9.setBean("b");
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-25 下午5:12
 * @Description: 同一个对象一把锁
 */
class ExampleBean9_1 {
    int count = 1;
    synchronized public void setBean(String username) {
        if (username.equals("a")) {
            count = 2;
            System.out.println("线程1设置完成");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count = 3;
            System.out.println("线程2设置完成");
        }
        System.out.println(username + "==" + count);
    }
}
class MyThread9_1_1 extends Thread {
    private ExampleBean9_1 exampleBean9_1;
    public MyThread9_1_1(ExampleBean9_1 exampleBean9_1){
        this.exampleBean9_1 = exampleBean9_1;
    }

    @Override
    public void run() {
        super.run();
        exampleBean9_1.setBean("a");
    }
}
class MyThread9_2_2 extends Thread {
    private ExampleBean9_1 exampleBean9_1;
    public MyThread9_2_2(ExampleBean9_1 exampleBean9_1){
        this.exampleBean9_1 = exampleBean9_1;
    }

    @Override
    public void run() {
        super.run();
        exampleBean9_1.setBean("b");
    }
}