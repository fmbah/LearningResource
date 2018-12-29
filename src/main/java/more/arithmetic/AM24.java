package more.arithmetic;

import java.util.*;

/**
 * @ClassName AM24
 * @Description
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
 * @Author root
 * @Date 18-12-26 上午10:33
 * @Version 1.0
 **/
public class AM24 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode root_left = new TreeNode(9);
        TreeNode root_right = new TreeNode(20);

        TreeNode root_right_left = new TreeNode(15);
        TreeNode root_right_right = new TreeNode(7);

        root.left = root_left;
        root.right = root_right;

        root_right.left = root_right_left;
        root_right.right = root_right_right;

        System.out.println(levelOrderBottom(root));

    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        if(root==null)
            return res;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode temp = root;
        q.add(root);
        int size = 1;
        while(true){
            size = q.size();
            ArrayList<Integer> inner = new ArrayList<Integer>();
            while(size!=0){
                temp = q.poll();
                inner.add(temp.val);
                size--;
                left = temp.left;
                right = temp.right;
                if(left!=null)
                    q.add(left);
                if(right!=null)
                    q.add(right);
            }
            res.add(inner);
            if(q.size()==0)
                break;
        }
        Collections.reverse(res);
        return res;
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
