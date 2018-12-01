package more.arithmetic;

/**
 * @ClassName AM1
 * @Description
 * @Author root
 * @Date 18-11-3 上午10:18
 * @Version 1.0
 **/
public class AM1 {


    /**
     *
     * 功能描述: 给定一个 32 位有符号整数，将整数中的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * 注意:
     *
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2 ^ 31,  2 ^ 31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-3 上午10:18
     */
    public static void main (String args[]) {

        System.out.println(reverse(1234567899));

    }

    public static int reverse(int x) {
        int result = 0;

        for (; x != 0;) {
            int z = x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && z > 7)) return 0;//当结果大于Integer最大值 或 result等于Integer最大值的整除10的结果并且个位数字大于Integer最大值的各位数字,那就证明溢出喽
            if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && z < -8)) return 0;//当结果小于Integer最小值 或 result等于Integer最小值的整除10的结果并且个位数字小于Integer最小值的个位数字,则证明溢出
            result = result * 10 + z;//如果此方程式导致数字溢出,那么会推出来上面两个比较式
        }

        return result;
    }


    /**
     *
     * 功能描述: 判断数字是否溢出
     *
     *  数字范围: -2^31 ~ 2^31-1
     *
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-27 上午10:00
     */
    public static boolean biggerMaxOrMin (int x) {


        return false;
    }
}
