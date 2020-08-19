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
1. 数据结构：线程->entryMap<K, V>（K:threadlocal，V:Object）
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
0. 数据结构：继承自ThreadLocal，重写了getMap和createMap方法，get set 都是操作线程的inheritableThreadLocals变量
1. 使用场景：子线程需要获取父线程的私有变量值
````
    父线程设置InheritableThreadLocal值
    -> 创建子线程（构造方法会判定父类inheritableThreadLocal不为空则把值复制到子线程的InheritableThreadLocal中）
    -> 子线程中使用InheritableThreadLocal get 即可得到父线程设置的值
    
````
2. 可能出现的问题：
````
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.inheritableThreadLocal.set(1);

        try {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("inheritableThreadLocal：" + threadLocalTest.inheritableThreadLocal.get());
                }
            };

            executorService.submit(runnable);

            TimeUnit.SECONDS.sleep(1);
            System.out.println();

            threadLocalTest.inheritableThreadLocal.set(2);
            executorService.submit(runnable);

            TimeUnit.SECONDS.sleep(1);

            System.out.println();
            threadLocalTest.inheritableThreadLocal.set(3);
            executorService.submit(runnable);

        } finally {
            executorService.shutdown();
        }

        inheritableThreadLocal：1

        inheritableThreadLocal：1

        inheritableThreadLocal：1
        
        线程池复用线程导致的悲剧...
````
3. 解决方案：TransmittableThreadLocal
````
        final ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.transmittableThreadLocal.set(1);

		try {

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					System.out.println("transmittableThreadLocal：" + threadLocalTest.transmittableThreadLocal.get());
				}
			};

			executorService.submit(runnable);

			TimeUnit.SECONDS.sleep(1);
			System.out.println();

			threadLocalTest.transmittableThreadLocal.set(2);
			executorService.submit(runnable);

			TimeUnit.SECONDS.sleep(1);

			System.out.println();
			threadLocalTest.transmittableThreadLocal.set(3);
			executorService.submit(runnable);

		} finally {
			executorService.shutdown();
		}
		
		transmittableThreadLocal：1
        
        transmittableThreadLocal：2
        
        transmittableThreadLocal：3
        
        
        
        另类解决方式：反射塞值
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
````