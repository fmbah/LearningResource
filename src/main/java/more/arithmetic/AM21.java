package more.arithmetic;

import com.sun.xml.internal.bind.v2.TODO;
import sun.reflect.generics.tree.Tree;

import java.util.*;

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

        isSymmetric(root);
    }

    public static boolean isSymmetric(TreeNode root) {

        TreeNode l = root;
        TreeNode r = root;

        return isMirror(l, r);
    }

    public static boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode l = q.poll();
            TreeNode r = q.poll();
            if (l == null && r == null) continue;
            if (l == null || r == null) return false;
            if (l.val != r.val) return false;
            q.add(l.left);
            q.add(r.right);
            q.add(l.right);
            q.add(r.left);
        }
        return true;
    }

    /**
     *
     * 功能描述:
     * 执行用时: 8 ms, 在Symmetric Tree的Java提交中击败了97.96% 的用户
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-12-12 下午6:16
     */
    public static boolean isMirror(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null) {
            return false;
        }

        return l.val == r.val && isMirror(l.left, r.right) && isMirror(l.right, r.left);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            visit(sb, this);
            System.out.println(sb.toString());
            return super.toString();
        }

        public void visit(StringBuilder sb, TreeNode node) {
            if (node.left != null) {
                visit(sb, node.left);
            }
            if (node.right != null) {
                visit(sb, node.right);
            }
            sb.append(node.val);
        }
    }
}
