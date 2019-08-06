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
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();

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
                while (taskList.isEmpty()) {
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
