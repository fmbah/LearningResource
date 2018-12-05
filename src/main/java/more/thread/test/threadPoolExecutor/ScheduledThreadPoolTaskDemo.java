package more.thread.test.threadPoolExecutor;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName ScheduledThreadPoolTaskDemo
 * @Description
 * @Author root
 * @Date 18-12-4 上午10:07
 * @Version 1.0
 **/
public class ScheduledThreadPoolTaskDemo implements Runnable{
    private String name;
    public ScheduledThreadPoolTaskDemo(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public void run() {
        System.out.println("Executing: " + name + ", Current Seconds: " + new Date().getSeconds());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        ScheduledThreadPoolTaskDemo task = new ScheduledThreadPoolTaskDemo("Repeat Task");
        System.out.println("Created: " + task.getName() + ", Created Seconds: " + new Date().getSeconds());

        scheduledExecutorService.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);//周期性执行任务(任务, 首次执行任务延迟时间, 上一个任务执行完成后延迟时间执行下一个任务, 时间单位)

//        scheduledExecutorService.schedule(task, 2, TimeUnit.SECONDS);//相应时间延迟后执行一次任务(2s后执行任务)

//        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);//也是周期性执行任务(任务, 首次执行任务的延迟时间, 间隔延迟时间后执行下一次任务不管上一个任务是否处理完成, 时间单位)
    }
}
