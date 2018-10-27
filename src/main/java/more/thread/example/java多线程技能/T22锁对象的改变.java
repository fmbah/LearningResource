package more.thread.example.java多线程技能;

/**
 * @ClassName T22锁对象的改变
 * @Description 对于锁对象来说: 只要对象不变(应该是引用地址),即使是属性改变,运行结果依然是同步的.
 *                            如果锁对象发生了改变,那么线程锁也失效了,运行结果会出现异步的情况.
 *
 * @Author root
 * @Date 18-10-27 上午11:58
 * @Version 1.0
 **/
public class T22锁对象的改变 {

    public static void main (String args[]) {
        final Service22 service22 = new Service22();
        Thread thread_1 = new Thread(new Runnable() {
            public void run() {
                service22.method();
            }
        });
        Thread thread_2 = new Thread(new Runnable() {
            public void run() {
                service22.method();
            }
        });
        thread_1.start();
        thread_2.start();

//        Thread-0=== start time:1540618678897
//        Thread-0=== end time:1540618678897
//        Thread-1=== start time:1540618678897
//        Thread-1=== end time:1540618678897

    }

}

class Service22 {
    String lock = "123";
    public void method() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "=== start time:" + System.currentTimeMillis());
            lock = "456";
            System.out.println(Thread.currentThread().getName() + "=== end time:" + System.currentTimeMillis());
        }
    }

}
