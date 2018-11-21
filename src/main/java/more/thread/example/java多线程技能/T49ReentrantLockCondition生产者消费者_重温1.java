package more.thread.example.java多线程技能;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T49ReentrantLockCondition生产者消费者_重温1
 * @Description
 * @Author root
 * @Date 18-11-15 上午11:11
 * @Version 1.0
 **/
public class T49ReentrantLockCondition生产者消费者_重温1 {

    private static class MyDataProcessService49 {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        private List<Integer> data = new ArrayList<Integer>();

        public void methodProduct () {
            try {
                lock.lock();
                while (data.size() > 5) {
                    condition.await();
                }
                data.add(1);
                System.out.println("methodProduct....当前data长度: " + data.size());
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void methodConsumer () {
            try {
                lock.lock();
                while (data.size() == 0) {
                    condition.await();
                }
                int size = data.size();
                if (size > 1) {
                    Random random = new Random();
                    data.remove(random.nextInt(data.size() - 1));
                } else {
                    data.remove(0);
                }
                System.out.println("methodConsumer....当前data长度: " + data.size());
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class MyDataProductThread49A extends Thread{
        private MyDataProcessService49 myDataProcessService49;
        public MyDataProductThread49A (MyDataProcessService49 myDataProcessService49) {
            this.myDataProcessService49 = myDataProcessService49;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myDataProcessService49.methodProduct();
            }
        }
    }

    private static class MyDataConsumerThread49A extends Thread{
        private MyDataProcessService49 myDataProcessService49;
        public MyDataConsumerThread49A (MyDataProcessService49 myDataProcessService49) {
            this.myDataProcessService49 = myDataProcessService49;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myDataProcessService49.methodConsumer();
            }
        }
    }

    public static void main (String argsp[]) {
        MyDataProcessService49 myDataProcessService49 = new MyDataProcessService49();

        MyDataProductThread49A myDataProductThread49A = new MyDataProductThread49A(myDataProcessService49);
        MyDataProductThread49A myDataProductThread49A_1 = new MyDataProductThread49A(myDataProcessService49);

        MyDataConsumerThread49A myDataConsumerThread49A = new MyDataConsumerThread49A(myDataProcessService49);
        MyDataConsumerThread49A myDataConsumerThread49A_1 = new MyDataConsumerThread49A(myDataProcessService49);

        myDataProductThread49A.start();
        myDataProductThread49A_1.start();

        myDataConsumerThread49A.start();
        myDataConsumerThread49A_1.start();

    }
}
