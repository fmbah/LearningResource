package more.thread.test.设计模式;

/**
 * @ClassName D5单例模式使用static代码块
 * @Description
 *              参照: https://blog.csdn.net/berber78/article/details/46472789
 * @Author root
 * @Date 18-11-10 上午10:33
 * @Version 1.0
 **/
public class D5单例模式使用static代码块 {

    Class[] classArray = {MyObject5.class};

    public static void main (String args[]) {
//        System.out.println(MyObject5.getInstance());
//        System.out.println(MyObject5.getInstance());

        System.out.println("Hello World");
    }

}

class MyObject5 {
    private static MyObject5 myObject5 = null;//静态成员变量可由类名直接.出来,是由于在进行class装载入jvm的时候,静态成员变量随着加载了
    private MyObject5 () {}

    //初始化
//    当一个类被主动使用时，Java虚拟就会对其初始化，如下六种情况为主动使用：
//
//    当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）
//    当调用某个类的静态方法时
//            当使用某个类或接口的静态字段时
//    当调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时
//            当初始化某个子类时
//    当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类）
//                Java编译器会收集所有的类变量初始化语句和类型的静态初始化器，将这些放到一个特殊的方法中：clinit。 

    //静态块的执行发生在类的初始化阶段
    static {
        System.out.println("我是什么时候执行的");
        myObject5 = new MyObject5();
    }

    public static MyObject5 getInstance () {
        return myObject5;
    }
}