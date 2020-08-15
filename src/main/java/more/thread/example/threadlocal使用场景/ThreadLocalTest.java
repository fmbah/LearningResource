package more.thread.example.threadlocal使用场景;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocalTest
 * @Description
 * @Author root
 * @Date 19-3-15 上午9:38
 * @Version 1.0
 **/
public class ThreadLocalTest {

	private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	private InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();
	private TransmittableThreadLocal<Integer> transmittableThreadLocal = new TransmittableThreadLocal<>();

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
//		threadLocalTest.threadLocal.set(1);
//
//		threadLocalTest.threadLocal = null;
//
//		System.gc();
//
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		Thread t = Thread.currentThread();
//		Class<? extends Thread> clz = t.getClass();
//		Field field = clz.getDeclaredField("threadLocals");
//		field.setAccessible(true);
//		Object threadLocalMap = field.get(t);
//		Class<?> tlmClass = threadLocalMap.getClass();
//		Field tableField = tlmClass.getDeclaredField("table");
//		tableField.setAccessible(true);
//		Object[] arr = (Object[]) tableField.get(threadLocalMap);
//		for (Object o : arr) {
//			if (o != null) {
//				Class<?> entryClass = o.getClass();
//				Field valueField = entryClass.getDeclaredField("value");
//				Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
//				valueField.setAccessible(true);
//				referenceField.setAccessible(true);
//				System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
//			}
//		}

//		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
//		threadLocalTest.threadLocal.set(1);
//
//		threadLocalTest.inheritableThreadLocal.set(2);
//
//		Thread thread = new Thread(() -> {
//			System.out.println("threadlocal：" + threadLocalTest.threadLocal.get());
//			System.out.println("inheritableThreadLocal：" + threadLocalTest.inheritableThreadLocal.get());
//		});
//		thread.start();





	}
	static ExecutorService executorService = Executors.newFixedThreadPool(2);

}
