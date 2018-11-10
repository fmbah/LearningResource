package more.thread.example.java多线程技能;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName T62Timer定时器顺延
 * @Description
 * @Author root
 * @Date 18-11-9 下午2:02
 * @Version 1.0
 **/
public class T62Timer定时器顺延 {

    private static Timer timer = new Timer();
    //是以一个队列的方式运行任务的,所以可能会将后面的任务延时掉,因为前一个任务,执行的时间点到了,却未释放锁,给后面的任务执行,
    // 所以后面的任务就一直在等前面的任务释放,等释放掉了的时候,后面的任务早已经过了执行时间点,在立即执行任务就是过点了


    private static class MyTimer62A extends TimerTask {

        @Override
        public void run() {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("定时器MyTimer62A运行了, 时间:" + new Date());
        }
    }

    private static class MyTimer62B extends TimerTask {

        @Override
        public void run() {
            System.out.println("定时器MyTimer62B运行了, 时间:" + new Date());
        }
    }

    public static void main (String args[]) {

        try {
            MyTimer62A myTimer62A = new MyTimer62A();
            MyTimer62B myTimer62B = new MyTimer62B();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStringA = "2018-11-09 14:14:30";
            String dateStringB = "2018-11-09 14:14:35";
            Date dateRefA = sdf.parse(dateStringA);
            Date dateRefB = sdf.parse(dateStringB);

            System.out.println("字符串dateRefA时间: " + dateRefA + ", 当前时间: " + new Date());
            System.out.println("字符串dateRefB时间: " + dateRefB + ", 当前时间: " + new Date());

            timer.schedule(myTimer62A, dateRefA);
            timer.schedule(myTimer62B, dateRefB);

//            字符串dateRefA时间: Fri Nov 09 14:14:30 CST 2018, 当前时间: Fri Nov 09 14:14:24 CST 2018
//            字符串dateRefB时间: Fri Nov 09 14:14:35 CST 2018, 当前时间: Fri Nov 09 14:14:24 CST 2018
//            定时器MyTimer62A运行了, 时间:Fri Nov 09 14:14:38 CST 2018
//            定时器MyTimer62B运行了, 时间:Fri Nov 09 14:14:38 CST 2018

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}


