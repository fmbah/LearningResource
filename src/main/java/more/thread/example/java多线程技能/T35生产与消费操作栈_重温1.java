package more.thread.example.java多线程技能;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T35生产与消费操作栈_重温1
 * @Description
 *              下面显示了 1 生产 1 消费 相互作用
 *
 * @Author root
 * @Date 18-11-14 下午4:10
 * @Version 1.0
 **/
public class T35生产与消费操作栈_重温1 {

    public static class MyServiceT35 {
        private List list = new ArrayList();

        public synchronized void push () {
            list.add("a");
        }

        public synchronized void pop () {
            list.remove(0);
        }
    }

    public static class MyProductT35 extends Thread {
        private Object lock;
        private MyServiceT35 myServiceT35;
        public MyProductT35 (Object lock, MyServiceT35 myServiceT35) {
            this.lock = lock;
            this.myServiceT35 = myServiceT35;
        }

        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    Thread.sleep(1000);
                    synchronized (lock) {
                        while (myServiceT35.list.size() != 0) {
                            lock.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " start");
                        myServiceT35.push();
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyComsumterT35 extends Thread {
        private Object lock;
        private MyServiceT35 myServiceT35;
        public MyComsumterT35 (Object lock, MyServiceT35 myServiceT35) {
            this.lock = lock;
            this.myServiceT35 = myServiceT35;
        }

        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    Thread.sleep(1000);
                    synchronized (lock) {
                        while (myServiceT35.list.size() == 0) {
                            lock.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + " start");
                        myServiceT35.pop();
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main (String args[]) {
        Object lock = "lock";
        MyServiceT35 myServiceT35 = new MyServiceT35();
        MyProductT35 myProductT35 = new MyProductT35(lock, myServiceT35);
        myProductT35.setName("生产者1");
        MyProductT35 myProductT35_1 = new MyProductT35(lock, myServiceT35);
        myProductT35_1.setName("生产者2");
        MyComsumterT35 myComsumterT35 = new MyComsumterT35(lock, myServiceT35);
        myComsumterT35.setName("消费者1");
        MyComsumterT35 myComsumterT35_1 = new MyComsumterT35(lock, myServiceT35);
        myComsumterT35_1.setName("消费者2");
        MyComsumterT35 myComsumterT35__2 = new MyComsumterT35(lock, myServiceT35);
        myComsumterT35__2.setName("消费者3");

        myProductT35.start();
//        myProductT35_1.start();
        myComsumterT35.start();
        myComsumterT35_1.start();
        myComsumterT35__2.start();

    }
}
