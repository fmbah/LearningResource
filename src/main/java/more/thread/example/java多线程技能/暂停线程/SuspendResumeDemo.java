package more.thread.example.java多线程技能.暂停线程;

public class SuspendResumeDemo extends Thread{

    public static void main (String args[]) {

        SuspendResumeDemo thread = new SuspendResumeDemo();
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.suspend();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.resume();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        System.out.println("呦，运行500ms, 暂停500ms, 恢复运行500ms， 暂停500ms, 停止运行");



    }


    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(i);
            if (this.isInterrupted()) {
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
