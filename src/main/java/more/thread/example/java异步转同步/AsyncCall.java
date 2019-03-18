package more.thread.example.java异步转同步;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName AsyncCall
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:05
 * @Version 1.0
 **/
public class AsyncCall {

	private SecureRandom secureRandom = new SecureRandom();

	private ExecutorService executorsService = Executors.newSingleThreadExecutor();

	public void call (BaseDemo baseDemo) {
		new Thread(() -> {
			long res = secureRandom.nextInt(10);
			try {
				Thread.sleep(res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			baseDemo.callBack(res);
		}).start();
	}

	public Future<?> futureCall() {
		Future<?> submit = executorsService.submit(() -> {
			long res = secureRandom.nextInt(10);
			try {
				Thread.sleep(res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return res;
		});

		return submit;
	}

	public void shutdown() {
		executorsService.shutdown();
	}
}
