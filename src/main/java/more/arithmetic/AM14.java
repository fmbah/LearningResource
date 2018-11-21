package more.arithmetic;

import java.util.TreeMap;

/**
 * @ClassName AM14
 * @Description
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * @Author root
 * @Date 18-11-20 上午9:36
 * @Version 1.0
 **/
public class AM14 {

    /**
     * 个人解法: 执行用时: 13 ms, 在Plus One的Java提交中击败了0.94% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-20 下午1:07
     */
    public static void main (String args[]) {
//        int[] nums = {6,1,4,5,3,9,0,1,9,5,1,8,6,7,0,5,5,4,3};
//        int[] nums = {1, 2, 3};
        int[] nums = {9, 9, 9};
        plusOne(nums);
    }

    public static int[] plusOne(int[] digits) {


        TreeMap<Integer, Integer> maps = new TreeMap<>();
        for (Integer i = 0, j = digits.length; i < j; i++) {
            maps.put(i, digits[i]);
        }

        dataProcess(maps, digits.length - 1);

        int[] result = new int[maps.size()];
        if (maps.size() > digits.length) {
            for (Integer i : maps.keySet()) {
                result[i + 1] = maps.get(i);
                System.out.print(maps.get(i));
            }
        } else {
            for (Integer i : maps.keySet()) {
                result[i] = maps.get(i);
                System.out.print(maps.get(i));
            }
        }

        return result;
    }

    public static void dataProcess (TreeMap<Integer, Integer> maps, Integer index) {
        Integer num = maps.get(index);
        num = num + 1;

        if (num == 10) {
            maps.put(index, 0);
            if (index == 0) {
                maps.put(-1, 1);
            } else {
                dataProcess(maps, --index);
            }
        } else {
            maps.put(index, num);
        }
    }
}
