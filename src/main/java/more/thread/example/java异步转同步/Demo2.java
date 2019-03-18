package more.thread.example.java异步转同步;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Demo2
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:33
 * @Version 1.0
 **/
public class Demo2 extends BaseDemo{

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	@Override
	public void callBack(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		try {
			lock.lock();

			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		Demo2 demo2 = new Demo2();

		demo2.call();

//		demo2.lock.lock();
//		try {
//			demo2.condition.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			demo2.lock.unlock();
//		}
		//发起调用
		//调用返回
		//得到结果
		//5
		//调用结束
		//主线程内容

		//发起调用
		//调用返回
		//主线程内容
		//得到结果
		//7
		//调用结束

		System.out.println("主线程内容");
	}


}
