package more.thread.test.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName ExecutorService方法
 * @Description
 * @Author root
 * @Date 18-12-3 下午5:37
 * @Version 1.0
 **/
public class ExecutorService方法 {
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);//固定数量的线程池

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();//缓存线程池,执行固定一个任务且时间有限使用此线程很好；如果线程多且时间不限,则不应该使用此线程,过多线程的申请会导致系统性能下降

        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);//周期性执行任务的线程池

        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();//一个线程的线程池,执行一个任务专用

        ExecutorService newWorkStealingPool = Executors.newWorkStealingPool(4);//多处理器中,同一时间点执行某任务最大分配的线程数
    }

}


