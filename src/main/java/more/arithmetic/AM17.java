package more.arithmetic;

/**
 * @ClassName AM17
 * @Description
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * [1, 1, 1]
 * [1, 2]
 * [2, 1]
 *
 * @Author root
 * @Date 18-11-23 上午9:10
 * @Version 1.0
 **/
public class AM17 {
    public static void main (String args[]) {
        System.out.println(climbStairsMy(6));
    }

    /**
     *
     * 楼梯数:走法数
     * 1:1
     * 2:2
     * 3:3
     * 4:5
     * 5:8
     * 6:13
     *
     * 从上面的描述,大概明白这个东西的原理了,如果要我做的话,就是将除了 1 和 2 楼梯数抛出,然后后面进行后一项=前两项相加和
     *
     * https://zh.wikipedia.org/wiki/%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0%E5%88%97
     *
     * 斐波那楔数列
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-23 上午9:12
     */
    public static int climbStairsMy(int n) {

        int step1 = 1;
        int step2 = 2;
        int ways = 0;
        if (n <= 1) {
            return step1;
        }
        if (n == 2) {
            return step2;
        }

        for (int i = 0, j = n - 2; i < j; i++) {
            ways = step1 + step2;
            step1 = step2;
            step2 = ways;
        }

        return ways;
    }


    public static int climbStairs(int n) {
        int dp1 = 1, dp2 = 2, dpWay = 0;
        if (n <= 1) return dp1;
        if (n == 2) return dp2;

        while ((n--) - 2 > 0) {
            dpWay = dp1 + dp2;
            dp1 = dp2;
            dp2 = dpWay;
        }

        System.out.println(dp1 + "-" + dp2);
        return dpWay;
    }

    public static int climbStairs1(int n) {
        int[] ways = {1, 1};
        for (int i = 1; i < n; i++) {
            int temp = ways[1];
            ways[1] += ways[0];
            ways[0] = temp;
        }
        return ways[1];
    }

    public static int climbStairs2(int n) {
        if (n == 1 || n ==0) return 1;
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }
}
