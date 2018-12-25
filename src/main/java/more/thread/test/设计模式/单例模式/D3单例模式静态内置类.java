package more.thread.test.设计模式.单例模式;

/**
 * @ClassName D3单例模式静态内置类
 * @Description
 *              饿汗模式:不能实现懒加载,用不用都占用了内存
 *              懒汉模式:实现了延迟加载,性能下降
 *
 *              由于静态单例对象没有作为Singleton的成员变量直接实例化，
 *              因此类加载时不会实例化Singleton，第一次调用getInstance()时将加载内部类HolderClass，
 *              在该内部类中定义了一个static类型的变量instance，此时会首先初始化这个成员变量，
 *              由Java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。
 *              由于getInstance()方法没有任何线程锁定，因此其性能不会造成任何影响。
 * @Author root
 * @Date 18-11-10 上午10:02
 * @Version 1.0
 **/
public class D3单例模式静态内置类 {

    public static void main (String args[]) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject3.getInstance().hashCode());
            }
        };

        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
    }

}

class MyObject3 {
    private static class MyObjectHandler {
        private static MyObject3 myObject3 = new MyObject3();
    }
    private MyObject3 () {}
    public static MyObject3 getInstance () {
        return MyObjectHandler.myObject3;
    }
}
