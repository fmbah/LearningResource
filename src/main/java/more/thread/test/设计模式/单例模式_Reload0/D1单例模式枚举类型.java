package more.thread.test.设计模式.单例模式_Reload0;

/**
 * @ClassName D1单例模式枚举类型
 * @Description
 * @Author root
 * @Date 18-11-29 下午2:28
 * @Version 1.0
 **/
public class D1单例模式枚举类型 {
    public static void main(String[] args) {
        SingletonEnumDemo instance = SingletonEnumDemo.getInstance();
        System.out.println(instance);
        SingletonEnumDemo instance1 = SingletonEnumDemo.getInstance();
        System.out.println(instance1);
    }
}

class SingletonEnumDemo {
    private SingletonEnumDemo(){}
    enum InnerEnum {
        singleton;
        private SingletonEnumDemo singletonEnumDemo;
        InnerEnum() {
            singletonEnumDemo = new SingletonEnumDemo();
        }
        public SingletonEnumDemo isSingle () {
            return this.singletonEnumDemo;
        }
    }
    public static SingletonEnumDemo getInstance() {
        return InnerEnum.singleton.isSingle();
    }
}
