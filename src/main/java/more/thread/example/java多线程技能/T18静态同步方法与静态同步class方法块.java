package more.thread.example.java多线程技能;

/**
 * @ClassName T17静态同步方法与静态同步class方法块
 * @Description synchronized写在类的静态方法上,是对类文件进行上锁,
 *              synchronized写在类的方法上,是对对象上锁,
 *                  锁对象不同,导致出现异步情况
 *                  锁对象相同,则会同步
 * @Author root
 * @Date 18-10-27 上午10:10
 * @Version 1.0
 **/
public class T18静态同步方法与静态同步class方法块 {

    public static void main (String args[]) {
        Service17 service17 = new Service17();
        MyThread17_A myThread17_a = new MyThread17_A(service17);
        myThread17_a.setName("a");
        MyThread17_B myThread17_b = new MyThread17_B(service17);
        myThread17_b.setName("b");
        MyThread17_C myThread17_c = new MyThread17_C(service17);
        myThread17_c.setName("c");
        myThread17_a.start();
        myThread17_b.start();
        myThread17_c.start();
    }


}

class Service17 {
    synchronized public static void printA() {
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",进入printA");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",离开printA");
    }

    synchronized public static void printB() {
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",进入printB");
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",离开printB");
    }

    synchronized public void printC() {
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",进入printC");
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", 时间:" + System.currentTimeMillis() + ",离开printC");
    }
}

class MyThread17_A extends Thread {
    private Service17 service17;
    public MyThread17_A(Service17 service17) {
        this.service17 = service17;
    }

    @Override
    public void run() {
        super.run();
        Service17.printA();
    }
}

class MyThread17_B extends Thread {
    private Service17 service17;
    public MyThread17_B(Service17 service17) {
        this.service17 = service17;
    }

    @Override
    public void run() {
        super.run();
        Service17.printB();
    }
}

class MyThread17_C extends Thread {
    private Service17 service17;
    public MyThread17_C(Service17 service17) {
        this.service17 = service17;
    }

    @Override
    public void run() {
        super.run();
        service17.printC();
    }
}