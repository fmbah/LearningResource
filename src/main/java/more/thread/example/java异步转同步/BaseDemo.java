package more.thread.example.java异步转同步;

/**
 * @ClassName BaseDemo
 * @Description
 * @Author root
 * @Date 19-3-15 上午10:04
 * @Version 1.0
 **/
public abstract class BaseDemo {

	protected AsyncCall asyncCall = new AsyncCall();
	public abstract void callBack(long response);

	public void call () {
		System.out.println("发起调用");
		asyncCall.call(this);
		System.out.println("调用返回");
	}


}
