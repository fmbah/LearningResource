package more.thread.example.java多线程技能;

/**
 * @ClassName T19同步方法无限等待与解决
 * @Description
 * @Author root
 * @Date 18-10-27 上午10:42
 * @Version 1.0
 **/
public class T19同步方法无限等待与解决 {

    public static void main (String args[]) {
        final Service19 service19 = new Service19();
        System.out.println(service19.object1.toString() + "==" + service19.object2.toString());
        Thread thread_a = new Thread() {
            @Override
            public void run() {
                super.run();
                service19.methodA();
            }
        };
        Thread thread_b = new Thread() {
            @Override
            public void run() {
                super.run();
                service19.methodB();
            }
        };
        thread_a.start();
        thread_b.start();
    }

}

class Service19 {
    Object object1 = new Object();
    public void methodA() {
        synchronized (object1) {
            System.out.println("methodA start...");
            boolean hasPass = true;
            while (hasPass) {

            }
            System.out.println("methodA end...");
        }
    }

    Object object2 = new Object();
    public void methodB() {
        synchronized (object2) {
            System.out.println("methodB start...");
            System.out.println("methodB end...");
        }
    }
}