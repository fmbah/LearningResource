package more.thread.example.java异步转同步;

/**
 * @ClassName Demo1
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:21
 * @Version 1.0
 **/
public class Demo1 extends BaseDemo{

	private Object lock = new Object();

	@Override
	public void callBack(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		synchronized (lock) {
			lock.notifyAll();
		}

	}

	public static void main(String[] args) {

		Demo1 demo1 = new Demo1();

		demo1.call();

		// 此段代码 实现了异步转同步的关键代码
		// 如果是异步的话, 则会直接输出'主线程内容'
		// 但是加上了唤醒机制后, 则是等待线程执行完成后, 唤醒了休眠中的线程,最后输出'主线程内容'
		synchronized (demo1.lock) {
			try {
				demo1.lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("主线程内容");
	}
}
