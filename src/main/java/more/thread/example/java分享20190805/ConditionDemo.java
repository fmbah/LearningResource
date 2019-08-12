package more.thread.example.java分享20190805;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();
    private static List<Integer> taskList = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
//        new Thread(new Consumer()).start();
//        new Thread(new Consumer()).start();

    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                // TODO: 为什么不用if
                // 参考：https://blog.csdn.net/u011863767/article/details/59731447
                // 永远在synchronized的方法或对象里使用wait、notify和notifyAll，不然Java虚拟机会生成 IllegalMonitorStateException。
                // 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知。
                // 永远在多线程间共享的对象（在生产者消费者模型里即缓冲区队列）上使用wait。
                while (taskList.isEmpty()) {
//                if (taskList.isEmpty()) {
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                taskList.remove(0);
                full.signalAll();
                System.out.println("开始消费任务,任务量:" + taskList.size());
                lock.unlock();
            }

        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                while (taskList.size() + 1 > 5) {
                    try {
                        System.out.println("任务量已最大");
                        full.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                taskList.add(2);
                empty.signalAll();
                System.out.println("生成任务,当前任务量:" + taskList.size());
                lock.unlock();
            }

        }
    }
}
