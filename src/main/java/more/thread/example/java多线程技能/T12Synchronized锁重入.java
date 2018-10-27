package more.thread.example.java多线程技能;

/**
 * @ClassName T12Synchronized锁重入
 * @Description 可重入锁:自己可以再次获取自己内部的锁
 *                      当存在继承关系时,子类完全可以通过"可重入锁"调用父类的同步方法
 *
 * @Author root
 * @Date 18-10-26 上午10:01
 * @Version 1.0
 **/
public class T12Synchronized锁重入 {
    public static void main (String args[]) {
//        final MyDoubleSynchronized myDoubleSynchronized = new MyDoubleSynchronized();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                myDoubleSynchronized.sayHi_1();
//            }
//        }.start();
//        myDoubleSynchronized.sayHi_1();

        final MySubDoubleSynchronized mySubDoubleSynchronized = new MySubDoubleSynchronized();
        new Thread() {
            @Override
            public void run() {
                super.run();
                mySubDoubleSynchronized.sayHi_1_sub();
            }
        }.start();
        mySubDoubleSynchronized.sayHi_1_sub();
    }
}

class MyDoubleSynchronized {
    synchronized public void sayHi_1() {
        System.out.println(Thread.currentThread().getName() + "sayHi_1");
        this.sayHi_2();
    }

    synchronized public void sayHi_2() {
        System.out.println(Thread.currentThread().getName() + "sayHi_2");
        this.sayHi_3();
    }
    synchronized public void sayHi_3() {
        System.out.println(Thread.currentThread().getName() + "sayHi_3");
    }
}

class MySubDoubleSynchronized extends MyDoubleSynchronized {
    synchronized public void sayHi_1_sub() {
        System.out.println(Thread.currentThread().getName() + "MySubDoubleSynchronized sayHi_1_sub");
        super.sayHi_1();
    }
}