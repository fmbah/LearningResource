package more.thread.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author a8079
 * @title: ThreadPoolTest1
 * @projectName LearningResource
 * @description: TODO
 * @date 2019/11/919:19
 */
public class ThreadPoolTest1 implements Runnable{
    static ExecutorService executorService = new ThreadPoolExecutor(5, 5, 2L, TimeUnit.MINUTES, new LinkedBlockingQueue(2000));
    public static void main(String[] args) {
        int count = 10000;
        while (--count > 0) {
            try {
                Thread.sleep(new Random().nextInt(10) * 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new ThreadPoolTest1());
            System.out.println("当前执行【"+ count +"】条数据......");
        }
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
