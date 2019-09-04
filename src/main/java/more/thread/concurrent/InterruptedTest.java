package more.thread.concurrent;

public class InterruptedTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptedThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(thread.isInterrupted());
        thread.interrupt();
//        System.out.println(thread.isInterrupted());

    }

    public static class InterruptedThread extends Thread{

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 当interrupt遇到线程阻塞状态时  会报错误 并将interrupt重新置成false
                    // 所以在异常里要重新调用下当前线程的interrupt方法， 将当前线程停止掉
                    Thread.currentThread().interrupt();
                }

                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println(interrupted);
                if (interrupted) {
                    break;
                }
            }
        }
    }

}
