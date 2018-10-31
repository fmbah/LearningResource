package more.thread.example.java多线程技能;

/**
 * @ClassName T27Notify和wait
 * @Description wait方法 使线程停止运行
 *              notify方法 使线程恢复运行
 * @Author root
 * @Date 18-10-30 下午3:37
 * @Version 1.0
 **/
public class T27Notify和wait {

    public static void main(String args[]) {
        String str = new String("");
        MyThread27 myThread27 = new MyThread27(str);
        MyThread27_1 myThread27_1 = new MyThread27_1(str);
        myThread27.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread27_1.start();
    }

}

class MyThread27 extends Thread {

    Object object;
    public MyThread27(Object object) {
        this.object = object;
    }

    public void run() {
        try {
            synchronized (object) {
                System.out.println("1");
                object.wait();
                System.out.println("2");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread27_1 extends Thread {

    Object object;
    public MyThread27_1(Object object) {
        this.object = object;
    }

    public void run() {
        synchronized (object) {
            System.out.println("3");
            object.notify();
            System.out.println("4");
        }
    }

}