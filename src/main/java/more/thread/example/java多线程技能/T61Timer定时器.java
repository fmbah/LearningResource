package more.thread.example.java多线程技能;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName T61Timer定时器
 * @Description
 * @Author root
 * @Date 18-11-9 上午11:47
 * @Version 1.0
 **/
public class T61Timer定时器 {

    public static void main (String args[]) {

    }

}

class Run61 {
    private static Timer timer = new Timer();
    //当构造函数中为true,代表当前定时器为守护线程(类似GC线程,非守护线程完成任务,则守护线程自动关闭)
    //构造函数参数为空, 启动了一个新的线程, 源码如下
//    public Timer() {
//        this("Timer-" + serialNumber());
//    }
//    public Timer(String name) {
//        thread.setName(name);
//        thread.start();
//    }


    static public class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了, 时间为: " + new Date());
        }
    }

    public static void main (String args[]) {
        try {
            MyTask myTask = new MyTask();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = "2018-11-09 13:59:42";
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间: " + dateRef.toLocaleString() + ", 当前时间:" + new Date().toLocaleString());
            timer.schedule(myTask, dateRef);//两个参数,一个是任务体, 一个是执行的时间(如果执行时间小于当前时间,则任务会直接执行；如果执行时间大于当前时间,则任务会延后直至相等时,才会执行任务体)
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}