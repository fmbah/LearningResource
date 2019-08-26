package more.thread.example.threadpool原理分析;

import java.util.concurrent.*;

public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        int keepAliveTime = 1000;
        int queueSize = 3;
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize), threadFactory, new RejectHandler());

        for (int i = 0; i < 10; i++) {
            System.out.println("ActiveCount: " + executor.getActiveCount() + "   " +
                    "CorePoolSize: " + executor.getCorePoolSize() + "   " +
                    "LargestPoolSize: " + executor.getLargestPoolSize() + "   " +
                    "MaximumPoolSize: " + executor.getMaximumPoolSize() + "   " +
                    "TaskCount: " + executor.getTaskCount() + "   " +
                    "PoolSize: " + executor.getPoolSize() + "   " +
                    "CompletedTaskCount: " + executor.getCompletedTaskCount());
            executor.execute(new Worker(i));
        }

        executor.shutdown();
    }


    public static class Worker implements Runnable{
        private Integer count;
        public Worker(Integer count) {
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start : " + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end : " + count);
        }
    }

    public static class RejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + "is reject....");
        }
    }

}
