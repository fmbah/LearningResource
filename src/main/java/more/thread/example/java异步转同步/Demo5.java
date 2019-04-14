package more.thread.example.java异步转同步;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Demo5
 * @Description
 * @Author root
 * @Date 19-3-15 上午11:03
 * @Version 1.0
 **/
public class Demo5 extends BaseDemo{

	// 初始值为2 主线程/异步调用的线程 不宜过多会产生死锁
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


	@Override
	public void callBack(long response) {

		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Demo5 demo5 = new Demo5();

		demo5.call();

		try {
			demo5.cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		System.out.println("主线程内容");
	}
}
