package more.arithmetic;

/**
 * @ClassName AM21
 * @Description
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * @Author root
 * @Date 18-12-12 上午9:33
 * @Version 1.0
 **/
public class AM21 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode root_left0 = new TreeNode(1);
        TreeNode root_right0 = new TreeNode(2);
        root.left = root_left0;
        root.right = root_right0;

        TreeNode root_left0left0 = new TreeNode(3);
        TreeNode root_left0right0 = new TreeNode(4);
        root_left0.left = root_left0left0;
        root_left0.right = root_left0right0;

        TreeNode root_right0right0 = new TreeNode(5);
        root_right0.right = root_right0right0;

        root.toString();
    }

    public boolean isSymmetric(TreeNode root) {
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            visit(this);
            return super.toString();
        }

        public void visit(TreeNode node) {
            if (node.left != null) {
                visit(node.left);
            }
            if (node.right != null) {
                visit(node.right);
            }
            System.out.println(node.val);
        }
    }
}
