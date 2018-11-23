package more.arithmetic;

/**
 * @ClassName AM16
 * @Description
 *
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * @Author root
 * @Date 18-11-22 上午9:32
 * @Version 1.0
 **/
public class AM16 {

    public static void main (String args[]) {
//        System.out.println(mySqrt(2));
//        System.out.println(NewtonSqrt(4, 1e-7));
        System.out.println(DichotomySqrt(4, 1e-7));
    }

    /**
     * 个人解法:执行用时: 43 ms, 在Sqrt(x)的Java提交中击败了61.99% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-22 上午9:41
     */
    public static int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    /**
     * 以下解法参照: https://blog.csdn.net/qq_17776287/article/details/53762265
     *
     * 功能描述: 先确定当前数所处的最小整数区间，然后再通过二分法来进行判断检测
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-22 上午9:46
     */
    public static float sqrtRoot(float m) {

        if ( m < 0 ) {
            throw new RuntimeException("Negetive number cannot have a sqrt root.");
        }

        if ( m == 0 ) {
            return 0;
        }

        float i = 0;
        float x1, x2 = 0;
        while ( ( i * i ) <= m ) {
            i += 0.1;
        }
        x1 = i;
        for (int j = 0; j < 10; j++) {
            x2 = m;
            x2 /= x1;
            x2 += x1;
            x2 /= 2;
            x1 = x2;
        }
        return x2;
    }


    /**
     * 以下解法参照: https://www.jianshu.com/p/dcd73888ac3a
     * 功能描述: 牛顿迭代法
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-22 上午10:02
     */
    public static double NewtonSqrt(double number, double accuracy) {
        //第一个猜测值
        double guess = number / 2;
        int count = 0;
        if (number < 0) {
            return Double.NaN;
        }
        //当两个猜测的差值大于精度即return
        while (Math.abs(guess - (number / guess)) > accuracy) {
            //迭代公式推导而成
            guess = (guess + (number / guess)) / 2;
            count++;
            System.out.printf("try count = %d, guess = %f\n", count, guess);
        }
        System.out.printf("final result = %f\n", guess);
        return guess;
    }

    /**
     * 以下解法参照: https://www.jianshu.com/p/dcd73888ac3a
     * 功能描述: 二分法
     *
     * 这个位置不要干想,你TM也不是genius!
     *
     * 画坐标轴,
     *
     * 比如取4的平方根,精度为7个0
     *
     * ---0---1---2---3---4-----------
     *
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-22 上午10:23
     */
    public static double DichotomySqrt(double number, double accuracy) {
        double higher = number;
        double lower = 0.0;
        double middle = (lower + higher) / 2;
        double last_middle = 0.00;
        int count = 0;
        if (number < 0) {
            return Double.NaN;
        }
        while (Math.abs(middle - last_middle) > accuracy) {
            if (middle * middle > number) {
                higher = middle;
            } else {
                lower = middle;
            }
            last_middle = middle;
            middle = (lower + higher) / 2;
            count++;
            System.out.printf("Dichotomy try count = %d, guess = %f\n", count, last_middle);
        }
        System.out.printf("Dichotomy final result = %f\n", last_middle);
        return last_middle;
    }

}
