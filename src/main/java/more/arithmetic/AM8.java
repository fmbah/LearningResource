package more.arithmetic;

/**
 * @ClassName AM8
 * @Description
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * @Author root
 * @Date 18-11-12 上午9:17
 * @Version 1.0
 **/
public class AM8 {
    public static void main (String args[]) {
        int nums[] = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(nums, 2));
    }

    public static int removeElement(int[] nums, int val) {

        if (nums == null || (nums != null && nums.length == 0)) {
            return 0;
        }

        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            } else {
                nums[size++] = nums[i];
            }
        }

        for (int i : nums) {
            System.out.print(i);
        }
        return size;
    }

    /**
     *
     * 功能描述: 官方解法,双指针法,当要删除的数量很少时
     * 现在考虑数组包含很少的要删除的元素的情况。
     * 例如，num=[1，2，3，5，4]，Val=4num=[1，2，3，5，4]，Val=4。
     * 之前的算法会对前四个元素做不必要的复制操作。
     * 另一个例子是 num=[4，1，2，3，5]，Val=4num=[4，1，2，3，5]，Val=4。
     * 似乎没有必要将 [1，2，3，5][1，2，3，5] 这几个元素左移一步，因为问题描述中提到元素的顺序可以更改。
     * 当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
     * 请注意，被交换的最后一个元素可能是您想要移除的值。但是不要担心，在下一次迭代中，我们仍然会检查这个元素。
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-13 下午1:46
     */
    public static int removeElement1(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

}
