package more.thread.test.resJava查缺补漏;

import org.junit.Test;

/**
 * @ClassName Java封装继承多态
 * @Description
 *
 *
 * 同一个类中的成员                 private     default     protected       public
 *                                  Y           Y             Y             Y
 * 同一个包中的成员                               Y             Y             Y
 *
 * 不同包中但存在继承关系的子类成员                               Y             Y
 *
 * 全局                                                                     Y
 *
 * java中不存在全局变量的意义:所以在编译器的时候,可以在类中 public type final static var1 的形式保存一个类似全局变量的属性
 *
 * @Author root
 * @Date 18-10-24 上午11:15
 * @Version 1.0
 **/
public class Java封装继承多态 {

    @Test
    public void test() {
        AbstractExtendsClass abstractExtendsClass = new AbstractExtendsClass();
        System.out.println(abstractExtendsClass.i + "==" + abstractExtendsClass.i2 + "==" + AbstractClass.i3);
        abstractExtendsClass.fun1();
        abstractExtendsClass.fun1(AbstractClass.i3);
    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 上午11:38
 * @Description: is-A 相关类需要必须复用的代码块
 */
abstract class AbstractClass {
    protected final int i = 9;
    private final int i1 = 99;
    final int i2 = 999;
    public static int i3 = 9999;
}
class AbstractExtendsClass extends AbstractClass implements InterfaceClass {

    public void fun1() {
        System.out.println("重写 父类方法: fun1");
    }
    public void fun1(Integer i){
        System.out.println("重载 方法: fun1" + i);
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 上午11:42
 * @Description: like-A 相关类不需要必须复用的代码块
 */
interface InterfaceClass {
    void fun1();
}
