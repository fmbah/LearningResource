package more.thread.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
//        ConditionTread1 t1 = new ConditionTread1("t1");
//        ConditionTread2 t2 = new ConditionTread2("t2");
//
//        t1.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t2.start();


//        System.out.println("1. 终止状态： " + t1.isInterrupted());
//        t1.interrupt();
//        System.out.println("2. 终止状态： " + t1.isInterrupted());

        BlockingQueueDemo<Object> demo = new BlockingQueueDemo<>(3);
        for (int i=0; i< 10; i++) {
            int data = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();

                        try {
                            demo.enqueue(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }.start();
        }

        for (int i=0; i< 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();

                        try {
                            demo.dequue();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }.start();
        }


    }


    static Object lock = new Object();
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition = reentrantLock.newCondition();


    public static class ConditionTread1 extends Thread {

        public ConditionTread1(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            System.out.println("ConditionTread1   准备获取锁....");
//            synchronized (lock) {
//                System.out.println("ConditionTread1   获取锁成功....");
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    System.out.println("wait.....");
//                }
//            }

            try {
                reentrantLock.lock();
                System.out.println("ConditionTread1   获取锁成功....");
                try {
                    // await 自动释放锁  终止当前锁    false -> true -> false
                    condition.await();
                } catch (InterruptedException e) {
                    System.out.println(this.isInterrupted());
                    e.printStackTrace();
                } finally {
                    System.out.println("wait.....");
                }
            } finally {
                reentrantLock.unlock();
            }




        }
    }

    public static class ConditionTread2 extends Thread {

        public ConditionTread2(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            System.out.println("ConditionTread2   准备获取锁....");
//            synchronized (lock) {
//                System.out.println("ConditionTread2   获取锁成功....");
//                try {
//                    lock.notify();
//                } finally {
//                    System.out.println("notify.....");
//                }
//            }

            try {
                reentrantLock.lock();
                System.out.println("ConditionTread2   获取锁成功....");
                try {
//                    condition.signal();
                } finally {
                    System.out.println("signal.....");
                }

            } finally {
                reentrantLock.unlock();
            }


        }
    }



    public static class BlockingQueueDemo<E> {
        int size;

        LinkedList<E> list = new LinkedList<>();
        ReentrantLock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        public BlockingQueueDemo(int size) {
            this.size = size;
        }


        public void enqueue(E e) throws Exception {
            lock.lock();

            try {
                while (list.size() == size) {
                    notFull.await();
                }
                list.add(e);
                System.out.println("入队：" + e);
                notEmpty.signalAll();

            } finally {
                lock.unlock();
            }
        }

        public void dequue() throws Exception {
            lock.lock();

            try {
                while (list.isEmpty()) {
                    notEmpty.await();
                }
                E e = list.removeFirst();
                System.out.println("出队：" + e);
                notFull.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }


}
