package more.arithmetic;

/**
 * @ClassName AM26
 * @Description
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * @Author root
 * @Date 19-1-7 上午9:47
 * @Version 1.0
 **/
public class AM26 {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(3);
//        root.left.left.left = new TreeNode(4);
//        root.left.left.right = new TreeNode(4);

//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);

        //[1,2,2,3,null,null,3,4,null,null,4]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(4);

        System.out.println(isBalanced(root));
        System.out.println(isBalanced1(root));
    }

    /**
     * 功能描述: 这个解法有问题
     *
     * @param:
     * @return: 
     * @auther: Fmbah
     * @date: 19-1-7 上午9:52
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getNodeHeight(root.left) - getNodeHeight((root.right))) > 1) {
            return false;
        }
        return true;
    }

    public static boolean isBalanced1(TreeNode root) {
        return getNodeHeight1(root) != -1;
    }

    public static int getNodeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(getNodeHeight(node.left), getNodeHeight(node.right));
    }

    public static int getNodeHeight1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getNodeHeight1(node.left);
        if (left == -1) {
            return -1;
        }
        int right = getNodeHeight1(node.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
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
