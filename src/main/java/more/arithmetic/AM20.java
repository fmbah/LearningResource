package more.arithmetic;

/**
 * @ClassName AM20
 * @Description
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *         / |
 *        3
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 *
 * DFS:深度优先搜索(Deepness First Search)
 * BFS:广度优先搜索(Breadth First Search)
 *
 *
 *
 * @Author root
 * @Date 18-11-30 上午10:24
 * @Version 1.0
 **/
public class AM20 {

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


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return false;
    }

    //Definition for a binary tree node
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }

        @Override
        public String toString() {
            //输出格式: 根节点-左侧子节点-右侧子节点
            this.assemble(this);
            return super.toString();
        }

        public void assemble (TreeNode treeNode) {

//            TreeNode tmp = treeNode;
//
//            while (tmp != null) {
//                System.out.print("[" + tmp.val + "]");
//                if (tmp.left != null) {
//                    assemble(tmp.left);
//                } else if (tmp.left == null) {
//                    assemble(tmp.right);
//                    break;
//                } else if (tmp.right != null) {
//                    assemble(tmp.right);
//                } else if (tmp.right == null) {
//                    break;
//                }
//                tmp = tmp.right;
//            }
            if (treeNode != null) {
                System.out.print("[" + treeNode.val + "]");//先序遍历
                assemble(treeNode.left);
                assemble(treeNode.right);

//                assemble(treeNode.left);
//                System.out.print("[" + treeNode.val + "]");//中序遍历
//                assemble(treeNode.right);

//                assemble(treeNode.left);
//                assemble(treeNode.right);
//                System.out.print("[" + treeNode.val + "]");//后序遍历
            }
        }
    }

}
