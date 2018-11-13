package more.thread.example.java多线程技能;

/**
 * @ClassName T4停止线程_重温1
 * @Description
 *
 * @Author root
 * @Date 18-11-13 下午2:07
 * @Version 1.0
 **/
public class T4停止线程_重温1 {
    public static void main (String args[]) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    System.out.println(Thread.currentThread().isInterrupted());
                    while (i < Integer.MAX_VALUE) {
//                        if (Thread.currentThread().isInterrupted()) {//线程已经停止
//                            return;
//                        }
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException("has stop!");
                        }
                        Math.random();
                        i ++;
                    }
                    System.out.println("alerdy stop! should not be here");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("stop!");
    }
}
