package more.thread.test.设计模式.单例模式;

/**
 * @ClassName D2单例模式懒汉模式
 * @Description
 * @Author root
 * @Date 18-11-9 下午3:57
 * @Version 1.0
 **/
public class D2单例模式懒汉模式 {

    private static class MyObject {
        private MyObject() {}
        private static MyObject myObject;

        public static MyObject getInstance () {//3.....线程必须取得对象监视器,直接在方法上加这个监视器,导致方法效率比较低, synchronized
            if (myObject == null) {
//                try {
//                    Thread.sleep(10);//1...................增大了同步问题的几率,就是模拟了下cpu时间片被让出的情况
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (MyObject.class) {//4.....此种同步代码块的写法和public synchronized static MyObject getInstance ()一样,将所有的代码都锁定住了
                    Thread.yield();//2...................替代sleep()方法
                synchronized (MyObject.class) {//5.......锁住初始化实例的关键代码,提升了效率,但是还是会出现同步问题(多个线程进入了 if (myObject == null) {语句中, 等待锁释放了之后,还是会重新初始话数据的)
                    if (myObject == null) {//6.........重新判断对象是否已经被初始化,这样无论第一次进入了多少个线程,都会只有一个线程初始化对象,再次进入的线程也会进入判断对象是否为空
                        System.out.println("谁这么幸运? " + Thread.currentThread().getName());
                        myObject = new MyObject();
                    }
                }
//                }
            }
            return myObject;
        }
    }

    public static void main (String args[]) {
//        MyObject instance = MyObject.getInstance();
//        MyObject instance1 = MyObject.getInstance();
//        System.out.println(instance == instance1);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance());
            }
        };
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 1000; i++) {
            threads[i].start();
        }
    }

}
