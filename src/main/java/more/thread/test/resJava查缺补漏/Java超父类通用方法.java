package more.thread.test.resJava查缺补漏;

import org.junit.Test;

/**
 * @ClassName Java超父类通用方法
 * @Description
     * public native int hashCode()
     *
     * public boolean equals(Object obj)
     *
     * protected native Object clone() throws CloneNotSupportedException
     *
     * public String toString()
     *
     * public final native Class<?> getClass()
     *
     * protected void finalize() throws Throwable {}
     *
     * public final native void notify()
     *
     * public final native void notifyAll()
     *
     * public final native void wait(long timeout) throws InterruptedException
     *
     * public final void wait(long timeout, int nanos) throws InterruptedException
     *
     * public final void wait() throws InterruptedException
 * @Author root
 * @Date 18-10-24 上午11:56
 * @Version 1.0
 **/
public class Java超父类通用方法 {

    /**
     *
     * 功能描述: 演示重写将一个对象的equals和hashCode方法重写后的情况
     *          等价的对象散列值一定相同；散列相同的不一定等价
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-24 下午1:47
     */
    @Test
    public void test() throws CloneNotSupportedException {
        EqualExample equalExample = new EqualExample(2, 3);
        EqualExample equalExample1 = new EqualExample(2, 3);

        //自反性 x.equals(x)
        //对称性 x.equals(y) y.equals(x)
        //传递性 if (x.equals(y) && y.equals(z)) ==> x.equals(z)
        //一致性 x.equals(x) == x.equals(x)
        //与null的比较 x.equals(null) 都为false
        System.out.println(equalExample.equals(equalExample1));

        //@ 后面的数值为散列码的无符号十六进制表示
        System.out.println(equalExample.toString() + "==" + equalExample1.toString());

        //对象克隆
        CloneExample cloneExample = new CloneExample();
        cloneExample.setA("999");
        cloneExample.setIndexValue(1, 2);
        CloneExample clone = null;
        clone = cloneExample.clone();
        cloneExample.setIndexValue(1, 3);//数组在内存中存储是一个地址,浅拷贝的时候拷贝的是引用地址,在操作一个引用地址里的值的时候,所以会出现改变一个数组的值,引用该地址的同样会改变
        System.out.println(clone.getIndex(1) == cloneExample.getIndex(1));
        System.out.println("克隆实例是否相同:" + (clone == cloneExample) + ", cloneExample.a:" + cloneExample.getA() + ", clone.a:" + clone.getA() + ",是否为浅拷贝:" + (clone.getA() == cloneExample.getA()));


        CloneExample1 cloneExample1 = new CloneExample1();
        cloneExample1.setA("深拷贝克隆");
        CloneExample1 clone1 = cloneExample1.clone();
        System.out.println("是否深拷贝:" + (clone1.getA() == cloneExample1.getA()));
    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 下午1:39
 * @Description: 重写equals方法
 */
class EqualExample {
    private int x;
    private int y;
    public EqualExample(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {//是否同一个对象引用,是则直接返回true
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {//检验类型是否一致,不一致则返回false
            return false;
        }
        EqualExample equalExample = (EqualExample)obj;//转化类型后,逐个属性比较
        if (equalExample.x != this.x) {
            return false;
        }
        return equalExample.y == this.y;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 下午6:13
 * @Description: 浅拷贝演示:将引用地址拷贝过去,拷贝变量更改,不会影响原对象值(此处需要注意,需要属性是一个数组/集合,那么情况另说)
 */
class CloneExample implements Cloneable{
    private String a;

    private int[] arr;

    public CloneExample() {
        this.arr = new int[10];
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setIndexValue (int index, int value) {
        arr[index] = value;
    }

    public int getIndex(int index) {
        return this.arr[index];
    }

    @Override
    protected CloneExample clone() throws CloneNotSupportedException {
        return (CloneExample)super.clone();
    }
}

/**
 * @Auther: Fmbah
 * @Date: 18-10-24 下午6:13
 * @Description: 深拷贝演示,对应里的值都需要重新申请空间存放
 */
class CloneExample1 implements Cloneable{
    private String a;

    public CloneExample1() {
        a = new String();
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    protected CloneExample1 clone() throws CloneNotSupportedException {
        CloneExample1 clone = (CloneExample1) super.clone();
        String strA = new String(this.getA());
        clone.setA(strA);
        return clone;
    }
}