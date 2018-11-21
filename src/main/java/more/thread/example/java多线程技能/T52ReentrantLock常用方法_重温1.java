package more.thread.example.java多线程技能;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T52ReentrantLock常用方法_重温1
 * @Description
 *
 * 2018-11-20   synchronized与wait
 *              lock与await
 *              这两组配合使用的使用的话,锁会自动释放,会让线程进入等待队列中去....
 *
 *
 * @Author root
 * @Date 18-11-20 下午1:44
 * @Version 1.0
 **/
public class T52ReentrantLock常用方法_重温1 {

    private static class MyTest50Service {
//        private ReentrantLock lock = new ReentrantLock();
//        private Condition condition = lock.newCondition();

        private ReentrantLock lock;
        private Condition condition;
        public MyTest50Service (ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        public void methodA () {
            long s = System.currentTimeMillis();
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ",我操,在获取时间之前就已经进来了....");
                System.out.println(Thread.currentThread().getName() + ",进来methodA方法");
                Thread.sleep(3000);
//                condition.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + ",lock 锁住的数量: " + lock.getHoldCount() + ", 共耗时:"+ (System.currentTimeMillis() - s) / 1000 +"s");
                lock.unlock();
            }
        }

        public void methodB (Object o) {
            try {
                long s = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + ",呦,您来嘞");
                synchronized (o) {
                    o.wait(3000);
                }
                System.out.println("共耗时" + (System.currentTimeMillis() - s) / 1000 + "s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String args[]) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        final MyTest50Service myTest50Service = new MyTest50Service(lock, condition);
        final Object o = "1";

//        for (int i = 0; i < 10; i++) {
            Thread threadA = new Thread() {
                @Override
                public void run() {
                    super.run();
                    myTest50Service.methodA();
                    myTest50Service.methodB(o);
                }
            };
            threadA.setName("A");
            threadA.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("我已经到这里,但是锁未释放,....");
            Thread threadB = new Thread() {
                @Override
                public void run() {
                    super.run();
                    System.out.println("你还没开始吗?");
                    myTest50Service.methodA();
                    myTest50Service.methodB(o);
                }
            };
            threadB.setName("B");
            threadB.start();
//        }


        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正在等待线程释放锁的数量: " + lock.getQueueLength());
    }
}


