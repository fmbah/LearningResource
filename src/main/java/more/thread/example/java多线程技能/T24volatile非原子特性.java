package more.thread.example.java多线程技能;

/**
 * @ClassName T24volatile非原子特性
 * @Description 对于一个volatile修饰符来说,i++并非是原子性的,因为在其中可以分解为三个步骤:
 *                  1:从内存中取得这个值
 *                  2:计算i的值
 *                  3:将i的值写入内存中
 *              而这三个位置都是可能会出现多线程同时操作的情况,那么则会出现脏读的情况!!!
 *              我们对此的解决方式,还是在类上加synchronized限制
 * @Author root
 * @Date 18-10-29 上午10:39
 * @Version 1.0
 **/
public class T24volatile非原子特性 {

    public static void main (String args[]) {
//        MyThread24 myThread24 = new MyThread24();
//        myThread24.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(MyThread24.getCount());


        MyThread24[] myThread24s = new MyThread24[100];
        for (int i = 0; i < 100; i++) {
            myThread24s[i] = new MyThread24();
        }

        for (int i = 0; i < 100; i++) {
            myThread24s[i].start();
        }
    }


}

class MyThread24 extends Thread {
        private static int count = 0;
    synchronized public static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + "===>" + count);
    }

    @Override
    public void run() {
        super.run();
        addCount();
    }
}
