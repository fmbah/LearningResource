#####标准事件
1. ContextRefreshedEvent
    - 当 Spring 容器处于初始化或者刷新阶段时就会触发，事实是ApplicationContext#refresh()方法被调用时，此时容器已经初始化完毕
2. ContextStartedEvent
    - 当调用 ConfigurableApplicationContext接口下的 start() 方法时触发，表示 Spring 容器启动；通常用于 Spring 容器显式关闭后的启动。
3. ContextStoppedEvent
    - 当调用 ConfigurableApplicationContext 接口下的 stop()方法时触发，表示 Spring 容器停止，此时能通过其 start()方法重启容器。
4. ContextClosedEvent
    - 当 Spring 容器调用 ApplicationContext#close() 方法时触发，此时 Spring 的 beans 都已经被销毁，并且不会重新启动和刷新。
5. RequestHandledEvent
    - 只在 Web 应用下存在，当接受到 HTTP 请求并处理后就会触发，实际传递的默认实现类 ServletRequestHandledEvent

-------

知道了 Spring 自带的事件有哪些后，我们就可以针对一些场景利用事件机制来实现需求，比如说在 Spring 启动后初始化资源，加载缓存数据到内存中等等。代码实现也很简单，如下：
````
@Component
public class InitalizeListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("Spring 容器启动  获取到 Application Context 对象 " + applicationContext);
        //TODO 初始化资源，加载缓存数据到内存
    }
}
````



--------

#####自定义事件
在了解如何侦听 Spring 事件后，我们再来看下如何实现自定义的事件发布和侦听处理。首先就要介绍 Spring 中事件机制的三类对象：

 - Event ：所需要触发的具体事件对象，通常扩展 ApplicationEvent 实现。
 - Publisher：触发事件发布的对象，Spring 提供了 ApplicationEventPublisher 对象供我们使用，使用它的publishEvent() 方法就可以发布该事件。
 - Listener：侦听事件发生的对象，也就是接受回调进行处理的地方，可以通过 实现 ApplicationListener接口，或者使用前面提到的 @EventListener注解声明为事件的侦听器。
 - 接下来就简单看下，一个自定义事件从声明到发布订阅的代码示例。
 
1. 自定义 Application Event
````
public class CustomEvent extends ApplicationEvent {
    private String data;

    public CustomEvent(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "data='" + data + '\'' +
                ", source=" + source +
                '}';
    }
}
````
2. 自定义 Publisher
````
@Component
public class CustomeEventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String message) {
        System.out.println("开始发布事件 " + message);
        applicationEventPublisher.publishEvent(new CustomEvent(this, message));
    }
}
````
3. 自定义 Listener
````
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(Thread.currentThread()+"CustomEventListener接受到自定义事件：" + event);
    }
}
````

- Spring 事件处理默认是同步的 当发布者执行了 publishEvent() 方法，默认情况下方法所在的当前线程就会阻塞，直到所有该事件相关的侦听器将事件处理完成。而这样采用单线程同步方式处理的好处主要是可以保证让事件处理与发布者处于同一个事务环境里，如果多个侦听方法涉及到数据库操作时保证了事务的存在。