package more.thread.example.java多线程技能;

/**
 * @ClassName T37join的使用
 * @Description join在内部使用wait方法进行等待,
 *              synchronized使用对象监视器原理做为同步
 * @Author root
 * @Date 18-11-5 上午9:31
 * @Version 1.0
 **/
public class T37join的使用 {

    public static void main(String args[]) {
        MyThread37 myThread37 = new MyThread37();
        myThread37.start();
//        Thread.sleep(?);
        try {
//            while (isAlive()) {
//                wait(0);
//            }
            myThread37.join();//内部实现是判断当前线程是否处于活跃状态,如果处于活跃状态,则一直等待,直到线程状态已停止,那么就退出等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("我想在MyThread37执行后,执行,但是我应该休眠多久呢");
        System.out.println("答案是:不确定");
    }

}

class MyThread37 extends Thread {
    @Override
    public void run() {
        super.run();
        int v = (int) (Math.random() * 10000);
        System.out.println("run: " + v);
        try {
            Thread.sleep(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}