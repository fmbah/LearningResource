package more.thread.example.java多线程技能;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName T64TimerTask的Cancel方法
 * @Description
 *              TimerTask.cancel()方法 是将自身从任务队列中清除
 *              Timer.cancel()方法  是清空队列中的所有任务(请注意,如果任务正在执行,不会将正在执行的任务停下来,任务会正常执行完成后,在将任务队列清空)
 * @Author root
 * @Date 18-11-9 下午2:26
 * @Version 1.0
 **/
public class T64TimerTask的Cancel方法 {
    private static Timer timer = new Timer();

    private static class MyTimerTask64 extends TimerTask {

        @Override
        public void run() {
            System.out.println("定时器MyTimerTask64运行了, 时间:" + new Date());
        }
    }

    private static class MyTimerTask64_1 extends TimerTask {

        @Override
        public void run() {
            System.out.println("定时器MyTimerTask64_1运行了, 时间:" + new Date());
        }
    }

    public static void main (String args[]) {
        MyTimerTask64 myTimerTask64 = new MyTimerTask64();
        MyTimerTask64_1 myTimerTask64_1 = new MyTimerTask64_1();

        timer.schedule(myTimerTask64, new Date(), 1000);
        timer.schedule(myTimerTask64_1, new Date(), 1000);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        boolean cancel = myTimerTask64.cancel();
//        System.out.println(cancel);

        timer.cancel();
    }

}

