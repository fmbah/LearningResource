package more.arithmetic;

/**
 * @ClassName AM12
 * @Description
 *
 * 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * @Author root
 * @Date 18-11-16 上午10:33
 * @Version 1.0
 **/
public class AM12 {
    public static void main (String args[]) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     *
     * 功能描述: 下面的这个是其它人提出的解决方式,真6
     *          连续子数组,无非就是两两相加取最大,但是你还需要有个新的数组记录上次相加的值,这样才能知道如何新算子数组的头
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-19 下午4:44
     */
    public static int maxSubArray(int[] nums) {
        int[] maxValue = new int[nums.length];
        int res = nums[0];
        maxValue[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxValue[i] = ((maxValue[i-1] + nums[i]) > nums[i] ? (maxValue[i-1] + nums[i]) : nums[i]);
            if (maxValue[i] > res){
                res = maxValue[i];
            }
        }
        for (int i = 0; i < maxValue.length; i++) {
            System.out.print("{" + maxValue[i] + "}");
        }
        System.out.println();
        return res;
    }
}
