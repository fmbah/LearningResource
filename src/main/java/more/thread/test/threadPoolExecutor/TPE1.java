package more.thread.test.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TPE1
 * @Description
 * @Author root
 * @Date 18-11-13 下午4:12
 * @Version 1.0
 **/
public class TPE1 {
    public static void main (String args[]) {

        ExecutorService executorService1 = Executors.newFixedThreadPool(10);//线程数固定大小，无空闲线程回收，无界的阻塞队列。

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();//线程数为1个,无空闲线程回收,误解的阻塞队列

        ExecutorService executorService3 = Executors.newCachedThreadPool();//线程数大小不限，空闲线程超时时间为60s，空元素阻塞队列。

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);//具有调度或周期执行的线程池。

        System.out.println(Runtime.getRuntime() .availableProcessors() + 1);//CPU密集型任务
    }
}
