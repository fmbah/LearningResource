package more.thread.example.java多线程技能;

/**
 * @ClassName T3
 * @Description 线程基础方法: getId(), sleep(), isAlive(), currentThread()
 * @Author root
 * @Date 18-10-23 下午6:25
 * @Version 1.0
 **/
public class T3 {

    public static void main (String args[]) {
        Thread mainThread = Thread.currentThread();//返回代码段正在被哪个线程调用的信息
        System.out.println(mainThread.getName() + ", 线程的唯一标识:" + mainThread.getId() + ", 是否处于活跃状态:" + mainThread.isAlive());
        T3_MyThread t3_myThread = new T3_MyThread();
        t3_myThread.start();
        try {
            Thread.sleep(1000);//此段代码:是为了让t3_myThread线程执行完成,演示t3_myThread.isAlive()为false的情况,如果当前代码注释掉,则会出现为true的情况,因为子线程还未停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t3_myThread.isAlive());
    }

}

class T3_MyThread extends Thread {
    public T3_MyThread() {
        System.out.println("T3_MyThread: " + Thread.currentThread().getName() + "===" + this.getName());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run: " + Thread.currentThread().getName() + "===" + this.getName());
    }
}

