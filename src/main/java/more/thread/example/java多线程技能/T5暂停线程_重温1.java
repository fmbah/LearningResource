package more.thread.example.java多线程技能;

/**
 * @ClassName T5暂停线程_重温1
 * @Description
 * @Author root
 * @Date 18-11-13 下午2:18
 * @Version 1.0
 **/
public class T5暂停线程_重温1 {
    public static void main (String args[]) {
        Thread thread = new Thread() {
            int num = 0;
            @Override
            public void run() {
                super.run();
                try {
                    while (num < 100000) {
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException("has stop!");
                        }
                        System.out.println(num);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.suspend();//暂停

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.resume();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("here????");
        thread.interrupt();
    }
}
