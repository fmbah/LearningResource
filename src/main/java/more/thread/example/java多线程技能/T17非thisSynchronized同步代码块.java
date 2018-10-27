package more.thread.example.java多线程技能;

/**
 * @ClassName T17非thisSynchronized同步代码块
 * @Description 将synchronized修饰父的权限排序:
 *              在方法上 >= 方法里this修饰的同步代码块 >方法里非this修饰的同步代码块
 * @Author root
 * @Date 18-10-26 下午7:34
 * @Version 1.0
 **/
public class T17非thisSynchronized同步代码块 {

    public static void main (String args[]) {
        MyExample17 myExample17 = new MyExample17();
        MyThread17 myThread17 = new MyThread17(myExample17);
        MyThread17_1 myThread17_1 = new MyThread17_1(myExample17);
        myThread17.start();
        myThread17_1.start();
//        myExample17.b();
//        myExample17.a();//测试锁对象为不同的新对象
    }

}

class MyExample17 {
    String isStop0 = "1";//同一把锁
    String isStop3 = new String();//创建两个MyExample7类,请问,此成员变量isStop3共有一个吗?否,两个对象,所以如果拿这个对象当作锁对象,则会出现异步调用的情况
    public void a () {
        String isStop1 = "1";//同一把锁
        String isStop2 = new String("1");//每个线程进来之后,都是一把新锁!!!,则会出现异步调用的情况
        synchronized (isStop3) {
            System.out.println("MyExample17 a starting........");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyExample17 a ended........");
        }
    }

    synchronized public void b () {
        System.out.println("MyExample17 b starting........");
        System.out.println("MyExample17 b ended........");
    }
}

class MyThread17 extends Thread {
    private MyExample17 myExample17;
    public MyThread17(MyExample17 myExample17) {
        this.myExample17 = myExample17;
    }

    @Override
    public void run() {
        super.run();
        myExample17.a();
    }
}

class MyThread17_1 extends Thread {
    private MyExample17 myExample17;
    public MyThread17_1(MyExample17 myExample17) {
        this.myExample17 = myExample17;
    }

    @Override
    public void run() {
        super.run();
        myExample17.a();
    }
}