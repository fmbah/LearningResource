package more.thread.example.java分享20190805;


import com.mongodb.util.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        List<Integer> taskList = new ArrayList<>();
        taskList.add(1);
        taskList.add(2);
        taskList.add(3);

        List<CompletableFuture<Integer>> futureList = taskList.stream().map(t ->
                CompletableFuture.supplyAsync(() -> {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    return t + 1;
                }, executorService)
                        //结果转换
//                        .thenApply(i-> i + 1)
//                        .exceptionally(e->{
//                            e.printStackTrace();
//                            return 0;
//                        })
                        .whenComplete((r, e)-> System.out.println(r))
        ).collect(Collectors.toList());


        //同步等待
//        List<Integer> resultList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

//        resultList.forEach(a-> System.out.print(a));


    }


    @Test
    public void testCompletableFuture() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }
}
