package more.thread.example.java多线程技能.生产者与消费者;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OPOC
 * @Description N生产一消费
 * @Author root
 * @Date 19-2-15 下午3:26
 * @Version 1.0
 **/
public class NPOC {
    private volatile List<Integer> data = new ArrayList<>();
    private volatile int num = 0;

    public void p() {

        synchronized (this) {
            if (data.size() > 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data.add(num++);
            this.notifyAll();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void c() {
        for(;;) {
            synchronized (this) {
                if (!data.isEmpty()) {
                    System.out.println(data.remove(0));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.notifyAll();
                } else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NPOC opoc = new NPOC();
        new Thread(()->{
            opoc.c();
        }).start();

        new Thread(()->{
            while (true) {
                opoc.p();
            }
        }).start();
        new Thread(()->{
            while (true) {
                opoc.p();
            }
        }).start();

    }

}
