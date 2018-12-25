package more.thread.test.threadPoolExecutor;

import java.util.concurrent.*;

/**
 * @ClassName FixedThreadPoolTaskDemo
 * @Description
 * @Author root
 * @Date 18-12-4 上午9:41
 * @Version 1.0
 **/
public class FixedThreadPoolTaskDemo implements Runnable{

    private String name;
    public FixedThreadPoolTaskDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing: " + this.name);
            TimeUnit.SECONDS.sleep(((long)Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        for (int i = 1; i <= 5; i++) {
//            FixedThreadPoolTaskDemo task = new FixedThreadPoolTaskDemo("FixedThreadPoolTaskDemo" + i);
//            System.out.println("Created: " + task.getName());
//            executorService.execute(task);
//        }
//        executorService.shutdown();

        Future<String> submit = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
                return "1111";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });


        System.out.println("main run.........." + submit.get());
        executorService.shutdown();
    }
}

