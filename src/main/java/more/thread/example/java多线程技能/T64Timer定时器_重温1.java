package more.thread.example.java多线程技能;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName T64Timer定时器_重温1
 * @Description
 * @Author root
 * @Date 18-11-22 下午1:43
 * @Version 1.0
 **/
public class T64Timer定时器_重温1 {

    static Timer timer = new Timer();

    private static class MyTimerTaskT64 extends TimerTask {

        @Override
        public void run() {
            System.out.println("MyTimerTaskT64 执行了...." + new Date());
        }
    }

    public static void main (String args[]) {
        MyTimerTaskT64 myTimerTaskT64 = new MyTimerTaskT64();//某时间点执行一次

        MyTimerTaskT64 myTimerTaskT64_1 = new MyTimerTaskT64();//间隔某时间段后重复执行

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 1);

        timer.schedule(myTimerTaskT64, instance.getTime());
        myTimerTaskT64.cancel();

        timer.scheduleAtFixedRate(myTimerTaskT64_1, instance.getTime(), 1000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
    }

}
