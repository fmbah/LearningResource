package more.arithmetic;

/**
 * @ClassName AM19
 * @Description
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * @Author root
 * @Date 18-11-28 上午9:05
 * @Version 1.0
 **/
public class AM19 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums1 = {1, 0, 0, 0, 0, 0};
//        int[] nums1 = {1, 2, 3, 5, 6, 0};
//        int[] nums1 = {0, 0, 0, 0, 0};
//        int[] nums2 = {1, 2, 3, 5, 6};
//        int[] nums2 = {3};
        int[] nums2 = {2, 5, 6};
//        int[] nums2 = {1, 2, 3, 4, 5};
//        merge(nums1, 1, nums2, 5);
        merge1(nums1, 3, nums2, 3);
    }

    /**
     * 个人解答: 执行用时: 9 ms, 在Merge Sorted Array的Java提交中击败了8.59% 的用户
     * 功能描述:
     *
     * refer to: https://segmentfault.com/a/1190000013946967
     * 请看下面的 merge1 使用归并排序算法
     *
     * @param: nums1: 数组1 m: 初始化数组数量(0代表占位)
     *         nums2: 数组2 n: 初始化数组数量
     *
     * @return:
     * @auther: Fmbah
     * @date: 18-11-28 上午9:32
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        //将nums1中0替换为nums2中元素
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[--n];
        }
        //对nums1排序
        for (int i = 0, j = nums1.length; i < j; i++) {
            for (int x = i + 1; x < j; x++) {
                if (nums1[i] > nums1[x]) {
                    int tmp = nums1[i];
                    nums1[i] = nums1[x];
                    nums1[x] = tmp;
                }
            }
        }

        for (int nu : nums1) {
            System.out.println(nu);
        }
    }

    /**
     * 执行用时: 3 ms, 在Merge Sorted Array的Java提交中击败了100.00% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-28 下午3:26
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int index1 = 0;
        int index2 = 0;
        int[] old_nums1 = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            old_nums1[i] = nums1[i];
        }


        //nums1     nums2       backup
        //{1,1,0,0}  {2,4}      {1,1,0,0}
        //{5,6,0,0}     {1,2}     {5,6,0,0}
        for (int i = 0; i < nums1.length; i++) {
            if (index1 > m - 1) {//处理后半部分
                nums1[i] = nums2[index2];
                index2++;
            } else if (index2 > n - 1) {
                nums1[i] = old_nums1[index1];
                index1++;
            } else if (old_nums1[index1] < nums2[index2]) {
                nums1[i] = old_nums1[index1];
                index1++;
            } else {
                nums1[i] = nums2[index2];
                index2++;
            }
        }

        for (int nu : nums1) {
            System.out.println(nu);
        }
    }

}
