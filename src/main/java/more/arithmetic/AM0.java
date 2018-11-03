package more.arithmetic;

import java.util.*;

/**
 * @ClassName AM0
 * @Description
 * @Author root
 * @Date 18-11-2 上午9:15
 * @Version 1.0
 **/
public class AM0 {

    /**
     *
     * 功能描述: 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     *          你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     *          示例:
     *
     *          给定 nums = [2, 7, 11, 15], target = 9
     *
     *          因为 nums[0] + nums[1] = 2 + 7 = 9
     *          所以返回 [0, 1]
     *
     *          参考:https://leetcode-cn.com/problems/two-sum/
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-2 上午9:17
     */
    public static void main (String args[]) {
        int[] nums = {-3,4,3,90};
        Integer target = 0;

        twoSum(nums, target);
    }



//    public static int[] twoSum(int[] nums, int target) {
//        Integer t1, t2;
//        List<String> result = new ArrayList<String>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int x = i+1; x < nums.length; x ++) {
//                if (((t1 = nums[i]) + (t2 = nums[x])) == target) {
//                    return new int[]{i, x};
//                }
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");
//    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-2 上午10:00
     */
//    public static int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if (map.containsKey(complement) && map.get(complement) != i) {
//                return new int[] { i, map.get(complement) };
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");
//    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
