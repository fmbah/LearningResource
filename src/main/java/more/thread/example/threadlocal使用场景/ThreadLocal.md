### ThreadLocal、InheritableThreadLocal、TransmittableThreadLocal
````
目前项目：校验当前用户是否登录的方式，
          过滤器MemberSessionFilter
          ->ApiFilter
          ->{从request获取cookie信息组装sessionid或者从header里获取sessionid标识}
          ->SessionManager#getUserInfo通过redis获取sessionid对应的用户对象
          ->最后把这些内容塞入threadlocal中，供当前请求线程使用
````

##### ThreadLocal核心方法
1. get
2. set
3. remove
4. 数据结构
````
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.threadLocal.set(1);

		threadLocalTest.threadLocal = null;

		System.gc();

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread t = Thread.currentThread();
		Class<? extends Thread> clz = t.getClass();
		Field field = clz.getDeclaredField("threadLocals");
		field.setAccessible(true);
		Object threadLocalMap = field.get(t);
		Class<?> tlmClass = threadLocalMap.getClass();
		Field tableField = tlmClass.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] arr = (Object[]) tableField.get(threadLocalMap);
		for (Object o : arr) {
			if (o != null) {
				Class<?> entryClass = o.getClass();
				Field valueField = entryClass.getDeclaredField("value");
				Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
				valueField.setAccessible(true);
				referenceField.setAccessible(true);
				System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
			}
		}


    当有需要新建一个线程，然后需要从thradlocal里面拿出来对象使用
    ThreadLocal threadLocal = new ThreadLocal();
    threadLocal.set(1);

    new Thread(()->{
        System.out.println("threadlocal：" + threadLocal.get());
    }).start();

````

##### InheritableThreadLocal
1. 使用场景：子线程需要获取父线程的私有变量值
````
    父线程设置InheritableThreadLocal值
    -> 创建子线程（构造方法会判定父类inheritableThreadLocal不为空则把值复制到子类的InheritableThreadLocal中）
    -> 子线程中使用InheritableThreadLocal get 即可得到父线程设置的值
````