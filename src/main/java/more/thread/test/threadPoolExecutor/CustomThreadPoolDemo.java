package more.thread.test.threadPoolExecutor;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName CustomThreadPoolDemo
 * @Description
 *
 * 自定义线程池(不推荐自己实现,可能会导致死锁等问题)
 *
 * @Author root
 * @Date 18-12-4 上午10:46
 * @Version 1.0
 **/
public class CustomThreadPoolDemo {
    private final int poolSize;//Thread pool size

    private final WorkerThread[] workers;//Internally pool is an array

    private final LinkedBlockingQueue<Runnable> queue;//FIFO ordering

    public CustomThreadPoolDemo(int poolSize) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<>();
        workers = new WorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }

    public void shutdown() {
        System.out.println("Shutting down thread pool");
        for (int i = 0; i < poolSize; i++) {
            workers[i] = null;
        }
    }

    public static void main(String[] args) {
        CustomThreadPoolDemo customThreadPoolDemo = new CustomThreadPoolDemo(2);
        for (int i = 1; i <= 5; i++) {
            customThreadPoolDemo.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ", time: " + new Date().getSeconds());
                }
            });
        }
    }
}
