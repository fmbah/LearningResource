package more.thread.example.java多线程技能.生产者与消费者;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OPOC
 * @Description 一生产N消费
 * @Author root
 * @Date 19-2-15 下午3:26
 * @Version 1.0
 **/
public class OPNC {
    private volatile List<Integer> data = new ArrayList<>();

    public void p() {
        int i = 0;
        for(;;i++) {
            if (data.isEmpty()) {
                data.add(i);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void c() {
        for(;;) {
            this.processData();
        }
    }

    private void processData() {
        synchronized (this) {
            if (!data.isEmpty()) {
                System.out.println(data.remove(0));
            }
        }
    }

    public static void main(String[] args) {
        OPNC opoc = new OPNC();
        new Thread(()->{
            opoc.c();
        }).start();
        new Thread(()->{
            opoc.c();
        }).start();
        new Thread(()->{
            opoc.c();
        }).start();
        new Thread(()->{
            opoc.c();
        }).start();

        new Thread(()->{
            opoc.p();
        }).start();

    }

}
