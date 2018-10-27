package more.thread.example.java多线程技能;

/**
 * @ClassName T5暂停线程
 * @Description suspend()方法暂停线程
 *              resume()方法恢复线程的执行
 *
 *              缺点:独占
 *                  数据不同步
 *
 * @Author root
 * @Date 18-10-24 下午8:55
 * @Version 1.0
 **/
public class T5暂停线程 {


//    /**
//     *
//     * 功能描述: 启动一个线程,用来计算器增加,然后主线程休眠5s(给子线程时间),暂停子线程,查看线程对象的计数器,在主线程中休眠5s,二次查看子线程计数器值,
//     *          两次比较后,线程确实被暂停了!
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-25 上午9:20
//     */
//    public static void main (String args[]) {
//        MyThread5 myThread5 = new MyThread5();
//        myThread5.start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        myThread5.suspend();
//        System.out.println("A=" + System.currentTimeMillis() + ", i=" + myThread5.getI());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("A=" + System.currentTimeMillis() + ", i=" + myThread5.getI());
//
//
//        myThread5.resume();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        myThread5.suspend();
//        System.out.println("B=" + System.currentTimeMillis() + ", i=" + myThread5.getI());
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("B=" + System.currentTimeMillis() + ", i=" + myThread5.getI());
//    }


//    /**
//     *
//     * 功能描述: 演示线程暂停后导致独占问题:公共的同步对象独占,使得其它线程无法访问公共同步对象
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-25 上午9:31
//     */
//    public static void main (String args[]) {
//        try {
//            //此处供匿名线程方法使用,需要声明为final,否则会提示Variable 'myThread5_synchronizedObject' is accessed from within inner class, needs to be declared final
//            //java中规定，内部类只能访问外部类中的成员变量，不能访问方法中定义的变量，如果要访问方法中的变量，就要把方法中的变量声明为final（常量）的，
//            // 因为这样可以使变量全局化，就相当于是在外部定义的而不是在方法里定义的
//            final MyThread5_SynchronizedObject myThread5_synchronizedObject = new MyThread5_SynchronizedObject();
//
//            Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    myThread5_synchronizedObject.printString();
//                }
//            };
//            thread.setName("a");
//            thread.start();
//            Thread.sleep(1000);
//
//            Thread thread1 = new Thread(){
//                @Override
//                public void run() {
//                    System.out.println("thread1启动了!但进入不了printString方法!只打印了一个begin");
//                    System.out.println("因为printString方法被a线程锁定并且永远暂停了!");
//                    myThread5_synchronizedObject.printString();
//                }
//            };
//            thread1.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     *
//     * 功能描述: 演示子线程被暂停后,打印出了main end字样后,JVM进制依然停止状态,无法正常关闭
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-25 上午9:56
//     */
//    public static void main (String args[]) {
//        MyThread5 myThread5 = new MyThread5();
//        myThread5.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        myThread5.suspend();
//        System.out.println("main end");
//    }

//    /**
//     *
//     * 功能描述: 演示System.out.println()方法是同步方法,当子线程打印的时候方法被锁住且暂停时,
//     *          其它线程无法在访问println方法,所以导致main end没有被打印出来
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-25 上午10:02
//     */
//    public static void main (String args[]) {
//        MyThread5_1 myThread5_1 = new MyThread5_1();
//        myThread5_1.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        myThread5_1.suspend();
//        System.out.println("main end");
//    }

    /**
     *
     * 功能描述: 此方法演示了当线程暂停后,出现数据不同步的情况
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-25 上午10:12
     */
    public static void main (String args[]) {
        final MyThread5_DATANOTSYNCHRONIZED myThread5_datanotsynchronized = new MyThread5_DATANOTSYNCHRONIZED();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                myThread5_datanotsynchronized.setValue("thread_au", "thread_ap");
            }
        };
        thread.setName("a");
        thread.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                myThread5_datanotsynchronized.printUsernamePassword();
            }
        }.start();

    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-25 上午9:24
 * @Description: 演示线程计数器自增
 */
class MyThread5 extends Thread {
    private long i;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-25 上午10:05
 * @Description: 演示线程计数器自增,并打印
 */
class MyThread5_1 extends Thread {
    private long i;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-25 上午9:29
 * @Description:
 */
class MyThread5_SynchronizedObject {

    synchronized public void printString() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a 线程永远 suspend 了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

}


/**
 * @Auther: Fmbah
 * @Date: 18-10-25 上午10:09
 * @Description: 演示暂停线程后,导致数据不一致的情况
 */
class MyThread5_DATANOTSYNCHRONIZED {

    private String username = "fmbah_u";
    private String password = "fmbah_p";

    public void setValue(String username, String password) {
        this.username = username;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("停止了a线程");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }

    public void printUsernamePassword() {
        System.out.println(username + "==" + password);
    }
}

