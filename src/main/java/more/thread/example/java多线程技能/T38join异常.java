package more.thread.example.java多线程技能;

/**
 * @ClassName T38join异常
 * @Description
 * @Author root
 * @Date 18-11-5 上午10:38
 * @Version 1.0
 **/
public class T38join异常 {

    /**
     *
     * 功能描述: join和interrupt方法遇到的时候,会出现异常
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-5 上午11:43
     */
    public static void main(String args[]) {
        try {
            ThreadB38 threadB38 = new ThreadB38();
            threadB38.start();
            Thread.sleep(500);
            ThreadC38 threadC38 = new ThreadC38(threadB38);
            threadC38.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadA38 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newString = new String();
            Math.random();
        }
    }
}

class ThreadB38 extends Thread {
    @Override
    public void run() {
        super.run();
        ThreadA38 threadA38 = new ThreadA38();
        threadA38.start();
        try {
            threadA38.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程B在run end处打印了");
    }
}

class ThreadC38 extends Thread {
    private ThreadB38 threadB38;
    public ThreadC38 (ThreadB38 threadB38) {
        this.threadB38 = threadB38;
    }

    @Override
    public void run() {
        super.run();
        threadB38.interrupt();
    }
}
