package more.thread.test.thread测试;

/**
 * @ClassName ThreadTest
 * @Description
 * @Author root
 * @Date 19-2-15 上午9:55
 * @Version 1.0
 **/
public class ThreadTest {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();

        threadA.setThreadC(threadC);
        threadB.setThreadA(threadA);
        threadC.setThreadB(threadB);

        threadA.start();
//        threadB.start();
//        threadC.start();

//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }


}
class ThreadA extends Thread{
    private ThreadC threadC;

    public void setThreadC(ThreadC threadC) {
        this.threadC = threadC;
    }

    @Override
    public void run() {
//        while(true) {
            synchronized (threadC) {
                synchronized (this) {
                    System.out.println("A..");
                    this.notify();
                }
                try {
                    threadC.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}
class ThreadB extends Thread{
    private ThreadA threadA;

    public void setThreadA(ThreadA threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
//        while(true) {
            synchronized (threadA) {
                synchronized (this) {
                    System.out.println("B..");
                    this.notify();
                }
                try {
                    threadA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}
class ThreadC extends Thread{
    private ThreadB threadB;
    public void setThreadB(ThreadB threadB) {
        this.threadB = threadB;
    }
    @Override
    public void run() {
//        while(true) {
            synchronized (threadB) {
                synchronized (this) {
                    System.out.println("C..");
                    this.notify();
                }
                try {
                    threadB.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}
