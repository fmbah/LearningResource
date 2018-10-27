package more.thread.example.java多线程技能;

/**
 * @ClassName T21内置类与同步
 * @Description
 * @Author root
 * @Date 18-10-27 上午11:28
 * @Version 1.0
 **/
public class T21内置类与同步 {

    public static void main (String args[]) {
        final OutClass.innerClass1 innerClass1 = new OutClass.innerClass1();
        final OutClass.innerClass2 innerClass2 = new OutClass.innerClass2();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                innerClass1.method1(innerClass2);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                innerClass1.method2();
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                innerClass2.method1();
            }
        });
        thread3.start();//想要获取自己类的对象锁,但是由于可能被前一个线程占用了,所以要一直等待
        thread1.start();//占用了类innerClass2锁,导致thread3需要等待thread1释放锁之后,才能正常执行
        thread2.start();//对象锁,可以理解为当前类中方法上加锁的方法都需要等待.
    }

}

class OutClass {
    static class innerClass1 {
        public void method1(innerClass2 innerClass2) {
            String threadName = Thread.currentThread().getName();
            synchronized (innerClass2) {
                System.out.println(threadName + " 进入innerClass1中的method1方法了!");
                for (int i = 0; i < 10; i++) {
                    System.out.println("innerClass1 --> method1, i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(threadName + " 离开innerClass1中的method1方法了!");
            }
        }
        synchronized public void method2() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 进入innerClass1中的method2方法了!");
            for (int i = 0; i < 10; i++) {
                System.out.println("innerClass1 --> method2, i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(threadName + " 离开innerClass1中的method2方法了!");
        }
    }
    static class innerClass2 {
        synchronized public void method1() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 进入innerClass2中的method1方法了!");
            for (int i = 0; i < 10; i++) {
                System.out.println("innerClass2 --> method1, i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(threadName + " 离开innerClass2中的method1方法了!");
        }
    }
}
