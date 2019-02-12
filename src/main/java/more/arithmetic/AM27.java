package more.arithmetic;

/**
 * @ClassName AM27
 * @Description
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * @Author root
 * @Date 19-1-8 上午9:42
 * @Version 1.0
 **/
public class AM27 {
    public static void main(String[] args) {
        //[1,2,2,3,null,null,3,4,null,null,4]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
//        root.right.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
//        root.right.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(4);
        System.out.println(minDepth(root));
    }
    /**
     *
     * 功能描述: 第一个节点下没有子节点,我认为就是距离根节点最近的子节点
     *
     *  1
     * / \
     *2  3
     *  / \
     * 4   5
     *    / \
     *   6   7
     *      /
     *      8
     * 步骤分析
     * 1. 根节点不为空,
     * 2. 根节点左节点不为空
     * 3. 根节点右节点不为空
     * 4. 递归并比较两个节点最小值 + 1
     *
     * 1. 2 不为空
     * 2. 左节点为空,循环右节点,右节点为空返回0 + 1 =====>1
     *
     * 1. 3 不为空
     * 2. 3的左右节点不为空
     * 3. 递归并比较3节点处的左右子节点最小值 + 1
     *
     *
     *
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-8 上午9:45
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
