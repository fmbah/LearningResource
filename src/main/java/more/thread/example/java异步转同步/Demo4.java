package more.thread.example.java异步转同步;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Demo4
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:59
 * @Version 1.0
 **/
public class Demo4 extends BaseDemo{

	private CountDownLatch countDownLatch = new CountDownLatch(1);

	@Override
	public void callBack(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		countDownLatch.countDown();
	}


	public static void main(String[] args) {
		Demo4 demo4 = new Demo4();

		demo4.call();

		try {
			demo4.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("主线程内容");
	}
}
