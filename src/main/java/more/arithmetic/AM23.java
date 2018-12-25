package more.arithmetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName AM23
 * @Description
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * @Author root
 * @Date 18-12-17 下午2:41
 * @Version 1.0
 **/
public class AM23 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(5);
        TreeNode root5 = new TreeNode(6);
        TreeNode root6 = new TreeNode(7);

        root.left = root1;
        root.right = root2;

        root1.left = root3;
        root1.right = root4;

        root2.left = root5;
        root2.right = root6;

        root.levelTraverse(root);

    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> childs = new ArrayList<>();
        //树的层级, 每次循环层级+1
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            level++;
            TreeNode poll = queue.poll();
            if (poll.left != null) {

            }
            if (poll.right != null) {

            }
        }

        return null;
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

//        @Override
//        public String toString() {
//
////            repeat(this);
//            levelTraverse(this);
//
//            return super.toString();
//        }


        private String repeat(TreeNode node) {

//            System.out.println(node.val);//DLR 深度优先搜索  DFS   Deepth first search
            if (node.left != null) {
                repeat(node.left);
            }
//            System.out.println(node.val);//LDR 广度优先搜索   BFS
            if (node.right != null) {
                repeat(node.right);
            }
            System.out.println(node.val);//LRD
            return null;
        }

        private void levelTraverse(TreeNode node) {//层次遍历
            if (node == null) {
                return;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(node);

            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                System.out.println(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
    }



}
