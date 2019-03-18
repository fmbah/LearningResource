package more.thread.example.java异步转同步;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName Demo3
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:43
 * @Version 1.0
 **/
public class Demo3{
	private AsyncCall asyncCall = new AsyncCall();

	public Future<?> call() {
		Future<?> future = asyncCall.futureCall();

		asyncCall.shutdown();

		return future;
	}

	public static void main(String[] args) {
		Demo3 demo3 = new Demo3();

		System.out.println("发起调用");

		Future<?> future = demo3.call();

		System.out.println("调用返回");

		while (!future.isDone() && !future.isCancelled()) {

		}

		System.out.println("得到结果");
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("调用结束");

		System.out.println("主线程内容");
	}
}
