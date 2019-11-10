package more.thread.example;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolTest
 * @Description
 * @Author root
 * @Date 19-3-11 下午3:36
 * @Version 1.0
 **/
public class ThreadPoolTest {
	public static void main(String[] args) {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), r -> {
//			Thread thread = new Thread(r);
//			thread.setName("自定义线程名称" + r.hashCode());
//			return thread;
//		}, new ThreadPoolExecutor.AbortPolicy());
//
//		for (int i = 0; i< 10; i++) {
//			executor.execute(() -> {
//				try {
//					System.out.println("正在执行: " + Thread.currentThread().getName());
//					Thread.sleep(1000);
//
//					System.out.println(executor.getPoolSize());
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			});
//		}
//
//		System.out.println(executor.getPoolSize());
//
//		executor.shutdown();


//		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10, r -> {
//			Thread thread = new Thread(r);
//			thread.setName("自定义沦寻线程池");
//			return thread;
//		}, new ThreadPoolExecutor.DiscardPolicy());
//		scheduledThreadPoolExecutor.schedule(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("我在10S后触发执行");
//			}
//		}, 10, TimeUnit.SECONDS);
//
//
//		// 核心线程池数量为5, (最大容量为10, 加上队列中的5的)即为15,允许最大有15个任务,如果在处理期间超过15个任务,就会报错
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), new ThreadFactory() {
//
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread thread = new Thread(r);
//				thread.setName("test_thread_" + r.hashCode());
//				return thread;
//			}
//		/// }, new ThreadPoolExecutor.AbortPolicy());
//		}, new ThreadPoolExecutor.DiscardPolicy());
//
//		for (int i = 0; i < 16; i++) {
//			MyTask myTask = new MyTask(i);
//			executor.execute(myTask);
//			System.out.println("线程池中的数量: " + executor.getPoolSize() + ", 队列中等待执行的任务数量: " + executor.getQueue().size());
//		}
//
//		executor.shutdown();

		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//		singleThreadExecutor.execute(() -> {
//			System.out.println("thread sleep 2s");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		singleThreadExecutor.shutdown();

		Future<?> future = singleThreadExecutor.submit(() -> {
			try {
				Thread.sleep(4000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "1";
		});

		try {
			Object o = future.get();


			System.out.println(o);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		singleThreadExecutor.shutdown();
	}

	private static class MyTask implements Runnable {
		private int num;
		public MyTask(int num) {
			this.num = num;
		}
		@Override
		public void run() {
			try {
				System.out.println("正在执行的任务: " + num);
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test1() {
//		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//		cachedThreadPool.submit(() -> {
//			try {
//				System.out.println("休眠1.5s________1111");
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		cachedThreadPool.execute(() -> {
//			try {
//				System.out.println("休眠1.5s________2222");
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		cachedThreadPool.shutdown();

//		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
//		fixedThreadPool.execute(() -> {
//			try {
//				System.out.println("休眠1.5s________3333");
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		});
//		fixedThreadPool.shutdown();
//
//
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		scheduledThreadPool.schedule(() -> {
			System.out.println("1s后执行一次");
		}, 1, TimeUnit.SECONDS);
		scheduledThreadPool.shutdown();

		// 当线程数 < 核心线程数 创建线程
		// 当线程数 >= 核心线程数 且任务队列未满, 将任务放入任务队列中
		// 当线程数 >= 核心线程数, 且任务队列已满
			// 如果线程数 < 最大线程数, 创建线程
			// 如果线程数 = 最大线程数, 则进行异常处理
		// 看下面的创建则是 4 + 1024是执行中+等待中的最大任务处理数,超过就会进行异常处理
		// 参数设置依据胰以下三个参数
		// 1. 每秒任务数 tasks 如: 100 - 300
		// 2. 处理一个任务耗时 taskcost 如: 0.1s
		// 3. 系统荣热最大响应时间 responsetime 如: 1s =====> 理解成队列里的任务可以待的最长时间
		// corePoolSize = tasks / (responsetime / taskcost) = (100 ~ 300) / (1 / 0.1) = 10 ~ 30; 根据8020原则, 则30 * %20 = 6即可
		// capacity = corePoolSize * (responsetime / taskcost) = 6 * (1 / 0.1) = 60; 1s1个线程处理10个任务===>1s24个线程处理60个任务,
		// 如果使用默认创建线程池的方式,则为Integer.MAX_VALUE, 那么就会出现corePoolSize不会增加, 任务增加,响应时间也会增加
		// maximumPoolSize = (max(task) - capacity) / (responsetime / taskcost) = = (300 - 60) / 10 = 24

		// cat /proc/cpuinfo 查看cpu数量
		// top -d 1 / top
		// f设置显示列/c显示隐藏列值/
		//
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("自定义线程");
				return thread;
			}
		}, new ThreadPoolExecutor.AbortPolicy());
		// 允许核心线程在空闲时进行回收
		threadPoolExecutor.allowCoreThreadTimeOut(true);
		Future<?> submit = threadPoolExecutor.submit(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		while (!submit.isDone()) {

		}

		System.out.println("线程池任务执行完成");

		threadPoolExecutor.shutdown();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
}


