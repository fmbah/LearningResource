package java虚拟机.精度损失;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author fb
 * @title: TestAccuracyLoss
 * @projectName LearningResource
 * @description: 精度损失
 * @date 2019/10/289:48
 */
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


        System.out.println(validScale(0.9560D, 6));

        BigDecimal t1 = new BigDecimal(0.96);
        BigDecimal t2 = new BigDecimal(0.4);
        System.out.println(t1.divide(t2, 2, BigDecimal.ROUND_UP));

    }

    public static BigDecimal validScale(double v1, int scale){
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(String.valueOf(v1));
        BigDecimal divisor = BigDecimal.ONE;
        MathContext mc = new MathContext(scale);
        return b.divide(divisor, mc);
    }

}
