package more.thread.test.设计模式;

/**
 * @ClassName D1单例模式饿汉模式
 * @Description
 * @Author root
 * @Date 18-11-9 下午3:45
 * @Version 1.0
 **/
public class D1单例模式饿汉模式 {

    /**
     * @Auther: Fmbah
     * @Date: 18-11-9 下午3:50
     * @Description: 立即加载实例, 提前占用了系统资源,
     *               由于类加载的时候已经初始化变量,所以不会有线程不安全的情况
     */
    private static class MyObject {
        private static MyObject myObject = new MyObject();
        private MyObject () {}

        public static MyObject getInstance() {
            return myObject;
        }
    }

    public static void main (String args[]) {
//        MyObject myObject = MyObject.getInstance();
//        MyObject myObject1 = MyObject.getInstance();
//        System.out.println(myObject == myObject1);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance());
            }
        };

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(runnable);

            threads[i].start();
        }
    }

}


