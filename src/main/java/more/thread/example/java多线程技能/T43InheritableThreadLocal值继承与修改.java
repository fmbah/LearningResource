package more.thread.example.java多线程技能;

import java.util.Date;

/**
 * @ClassName InheritableThreadLocal值继承
 * @Description
 * @Author root
 * @Date 18-11-6 下午3:04
 * @Version 1.0
 **/
public class T43InheritableThreadLocal值继承与修改 {

    public static void main(String args[]) {
//        for (int i = 0; i < 20; i++) {
//            System.out.println("main: i :" + i + "==>" + Tools43.inheritableThreadLocalExt.get());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        MyThread43 myThread43 = new MyThread43();
//        myThread43.start();

//        System.out.println("last1: i :==>" + Tools43.inheritableThreadLocalExt1.get());
//        System.out.println("last2: i :==>" + Tools43.inheritableThreadLocalExt1.get());


        new Thread(new Runnable() {
            public void run() {
                System.out.println("last3: i :==>" + Tools43.inheritableThreadLocalExt1.get());

                new Thread(new Runnable() {
                    public void run() {
                        System.out.println("last4: i :==>" + Tools43.inheritableThreadLocalExt1.get());
                    }
                }).start();
            }
        }).start();



    }

}


/**
 * @Auther: Fmbah
 * @Date: 18-11-6 下午3:12
 * @Description:   InheritableThreadLocal类,不会随着时间改变初始值,第一次初始化完成后,值就已经确定了
 *                  ThreadLocal类,会随着时间改变初始值,每一次get默认值的时候,都会取得最新的时间
 *
 *                  实现了InheritableThreadLocal类的childValue方法,即可在第一次使用此对象的线程为主线程,并在其创建的子线程中覆盖其默认值
 *
 */
class InheritableThreadLocalExt extends InheritableThreadLocal {
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "我在子线程上加的";
    }
}

class Tools43 {
    public static InheritableThreadLocalExt inheritableThreadLocalExt = new InheritableThreadLocalExt();
    public static InheritableThreadLocalExt inheritableThreadLocalExt1 = new InheritableThreadLocalExt();
}

class MyThread43 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 20; i++) {
            System.out.println("MyThread43: i :" + i + "==>" + Tools43.inheritableThreadLocalExt.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}