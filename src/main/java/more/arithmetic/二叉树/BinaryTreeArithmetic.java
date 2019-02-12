package more.arithmetic.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BinaryTreeArithmetic
 * @Description 二叉树常见算法
 * @Author root
 * @Date 19-1-9 下午5:25
 * @Version 1.0
 **/
public class BinaryTreeArithmetic {

    /**
     *
     * 功能描述:
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
     * @date: 19-1-10 下午5:48
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
//        levelTraversal(root);
        System.out.println(getNodeNumRec1(root));
    }

    /**
     *
     * 功能描述: 层次遍历
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 下午5:27
     */
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();//队列保存树节点(先进先出)
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            System.out.println(tmp.val + "->");
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
    }

    /**
     * 递归解法
     * 功能描述: 获取二叉树节点数
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-10 下午5:50
     */
    public static int getNodeNumRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
    }

    public static int getNodeNumRec1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                count++;
            }
            if (node.right != null) {
                queue.add(node.right);
                count++;
            }
        }
        return count;
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
