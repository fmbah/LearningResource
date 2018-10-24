package more.thread.test.resJava查缺补漏;

import org.junit.Test;

/**
 * @ClassName Java基础类型
 * @Description 基础类型
 * @Author root
 * @Date 18-10-24 上午10:03
 * @Version 1.0
 **/
public class Java基础类型 {

    /**
     *
     * 功能描述: 缓冲池
     *          拆箱:封装对象->基础对象
     *          装箱:基础对象->封装对象
     *
     *          同样适用其它基础对象与封装对象
     *          boolean values true and false
     *          all byte values
     *          short values between -128 and 127
     *          int values between -128 and 127
     *          char in the range \u0000 to \u007F
     *
     *          参考此处:https://stackoverflow.com/questions/9030817/differences-between-new-integer123-integer-valueof123-and-just-123
     *
     *          switch 不支持long类型
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 上午10:03
     */
    @Test
    public void test() {

        //此方法要记住一个问题:缓冲池相应类型的默认大小!!!  如果你的值超过了缓冲池的默认大小,则会新创建对象,那么下面的比较就是不正确了

        Integer integer0 = new Integer(1);//先new一个Integer对象 然后创建引用integer0 然后将引用地址连接起来
        Integer integer1 = new Integer(1);//先new一个Integer对象 然后创建引用integer1 然后将引用地址连接起来
        Integer integer2 = Integer.valueOf(1);//从缓存池中取出1, 创建引用integer2 值与引用链接起来
        Integer integer3 = Integer.valueOf(1);//从缓存池中取出1, 创建引用integer3 值与引用链接起来
        Integer integer4 = integer0;
        Integer integer5 = 1;

        System.out.println((integer0 == integer1) + "===" + (integer0.compareTo(integer1)));//引用地址不同 值相同 false
        System.out.println((integer0 == integer4) + "===" + (integer0.compareTo(integer4)));//引用地址相同 值相同 true
        System.out.println((integer0 == integer2) + "===" + (integer0.compareTo(integer2)));//引用地址不同 对象值相同false
        System.out.println(integer2 == integer3);//引用地址相同 对象值相同true
        System.out.println(integer2 == integer5);//引用地址相同 对象值相同true
    }

    /**
     *
     * 功能描述: String: final不可继承,不可变(线程安全, stringpool, 缓存hash值)
     *          StringBuffer 可变,线程安全,内部synchronized实现
     *          StringBuilder 可变,线程不安全
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 上午10:20
     */
    @Test
    public void test1() {
        String a = new String("a");
        String b = new String("a");
        System.out.println(a == b);
        String c = a.intern();//获取字符串并存储到stringpool中,将pool中的引用返回来给c
        String d = b.intern();//获取字符串并存储到stringpool中,将pool中的引用返回来给c
        System.out.println(c == d);

        String oldStr = "A";
        System.out.println("before:" + oldStr);
        change(oldStr);
        System.out.println("after:" + oldStr);
    }

    /**
     *
     * 功能描述: 传值? 传引用?
     *
     *  https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 上午10:47
     */
    public void change(Object str) {
        System.out.println("change method before:" + str);
        str = "AAAA";
        System.out.println("change method before:" + str);
    }

    /**
     *
     * 功能描述: 隐式类型转换(+= -= *= /=)
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 上午11:08
     */
    @Test
    public void test2() {
        short s = 1;
        s = (short)(s + 1);
        s += 1;//隐式类型转换

        int i = 0;
        i = i + s;//向上转是可以的,因为向下转会有精度损失
        long l = 1;
//        i = i + l;//这就是不可以的
        i += l;//隐式类型转换 i = (int)(i + l)
    }


}
