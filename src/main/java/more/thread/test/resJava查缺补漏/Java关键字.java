package more.thread.test.resJava查缺补漏;

import org.junit.Test;

/**
 * @ClassName Java关键字
 * @Description
 *  final:  声明数据为常量,可以是编译时常量,也可以是运行时被初始化后不能改变的常量
 *          声明方法则子类不可重写
 *          声明类则不可被继承
 *  static: 静态变量:内存中只保存一份,类实例共享此变量
 *          静态方法:非抽象方法,不可子类重写
 *          静态导入包:影响可读性
 *          静态内部类:
 *          静态语句块:类初始化的时候执行一次
 *          初始化顺序:父类(静态变量/语句块)->子类(静态变量/语句块)->父类(实例变量/普通语句块)->父类(构造函数)->子类(实例变量/普通语句块)->子类(构造方法)
 *
 * @Author root
 * @Date 18-10-24 下午6:17
 * @Version 1.0
 **/
public class Java关键字 {

    public final Integer i = 2;

    @Test
    public void test() {
//        i = 4;//Cannot assign a value to final variable 'i'
        final int is;
        is = 7;
//        is = 9;//Variable 'is' might already have been assigned to
        this.setIs(is);
        System.out.println(is);
        ExampleBean exampleBean = new ExampleBean();
        exampleBean.setStr("setBeanBefore");
        this.setBean(exampleBean);
        System.out.println(exampleBean.getStr());

        new Thread(new Thread2()).start();
        new Thread(new Thread1()).start();
        System.out.println(new ExampleStatic().b);

        System.out.println(new ExampleStatic.InnerExampleStatic().a);
    }

    public void setIs(int is) {
        is = 8;
        System.out.println("setIs :" + is);
    }
    public void setBean(ExampleBean exampleBean) {
        exampleBean.setStr("setBeanAfter");
    }

}

final class ExampleBean {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    final public void helloWorld() {
        System.out.println("ExampleBean helloWorld");
    }
}


class ExampleExtendsBean
//        extends ExampleBean
{//Cannot inherit from final 'more.thread.test.resJava查缺补漏.ExampleBean'
    //子类无法重写父类中由final修饰符修饰的方法
}


class Thread1 extends Thread {
    @Override
    public void run() {
        super.run();
        ExampleStatic.b = 5;
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        super.run();
        ExampleStatic.b = 2;
    }
}

class ExampleStatic {
    private int a;//实例变量 随着实例的生命周期结束
    public static int b;//静态变量 类所有实例都共享此变量,内存中只存在一份,上面我用两个线程分别操作,可发现其实操作的都是同一份变量

//    public static void hiStatic();//Missing method body, or declare abstract

    public static void hiStatic() {
//        System.out.println(this.a);//'more.thread.test.resJava查缺补漏.ExampleStatic.this' cannot be referenced from a static context
    }

    static class InnerExampleStatic {//静态内部类不依靠外部类即可使用
        public int a = 7;
    }
}

