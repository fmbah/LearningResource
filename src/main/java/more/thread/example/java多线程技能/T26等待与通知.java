package more.thread.example.java多线程技能;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T26等待与通知
 * @Description
 * @Author root
 * @Date 18-10-30 下午3:04
 * @Version 1.0
 **/
public class T26等待与通知 {

    public static void main(String args[]) {

        final List<Integer> list = new ArrayList<Integer>();

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println("add success!" + list.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {

                        Thread.sleep(1000);
                        System.out.println("get success!" + list.size());

                        throw new InterruptedException("size is 5, break!!!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
