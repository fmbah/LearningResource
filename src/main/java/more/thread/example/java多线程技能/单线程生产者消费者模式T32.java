package more.thread.example.java多线程技能;

/**
 * @ClassName 单线程生产者消费者模式T32
 * @Description
 * @Author root
 * @Date 18-10-31 上午10:39
 * @Version 1.0
 **/
public class 单线程生产者消费者模式T32 {

    public static void main(String args[]) {
        Object lock = new Object();
        P p = new P(lock);
        C c = new C(lock);
        new MyThreadP32(p).start();
        new MyThreadC32(c).start();
    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-31 上午10:42
 * @Description: 定义一个存储对象,用来存放生产者生产的值
 */
class ValueObject{
    public static String value = "";
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-31 上午10:43
 * @Description: 创建生产者,不能一直生产,所以要有个监控的地方!wait notify
 */
class P {
    private Object lock;
    public P(Object lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if(!ValueObject.value.equals("")) {
                    lock.wait();
                }
                double random = Math.random();
                System.out.println("set的value:" + random);
                ValueObject.value = "" + random;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class C {
    private Object lock;
    public C(Object lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if(ValueObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("取得值为:" + ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreadP32 extends Thread {
    private P p;
    public MyThreadP32(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            p.setValue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThreadC32 extends Thread {
    private C c;
    public MyThreadC32(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            c.getValue();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}