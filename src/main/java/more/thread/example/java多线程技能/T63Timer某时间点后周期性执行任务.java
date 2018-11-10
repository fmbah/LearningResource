package more.thread.example.java多线程技能;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName T63Timer某时间点后周期性执行任务
 * @Description
 * @Author root
 * @Date 18-11-9 下午2:18
 * @Version 1.0
 **/
public class T63Timer某时间点后周期性执行任务 {

    private static Timer timer = new Timer();

    private static class MyTimerTask63A extends TimerTask {

        @Override
        public void run() {
            System.out.println("定时器MyTimerTask63A运行了, 时间:" + new Date());
        }
    }

    public static void main (String args[]) {
        MyTimerTask63A myTimerTask63A = new MyTimerTask63A();

        //发音: timer.sgaijiu 哈哈哈
        timer.schedule(myTimerTask63A, new Date(), 1000);//这个时间点,也是同样,如果小于当前时间,则直接执行,如果大于当前时间,则延后执行
    }

}
