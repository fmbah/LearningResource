package more.thread.example.java分享20190805;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    // TODO: 控制线程的并发数量
    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(() -> {
                try {
                    semaphore.acquire();
                    doWork();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
    }
    private static void doWork() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ",finish work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
