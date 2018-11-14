package more.thread.example.java多线程技能;

/**
 * @ClassName T17锁分类
 * @Description
 *              目前知道的关于synchronized锁的分类
 *                  方法锁
 *                  方法内部this.getClass()锁
 *                  方法内部this锁
 *                  方法内部非静态成员变量锁
 *                  静态方法锁/静态关键字锁
 *              这几类锁,锁对象都不同,所以在不同线程进行访问的时候,不会互相影响,只有类型相同,且操作的锁相同才会出现同步的情况
 * @Author root
 * @Date 18-11-14 上午10:39
 * @Version 1.0
 **/
public class T17锁分类 {
    public static void main (String args[]) {
        final T17ServiceSynchronizedType t17ServiceSynchronizedType = new T17ServiceSynchronizedType();

        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodA();
            }
        };

        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodB();
            }
        };
        Runnable runnableB_1 = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodB_1();
            }
        };

        Runnable runnableC = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodC();
            }
        };

        Runnable runnableD = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodD();
            }
        };

        Runnable runnableE = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodE();
            }
        };

        Runnable runnableF = new Runnable() {
            @Override
            public void run() {
                t17ServiceSynchronizedType.methodF();
            }
        };

        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        Thread threadB_1 = new Thread(runnableB_1);
        Thread threadC = new Thread(runnableC);
        Thread threadD = new Thread(runnableD);
        Thread threadE = new Thread(runnableE);
        Thread threadF = new Thread(runnableF);

        threadA.start();
        threadB.start();//this.getClass()锁
        threadB_1.start();
        threadC.start();//this锁
        threadD.start();
//        threadE.start();//静态方法锁与静态成员变量共享一把锁,称为类锁
//        threadF.start();//
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-11-14 上午10:42
 * @Description: 描述锁的分类
 */
class T17ServiceSynchronizedType {
    private static String staticStr = "staticStr";//类静态成员变量
    private String str = "str";//类非静态成员变量

    public synchronized void methodA () {
        System.out.println("methodA");
    }
    public void methodB () {
        synchronized (this.getClass()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("methodB");
        }
    }
    public void methodB_1 () {
        synchronized (this.getClass()) {
            System.out.println("methodB_1");
        }
    }
    public void methodC () {
        synchronized (this) {
            System.out.println("methodC");
        }
    }
    public void methodD () {
        synchronized (str) {
            System.out.println("methodD");
        }
    }
    public synchronized static void methodE () {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodE");
    }
    public void methodF () {
        synchronized (staticStr) {
            System.out.println("methodF");
        }
    }
}