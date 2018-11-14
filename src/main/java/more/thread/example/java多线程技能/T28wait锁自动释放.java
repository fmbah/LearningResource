package more.thread.example.java多线程技能;

/**
 * @ClassName T28wait锁自动释放
 * @Description
 * @Author root
 * @Date 18-10-30 下午3:55
 * @Version 1.0
 **/
public class T28wait锁自动释放 {

    public static void main(String args[]) {
        Object lock = new Object();
        Thread28A thread28A = new Thread28A(lock);
        thread28A.setName("A");
        Thread28B thread28B = new Thread28B(lock);
        thread28B.setName("B");
        thread28A.start();
        thread28B.start();

//        A,begin wait
//        B,begin wait
    }
}

class Service28 {
    public void testMethod(Object object) {
        try {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ",begin wait");
                object.wait();//当wait方法存在的时候,会自动释放锁
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + ",end wait");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread28A extends Thread {
    private Object object;
    public Thread28A(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        Service28 service28 = new Service28();
        service28.testMethod(object);
    }
}

class Thread28B extends Thread {
    private Object object;
    public Thread28B(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        Service28 service28 = new Service28();
        service28.testMethod(object);
    }
}