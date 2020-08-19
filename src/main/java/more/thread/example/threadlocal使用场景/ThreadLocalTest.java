package more.thread.example.threadlocal使用场景;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.TtlWrappers;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.WeakHashMap;
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

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException, NoSuchMethodException, InvocationTargetException {
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


//		final ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
//
//		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
//		threadLocalTest.transmittableThreadLocal.set(1);
//
//		try {
//
//			Runnable runnable = new Runnable() {
//				@Override
//				public void run() {
//					System.out.println("transmittableThreadLocal：" + threadLocalTest.transmittableThreadLocal.get());
//				}
//			};
//
//			executorService.submit(runnable);
//
//			TimeUnit.SECONDS.sleep(1);
//			System.out.println();
//
//			threadLocalTest.transmittableThreadLocal.set(2);
//			executorService.submit(runnable);
//
//			TimeUnit.SECONDS.sleep(1);
//
//			System.out.println();
//			threadLocalTest.transmittableThreadLocal.set(3);
//			executorService.submit(runnable);
//
//		} finally {
//			executorService.shutdown();
//		}
//
//
		InheritableThreadLocal<String> transmittableThreadLocal = new InheritableThreadLocal<>();
        transmittableThreadLocal.set("i am parent");

        System.out.println(transmittableThreadLocal.get());




		CustomInheritableRunnable runnable = new CustomInheritableRunnable() {
			@Override
			public void runTask() {
				System.out.println(transmittableThreadLocal.get());
			}
		};

//        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);

		ExecutorService pool = null;
        try {
			pool = Executors.newFixedThreadPool(1);
			pool.submit(runnable);


			TimeUnit.SECONDS.sleep(4);
			transmittableThreadLocal.set("22222");


			pool.submit(new CustomInheritableRunnable() {
				@Override
				public void runTask() {
					System.out.println(transmittableThreadLocal.get());
				}
			});

		} finally {
			if (!Objects.isNull(pool)) {
				pool.shutdown();
			}
		}

		SaAa saAa = new SaAa();
		saAa.setA("123");

		Field a = SaAa.class.getDeclaredField("a");
		a.setAccessible(true);
		System.out.println(a.get(saAa));

		Method setA = SaAa.class.getDeclaredMethod("setA", String.class);
		setA.setAccessible(true);
		Object invoke = setA.invoke(saAa, "456");

		System.out.println(invoke + "====" + saAa.getA());

	}


	public abstract static class CustomInheritableRunnable implements Runnable {
		private Object inheritableThreadLocalsObj;
		public CustomInheritableRunnable() {
			try {
				Thread currentThread = Thread.currentThread();
				Field inheritableThreadLocals = Thread.class.getDeclaredField("inheritableThreadLocals");
				inheritableThreadLocals.setAccessible(true);
				Object inheritableThreadLocalsMap = inheritableThreadLocals.get(currentThread);
				if (inheritableThreadLocalsMap != null) {
					Class<?> threadLocalMapClazz = inheritableThreadLocals.getType();
					Method createInheritedMap = ThreadLocal.class.getDeclaredMethod("createInheritedMap", threadLocalMapClazz);
					createInheritedMap.setAccessible(true);
					Object newThreadLocalMap = createInheritedMap.invoke(ThreadLocal.class, inheritableThreadLocalsMap);
					inheritableThreadLocalsObj = newThreadLocalMap;
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} finally {
			}
		}

		// 执行业务方法
		public abstract void runTask();

		@Override
		public void run() {
			Thread currentThread = Thread.currentThread();
			Field inheritableThreadLocals = null;
			try {
				inheritableThreadLocals = Thread.class.getDeclaredField("inheritableThreadLocals");
				inheritableThreadLocals.setAccessible(true);
				if (null != inheritableThreadLocalsObj) {
					inheritableThreadLocals.set(currentThread, inheritableThreadLocalsObj);
					inheritableThreadLocalsObj = null;
				}
				runTask();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} finally {
				if (null != inheritableThreadLocals) {
					try {
						inheritableThreadLocals.set(currentThread, null);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}


		}
	}

}
