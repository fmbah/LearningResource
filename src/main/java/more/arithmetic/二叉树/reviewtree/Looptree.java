package more.arithmetic.二叉树.reviewtree;

import java.util.Stack;

/**
 * @ClassName Looptree
 * @Description
 * @Author root
 * @Date 19-1-10 上午11:36
 * @Version 1.0
 **/
public class Looptree {

    /**
     *
     * 功能描述: 后序循环二叉树
     *
     *       1
     *     /  \
     *    2    3
     *   / \  / \
     *  4  5 6   7
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-10 上午11:46
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        backlooptree(root);
    }

    /**
     *
     * 功能描述: 双栈法,后序递归二叉树
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-10 下午1:40
     */
    public static void backlooptree(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            TreeNode tmp = stack1.pop();
            stack2.add(tmp);
            if (tmp.left != null) {
                stack1.add(tmp.left);
            }
            if (tmp.right != null) {
                stack1.add(tmp.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + "->");
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
