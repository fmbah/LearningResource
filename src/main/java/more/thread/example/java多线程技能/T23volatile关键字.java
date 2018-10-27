package more.thread.example.java多线程技能;

/**
 * @ClassName T23volatile关键字
 * @Description volatile关键字主要作用是使变量在多个线程间可见
 * @Author root
 * @Date 18-10-27 下午1:41
 * @Version 1.0
 **/
public class T23volatile关键字 {

//    /**
//     *
//     * 功能描述: 此方法演示:main线程一直在处理死循环
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-27 下午1:51
//     */
//    public static void main(String args[]) {
//        PrintString23 printString23 = new PrintString23();
//        printString23.printStringMethod();
//        System.out.println("我要停止它!, stopThreadName: " + Thread.currentThread().getName());
//        printString23.setIsContinuePrint(false);
//    }

//    /**
//     *
//     * 功能描述:  线程间共同操作一个对象时,可能会出现脏读的情况,避免这种情况使用volatile修饰符
//     *
//     * @param:
//     * @return:
//     * @auther: Fmbah
//     * @date: 18-10-27 下午2:02
//     */
//    public static void main(String args[]) {
//        PrintString23_1 printString23_1 = new PrintString23_1();
//        new Thread(printString23_1).start();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("我要停止它!, stopThreadName: " + Thread.currentThread().getName());
//        printString23_1.setIsContinuePrint(false);
//
////        run printStringMehod threadName: Thread-0
////        run printStringMehod threadName: Thread-0
////        我要停止它!, stopThreadName: main
//    }

    public static void main(String args[]) {
        RunThread23 runThread23 = new RunThread23();
        new Thread(runThread23).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runThread23.setStop(true);
        System.out.println("设置为停止循环了!!!");

    }

}

class PrintString23 {
    private boolean isContinuePrint = true;
    public boolean isContinuePrint() {
        return isContinuePrint;
    }
    public void setIsContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }
    public void printStringMethod() {
        while (this.isContinuePrint()) {
            System.out.println("run printStringMehod threadName: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class PrintString23_1 implements Runnable {
    volatile private boolean isContinuePrint = true;
    public boolean isContinuePrint() {
        return isContinuePrint;
    }
    public void setIsContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }
    public void printStringMethod() {
        while (isContinuePrint) {
            System.out.println("run printStringMehod threadName: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        printStringMethod();
    }
}

class RunThread23 implements Runnable {
    volatile private boolean isStop = false;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public void run() {
        System.out.println("RunThread23 run start time:" + System.currentTimeMillis());
        while(!isStop()) {

        }
        System.out.println("RunThread23 run stop time:" + System.currentTimeMillis());
    }
}