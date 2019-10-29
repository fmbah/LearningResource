```java
public class TestAccuracyLoss {
    private static final int i0 = 100;
    private static final int i1 = 200;

    private static final long l0 = 100L;
    private static final long l1 = 200L;

    private static final float f0 = 100.221F;
    private static final float f1 = 200.442F;

    private static final double d0 = 100.221D;
    private static final double d1 = 200.442D;

    @Test
    public void test0() {
        System.out.println("int:" + (i1 - i0));
        System.out.println("BigDecimal use Int Substract:" + (new BigDecimal(i1).subtract(new BigDecimal(i0))));
        System.out.println("BigDecimal use Int String Substract:" + (new BigDecimal(i1+"").subtract(new BigDecimal(i0+""))));
        System.out.println("BigDecimal use Int Devide:" + (new BigDecimal(i1).divide(new BigDecimal(i0))));
        System.out.println("BigDecimal use Int String Devide:" + (new BigDecimal(i1+"").divide(new BigDecimal(i0+""))));

        System.out.println("long:" + (l1 - l0));
        System.out.println("BigDecimal use Long Substract:" + (new BigDecimal(l1).subtract(new BigDecimal(l0))));
        System.out.println("BigDecimal use Long String Substract:" + (new BigDecimal(l1+"").subtract(new BigDecimal(l0+""))));
        System.out.println("BigDecimal use Long Devide:" + (new BigDecimal(l1).divide(new BigDecimal(l0))));
        System.out.println("BigDecimal use Long String Devide:" + (new BigDecimal(l1+"").divide(new BigDecimal(l0+""))));

        System.out.println("float:" + (f1 - f0));
        System.out.println("损失最为严重；BigDecimal use Float Substract:" + (new BigDecimal(f1).subtract(new BigDecimal(f0))));
        System.out.println("BigDecimal use Float String Substract:" + (new BigDecimal(f1+"").subtract(new BigDecimal(f0+""))));
        System.out.println("BigDecimal use Float Devide:" + (new BigDecimal(f1).divide(new BigDecimal(f0))));
        System.out.println("BigDecimal use Float String Devide:" + (new BigDecimal(f1+"").divide(new BigDecimal(f0+""))));

        System.out.println("double:" + (d1 - d0));
        System.out.println("损失较为严重；BigDecimal use Double Substract:" + (new BigDecimal(d1).subtract(new BigDecimal(d0))));
        System.out.println("BigDecimal use Double String Substract:" + (new BigDecimal(d1+"").subtract(new BigDecimal(d0+""))));
        System.out.println("BigDecimal use Double Devide:" + (new BigDecimal(d1).divide(new BigDecimal(d0))));
        System.out.println("BigDecimal use Double String Devide:" + (new BigDecimal(d1+"").divide(new BigDecimal(d0+""))));

    }


}
```

````
int:100
BigDecimal use Int Substract:100
BigDecimal use Int String Substract:100
BigDecimal use Int Devide:2
BigDecimal use Int String Devide:2
long:100
BigDecimal use Long Substract:100
BigDecimal use Long String Substract:100
BigDecimal use Long Devide:2
BigDecimal use Long String Devide:2
float:100.221
BigDecimal use Float Substract:100.22100067138671875
BigDecimal use Float String Substract:100.221
BigDecimal use Float Devide:2
BigDecimal use Float String Devide:2
double:100.221
BigDecimal use Double Substract:100.22100000000000363797880709171295166015625
BigDecimal use Double String Substract:100.221
BigDecimal use Double Devide:2
BigDecimal use Double String Devide:2
````

### 情形
由于公司最近要求把股票相关的数据，全部交给后端来处理，不再由前端来处理。
股票大家都知道，这里面的计算都是商业级别的，小数点4+位那是再正常不过啦。
比如这样几组数字
````
2539230979.0000 //流通受限股份
8680253870 //某个股东持股数
0.4081 //某某股东所占总股数的比例
````
需求是这样的:股份单位是 万股。比例是百分之多少（%）；
所以对于股份我们需要除以10000，保留2位小数
对于比例 是要乘以100，保留2位小数。

##### 除法
````
/**
 * scale 小数点保留几位
 */
public static BigDecimal divi(double v1,double v2, int scale){
    BigDecimal b1 = new BigDecimal(String.valueOf(v1));
    BigDecimal b2 = new BigDecimal(String.valueOf(v2));
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
}
````
##### 乘法
````
public static BigDecimal muli(double v1, double v2, int scale){
    BigDecimal b1 = new BigDecimal(String.valueOf(v1));
    BigDecimal b2 = new BigDecimal(String.valueOf(v2));
    BigDecimal multiply = b1.multiply(b2);
    return multiply.setScale(scale, BigDecimal.ROUND_HALF_UP)
}
````

##### 保留有效位
````
public static BigDecimal validScale(double v1, int scale){
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(String.valueOf(v1));
        BigDecimal divisor = BigDecimal.ONE;
        MathContext mc = new MathContext(scale);
        return b.divide(divisor, mc);
    }
````

##### BigDecimal构造方法应使用String类型
````
BigDecimal t1 = new BigDecimal(0.96);
        BigDecimal t2 = new BigDecimal(0.4);
        System.out.println(t1.divide(t2));
````
java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result
原因：创建BigDecimal时，浮点型放入BigDecimal内，其存储值为
t1=0.95999999999999996447286321199499070644378662109375
t2=0.40000000000000002220446049250313080847263336181640625，两个浮点数相除时，由于除不尽，而又没有设置精度和保留小数点位数，导致抛出异常。
````
public BigDecimal(double val)
将double表示形式转换为BigDecimal

public BigDecimal(int val)
将int表示形式转换为BigDecimal

public BigDecimal(String val)
将字符串表示形式转换为BigDecimal
````
通过这三个构造函数，可以把double类型，int类型，String类型构造为BigDecimal对象，
在BigDecimal对象内通过BigIntegerintVal存储传递对象数字部分，通过int scale;记录小数点位数，
通过int precision;记录有效位数（默认为0）。

BigDecimal的加减乘除就成了BigInteger与BigInteger之间的加减乘除，浮点数的计算也转化为整形的计算，
可以大大提供性能，并且通过BigInteger可以保存大数字，从而实现真正大十进制的计算，
在整个计算过程中，还涉及scale的判断和precision判断从而确定最终输出结果。

通过上面的例子可以看出String的构造函数就是通过BigInteger记录BigDecimal的值，
使其计算变成BigInteger之间的计算。所以我们一般最好使用String类型的构造方法。

如果一定要使用double来进行计算，那么请加以确认精度后进行计算
````
BigDecimal t1 = new BigDecimal(0.96);
        BigDecimal t2 = new BigDecimal(0.4);
        System.out.println(t1.divide(t2, 2, BigDecimal.ROUND_UP));
````
