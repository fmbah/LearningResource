package more.thread.test.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TPE1
 * @Description
 * @Author root
 * @Date 18-11-13 下午4:12
 * @Version 1.0
 **/
public class TPE1 {
    public static void main (String args[]) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("job");
            }
        });
    }
}
