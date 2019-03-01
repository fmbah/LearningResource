package more.thread.example.java多线程技能.停止线程;

/**
 * @ClassName StopThreadTest
 * @Description
 * @Author root
 * @Date 19-2-15 下午12:00
 * @Version 1.0
 **/
public class StopThreadTest {

    public static void main(String[] args) {
        RunningThread t = new RunningThread();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        System.out.println("RunningThread 是否停止: " + t.isInterrupted());
    }

}

class RunningThread extends Thread {
    @Override
    public void run() {
        int i = 0;
        try {
            for (;i<1000000;i++) {
                if (this.isInterrupted()) {
//                throw new RuntimeException("终止线程");
                    return;
                }
                System.out.println(this.getName()+"="+this.isInterrupted()+"="+i);
            }
            System.out.println("终止线程了,就不能走这里了");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}