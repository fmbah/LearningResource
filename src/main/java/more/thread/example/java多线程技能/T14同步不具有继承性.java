package more.thread.example.java多线程技能;

/**
 * @ClassName T14同步不具有继承性
 * @Description
 * @Author root
 * @Date 18-10-26 上午11:04
 * @Version 1.0
 **/
public class T14同步不具有继承性 {
    public static void main (String args[]) {
        final My14SubExample my14SubExample = new My14SubExample();
        new Thread() {
            @Override
            public void run() {
                super.run();
                my14SubExample.printNotSynchronized();
            }
        }.start();
        my14SubExample.printNotSynchronized();
    }
}

class My14Example {
    synchronized public void printNotSynchronized() {
        System.out.println("My14Example, " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("My14Example, " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
    }
}
class My14SubExample extends My14Example {
    @Override
    public void printNotSynchronized() {
        System.out.println("My14SubExample, " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("My14SubExample, " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
    }
}