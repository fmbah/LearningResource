package more.thread.example.java多线程技能;

/**
 * @ClassName T10Synchronized方法与锁对象
 * @Description
 * @Author root
 * @Date 18-10-25 下午5:22
 * @Version 1.0
 **/
public class T10Synchronized方法与锁对象 {

    public static void main (String args[]) {
        MyMethod myMethod = new MyMethod();
        MyThread10_1 myThread10_1 = new MyThread10_1(myMethod, "MyThread10_1");
        MyThread10_2 myThread10_2 = new MyThread10_2(myMethod, "MyThread10_2");
        myThread10_1.start();
        myThread10_2.start();
    }

//    public static void main (String args[]) {
//        MyMethod_1 myMethod_1 = new MyMethod_1();
//        MyThread10_1_1 myThread10_1_1 = new MyThread10_1_1(myMethod_1);
//        MyThread10_2_2 myThread10_2_2 = new MyThread10_2_2(myMethod_1);
//        MyThread10_3_3 myThread10_3_3 = new MyThread10_3_3(myMethod_1);
//        myThread10_1_1.start();
//        myThread10_2_2.start();
//        myThread10_3_3.start();
//    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-26 上午9:20
 * @Description: 不加锁的时候,出现两个线程进入同一个方法,延时后结束
 *               加锁的时候,则先进入方法的线程获得该方法锁,并阻止其它线程访问,达到了顺序访问的效果
 *               上面两种情况都是针对共享一个对象而言,如果没有存在共享对象,锁的意义就没有了!
 *
 *               注释 2018-10-30:此时的锁,只是演示通过synchronized修饰符达到了顺序线程间顺序访问方法的作用
 */
class MyMethod {
    synchronized public void printMethodSort() {
        System.out.println(Thread.currentThread().getName() + ",come here!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ",end!");
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-26 上午9:25
 * @Description: 演示一个类中两个方法的情况,a方法加锁,b方法未加锁
 *               分别由两个线程调用不同的方法,看情况
 *                  1. a方法加锁,b方法未加锁--> A线程访问a方法,上锁,B线程访问b方法,互不影响
 *                  2. a方法加锁,b方法加锁--> A线程访问a方法,将先拥有对象中已上锁方法的锁,也就是ab方法都被A线程占用,此时B线程等待A线程释放锁后才可以正常访问b方法
 */
class MyMethod_1 {
    synchronized public void printMethodSortA() {
        System.out.println(Thread.currentThread().getName() + ", printMethodSortA, come here!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", printMethodSortA, end!");
    }

    synchronized public void printMethodSortB() {
        System.out.println(Thread.currentThread().getName() + ", printMethodSortB, come here!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", printMethodSortB, end!");
    }
    public void printMethodSortC() {
        System.out.println(Thread.currentThread().getName() + ", printMethodSortC, come here!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", printMethodSortC, end!");
    }
}

class MyThread10_1 extends Thread {
    private MyMethod myMethod;
    public MyThread10_1(MyMethod myMethod, String name) {
        this.myMethod = myMethod;
        this.setName(name);
    }
    @Override
    public void run() {
        super.run();
        myMethod.printMethodSort();
    }
}
class MyThread10_2 extends Thread {
    private MyMethod myMethod;
    public MyThread10_2(MyMethod myMethod, String name) {
        this.myMethod = myMethod;
        this.setName(name);
    }
    @Override
    public void run() {
        super.run();
        myMethod.printMethodSort();
    }
}


class MyThread10_1_1 extends Thread {
    private MyMethod_1 myMethod_1;
    public MyThread10_1_1(MyMethod_1 myMethod_1) {
        this.myMethod_1 = myMethod_1;
    }
    @Override
    public void run() {
        super.run();
        myMethod_1.printMethodSortA();
    }
}
class MyThread10_2_2 extends Thread {
    private MyMethod_1 myMethod_1;
    public MyThread10_2_2(MyMethod_1 myMethod_1) {
        this.myMethod_1 = myMethod_1;
    }
    @Override
    public void run() {
        super.run();
        myMethod_1.printMethodSortB();
    }
}
class MyThread10_3_3 extends Thread {
    private MyMethod_1 myMethod_1;
    public MyThread10_3_3(MyMethod_1 myMethod_1) {
        this.myMethod_1 = myMethod_1;
    }
    @Override
    public void run() {
        super.run();
        myMethod_1.printMethodSortC();
    }
}