package more.arithmetic;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName AM22
 * @Description
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @Author root
 * @Date 18-12-13 上午9:46
 * @Version 1.0
 **/
public class AM22 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root_left0 = new TreeNode(2);
        TreeNode root_right0 = new TreeNode(2);
        root.left = root_left0;
        root.right = root_right0;

        TreeNode root_left0left0 = new TreeNode(3);
        TreeNode root_left0right0 = new TreeNode(4);
        root_left0.left = root_left0left0;
//        root_left0.right = root_left0right0;

        TreeNode root_right0right0 = new TreeNode(3);
        TreeNode root_right0left0 = new TreeNode(4);
        root_right0.right = root_right0right0;
//        root_right0.left = root_right0left0;

        System.out.println(maxDepth1(root));
    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int right_height = maxDepth(root.right);
        int left_height = maxDepth(root.left);
        return Math.max(right_height, left_height) + 1;
    }

    public static int maxDepth1(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
