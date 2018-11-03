package more.thread.example.java多线程技能;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T35一生产与多消费操作栈
 * @Description
 * @Author root
 * @Date 18-11-3 下午1:39
 * @Version 1.0
 **/
public class T35生产与消费操作栈 {

    /**
     *
     * 功能描述: 生产者与消费者
     *          生产数组数量不超过1,消费者就开始进行消费
     *           由 1生产1消费
     *              1生产多消费
     *              多生产1消费
     *              多生产多消费
     *
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-3 下午2:47
     */
    public static void main (String args[]) {
        T35MyStack t35MyStack = new T35MyStack();
        P35 p35 = new P35(t35MyStack);
        C35 c35 = new C35(t35MyStack);
        P35_Thread p35_thread = new P35_Thread(p35);
        P35_Thread p35_thread_1 = new P35_Thread(p35);
        P35_Thread p35_thread_2 = new P35_Thread(p35);
        C35_Thread c35_thread = new C35_Thread(c35);
        C35_Thread c35_thread_1 = new C35_Thread(c35);
        C35_Thread c35_thread_2 = new C35_Thread(c35);
        C35_Thread c35_thread_3 = new C35_Thread(c35);
        C35_Thread c35_thread_4 = new C35_Thread(c35);


        p35_thread.start();
        p35_thread_1.start();
        p35_thread_2.start();
        c35_thread.start();
        c35_thread_1.start();
        c35_thread_2.start();
        c35_thread_3.start();
        c35_thread_4.start();
    }

}

class T35MyStack {
    private List list = new ArrayList();

    synchronized public void push () {
        try {

            while (list.size() == 1) {//由if转为while,避免当多个线程处于休眠状态的时候,同时被唤醒了多个,那么就会共同减少数组元素,导致越界错误
                System.out.println("push 休眠了");
                this.wait();
            }

            list.add(Math.random());
            this.notifyAll();//由notify方法转为notifyAll方法,主要是将随机唤醒线程的机制改为将休眠中的线程全部唤醒,这样避免了随机唤醒了同类线程,导致程序进入假死状态
            System.out.println("push over, " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop () {
        String returnValue = "";
        try {

            while (list.size() == 0) {
                System.out.println("pop操作中的:" + Thread.currentThread().getName() + ", 线程呈等待状态");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

class P35 {
    private T35MyStack t35MyStack;
    public P35(T35MyStack t35MyStack) {
        this.t35MyStack = t35MyStack;
    }

    public void pushService() {
        while (true) {
            t35MyStack.push();
        }
    }
}

class P35_Thread extends Thread {
    private P35 p35;
    public P35_Thread (P35 p35) {
        this.p35 = p35;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            p35.pushService();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class C35 {
    private T35MyStack t35MyStack;
    public C35 (T35MyStack t35MyStack) {
        this.t35MyStack = t35MyStack;
    }

    public void popService () {
        System.out.println("pop = " + t35MyStack.pop());
    }
}

class C35_Thread extends Thread {
    private C35 c35;
    public C35_Thread (C35 c35) {
        this.c35 = c35;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            c35.popService();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
