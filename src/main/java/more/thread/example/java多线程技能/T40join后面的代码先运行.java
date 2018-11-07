package more.thread.example.java多线程技能;

/**
 * @ClassName T40join后面的代码先运行
 * @Description
 * @Author root
 * @Date 18-11-6 上午11:09
 * @Version 1.0
 **/
public class T40join后面的代码先运行 {

    public static void main(String args[]) {
        MyThread40B myThread40B = new MyThread40B();
        MyThread40A myThread40A = new MyThread40A(myThread40B);
        myThread40A.start();
        myThread40B.start();
        try {
            myThread40B.join(2000);//记住,join方法的内部实现
//            public final synchronized void join(long millis)
//                                                              throws InterruptedException {
//                long base = System.currentTimeMillis();
//                long now = 0;
//
//                if (millis < 0) {
//                    throw new IllegalArgumentException("timeout value is negative");
//                }
//
//                if (millis == 0) {
//                    while (isAlive()) {
//                        wait(0);
//                    }
//                } else {//此处是一个循环,休眠完成之后要再次轮寻判断是否继续要休眠,这是要将线程锁住的!所以会发生线程抢占情况
//                    while (isAlive()) {//判断当前线程是否活跃
//                        long delay = millis - now;//休眠时间
//                        if (delay <= 0) {
//                            break;
//                        }
//                        wait(delay);//休眠后会释放锁
//                        now = System.currentTimeMillis() - base;//休眠了多久
//                    }
//                }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end time:" + System.currentTimeMillis());


//        begin a time: 1541474411504
//        end a time: 1541474416505
//        begin b time: 1541474416505
//        end b time: 1541474421505
//        main end time:1541474421505

//        begin b time: 1541474437165
//        end b time: 1541474442165
//        begin a time: 1541474442165
//        end a time: 1541474447165
//        main end time:1541474447166

//        begin a time: 1541474462483
//        end a time: 1541474467483
//        main end time:1541474467483
//        begin b time: 1541474467483
//        end b time: 1541474472484
    }

}

class MyThread40A extends Thread {
    private MyThread40B myThread40B;
    public MyThread40A (MyThread40B myThread40B) {
        this.myThread40B = myThread40B;
    }

    @Override
    public void run() {
        super.run();
        synchronized (myThread40B) {
            System.out.println("begin a time: " + System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end a time: " + System.currentTimeMillis());
        }
    }
}

class MyThread40B extends Thread {
    @Override
    synchronized public void run() {
        super.run();
        System.out.println("begin b time: " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end b time: " + System.currentTimeMillis());
    }
}