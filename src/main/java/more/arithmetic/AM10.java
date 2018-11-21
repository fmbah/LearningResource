package more.arithmetic;

/**
 * @ClassName AM10
 * @Description
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @Author root
 * @Date 18-11-15 上午9:07
 * @Version 1.0
 **/
public class AM10 {
    public static void main (String args[]) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 0));
    }

    /**
     * 个人解法: 执行用时: 3 ms, 在Search Insert Position的Java提交中击败了100.00% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-15 上午9:23
     */
    public static int searchInsert(int[] nums, int target) {

        //已排序不重复数组,查找元素
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > target) {
                    break;
                }
                index++;
            }
        }

        return index;
    }
}
