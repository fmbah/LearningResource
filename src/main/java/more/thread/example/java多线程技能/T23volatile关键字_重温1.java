package more.thread.example.java多线程技能;

/**
 * @ClassName T23volatile关键字_重温1
 * @Description
 *
 *              volatile 关键字
 *                  1. 有序性
 *                          //线程1:
 *                          context = loadContext();   //语句1
 *                          inited = true;             //语句2
 *
 *                          //线程2:
 *                          while(!inited ){
 *                              sleep()
 *                          }
 *                          doSomethingwithconfig(context);//语句3
 *                      如果上面的inited参数未使用volatile修饰符号,那么上述语句1 和 语句2的执行顺序不可确定,也就意味着,如果语句2先执行了,可能会直接执行语句3导致报错(因为context对象还没有被正确赋值)
 *                      如果上面的inited参数使用了volatile修饰符号,那么当执行到了语句2的时候,语句1一定是执行完成的,也就不存在未正确初始化就被使用的问题
 *                  2.  可见性, 线程间对共享变量的操作是可见的!
 *                          线程1对共享变量1写操作会将其它线程中的缓存对象失效,CPU对象的L1 L2等缓存区域
 *                          强制线程重新从主存中加载数据
 *                  3.  非原子性
 *                              线程对变量的操作:  1 从主存读取到线程的工作内存中
 *                                              2   在工作内存中操作变量(写操作)
 *                                              3   将工作内存中的数据写回到主存中
 *                              如果线程1 读取a = 10后,阻塞了,
 *                              由于线程1只是进行了读取的操作,线程2也读取了a = 10,然后进行了a自增1操作,写入了工作内存并最后写会到主存中
 *                              此时,线程1对a进行自增1操作,由于工作内存中的a=10,所以直接进行加1操作,最后也是将11写入工作内存中
 *                              这样就出现了,两个线程增加了两次,但是最终结果依然只显示增加了1次的数值
 *
 *                              解决非原子性问题   1 由synchronized修饰
 *                                               2 Lock lock = new ReentrantLock()
 *                                               3. AtomicInteger类
 *
 *
 * @Author root
 * @Date 18-11-14 下午1:42
 * @Version 1.0
 **/
public class T23volatile关键字_重温1 {

    private static class MyServiceT23 {
        private volatile boolean flag = true;//volatile修饰符使变量的变化在线程中可见

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void method () {
            System.out.println("开始循环");
            while (this.isFlag()) {

            }
            System.out.println("停止循环");
        }

        private volatile int count = 0;

        public synchronized void methodB () {
            count++;
        }

    }

    public static void main(String args[]) {
        final MyServiceT23 myServiceT23 = new MyServiceT23();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                myServiceT23.method();
            }
        };

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myServiceT23.setFlag(false);
        System.out.println("已经设置了,停止循环的变量!");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    myServiceT23.methodB();
                }
            }
        };
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myServiceT23.count);
    }

}
