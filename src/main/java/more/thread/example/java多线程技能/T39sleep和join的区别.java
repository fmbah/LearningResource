package more.thread.example.java多线程技能;

/**
 * @ClassName T39sleep和join的区别
 * @Description join内部实现依靠wait,在时间结束后,可自动释放锁, while(true) wait(0)====>等待下便释放锁
 *              sleep线程休眠操作,线程锁不会自动释放
 * @Author root
 * @Date 18-11-6 上午10:16
 * @Version 1.0
 **/
public class T39sleep和join的区别 {

    public static void main(String args[]) {
        MyThread39B myThread39B = new MyThread39B();
        MyThread39A myThread39A = new MyThread39A(myThread39B);
        myThread39A.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread39C myThread39C = new MyThread39C(myThread39B);
        myThread39C.start();
    }

}

class MyThread39B extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("b begin time: " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b end time: " + System.currentTimeMillis());
    }

    synchronized public void bService () {
        System.out.println("bService start time: " + System.currentTimeMillis());
    }
}

class MyThread39A extends Thread {
    private MyThread39B myThread39B;
    public MyThread39A (MyThread39B myThread39B) {
        this.myThread39B = myThread39B;
    }

    @Override
    public void run() {
        super.run();

        synchronized (myThread39B) {
            myThread39B.start();
            try {
                myThread39B.join();//
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class MyThread39C extends Thread {
    private MyThread39B myThread39B;
    public MyThread39C (MyThread39B myThread39B) {
        this.myThread39B = myThread39B;
    }

    @Override
    public void run() {
        super.run();
        myThread39B.bService();
    }
}