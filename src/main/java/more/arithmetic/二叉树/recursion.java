package more.arithmetic.二叉树;

import java.util.Stack;

/**
 * @ClassName recursion
 * @Description
 * @Author root
 * @Date 19-1-9 上午9:54
 * @Version 1.0
 **/
public class recursion {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right= new TreeNode(4);
//        preorderTraversalRec(root);
        System.out.println();
        System.out.println();
//        preorderTraversalRec1(root);

//        centerorderTraversalRec(root);
        System.out.println();
        System.out.println();
//        centerorderTraversalRec1(root);

        backorderTraversalRec(root);
        System.out.println();
        System.out.println();
        backorderTraversalRec1(root);
    }

    /**
     *
     * 功能描述: 前序遍历二叉树(优先深度搜索)
     * 1. 如果二叉树为空,则空操作
     * 2. 如果二叉树不为空, 访问根节点, 前序遍历左节点, 前序遍历右节点
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 上午9:59
     */
    public static void preorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val + "->");
        preorderTraversalRec(root.left);
        preorderTraversalRec(root.right);
    }

    /**
     *
     * 功能描述: 非递归解法1
     *      0
     *     / \
     *    1   1
     *   / \
     *  3   3
     *  分解下方法:
     *      第一次外循环:
     *          输出: 0->1->3
     *          栈(底->顶): 0 1 3
     *          cur: 取出栈顶3的右节点: null
     *          栈(底->顶): 0 1
     *      第二次外循环:
     *          输出:
     *          栈(底->顶): 0 1
     *          cur: 取出栈顶1的右节点: 3
     *          栈(底->顶): 0
     *      第三次外循环:
     *          输出: 3
     *          栈(底->顶): 0 3
     *          cur: 取出栈顶3的右节点: null
     *          栈(底->顶): 0
     *      第四次外循环:
     *          输出:
     *          栈(底->顶): 0
     *          cur: 取出栈顶0的右节点: 1
     *          栈(底->顶): null
     *      第五次外循环:
     *          输出: 1
     *          栈(底->顶): 1
     *          cur: 取出栈顶1的右节点: null
     *          栈(底->顶): null
     *      同时满足栈为空cur为空则退出循环.....
     *
     * @param:
     * @return: 
     * @auther: Fmbah
     * @date: 19-1-9 上午10:27
     */
    public static void preorderTraversalRec1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();//辅助栈
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while(cur != null) {//不断将左子节点入栈, 直到cur为空
                stack.push(cur);
                System.out.println(cur.val + "->");
                cur = cur.left;
            }
            if (!stack.isEmpty()) {//栈不为空,弹出栈元素
                cur = stack.pop();//此时弹出最左边的节点
                cur = cur.right;//令当前节点为右子节点
            }
        }
    }

    /**
     *
     * 功能描述: 非递归解法2
     *     3
     *   1 3 3
     * 0 1 1 1 1
     *
     * @param: 
     * @return: 
     * @auther: Fmbah
     * @date: 19-1-9 上午11:54
     */
    public static void preorderTraversalRec2(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();//辅助栈保存树节点
        stack.add(root);
        while(!stack.isEmpty()) {//栈不为空
            TreeNode tmp = stack.pop();
            System.out.println(tmp.val + "->");//先根节点, 因为是前序遍历
            if (tmp.right != null) {//先添加右孩子,因为栈是先进后出
                stack.add(tmp.right);
            }
            if (tmp.left != null) {
                stack.add(tmp.left);
            }
        }
    }

    /**
     * 递归解法
     * 功能描述: 中序遍历二叉树(优先广度搜索)
     * 如果二叉树为空, 则空操作
     * 中序遍历左节点, 访问根节点, 中序遍历右节点
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 上午10:04
     */
    public static void centerorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        centerorderTraversalRec(root.left);
        System.out.println(root.val + "->");
        centerorderTraversalRec(root.right);
    }

    /**
     * 非递归解法
     * 功能描述: 中序遍历二叉树
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 下午2:09
     */
    public static void centerorderTraversalRec1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.val + "->");
                cur = cur.right;
            }
        }
    }

    /**
     * 递归法
     * 功能描述: 后序遍历二叉树
     *
     *     0
     *    / \
     *   1   1
     *  / \
     * 3   3
     *  1. 0 -> 1 -> 3 打印左节点3
     *  2. 0 -> 1 -> 3 打印右节点3
     *  3. 0 -> 1 打印中间节点1
     *  4. 0 -> 1 打印右节点1
     *  5. 0 打印根节点0
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 下午4:33
     */
    public static void backorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        backorderTraversalRec(root.left);
        backorderTraversalRec(root.right);
        System.out.println(root.val);
    }

    /**
     * 非递归法(双栈法)
     * 功能描述: 后序遍历二叉树
     *
     * 第一次循环:
     *  栈1: 0 -> 1-2
     *  栈2: null -> 0
     * 第二次循环:
     *  栈1: 1-2 -> 1
     *  栈2: 0 -> 0-2
     * 第三次循环:
     *  栈1: 1 -> 3-4
     *  栈2: 0-2 -> 0-2-1
     * 第四次循环:
     *  栈1: 3-4 -> 3
     *  栈2: 0-2-1 -> 0-2-1-4
     * 第五次循环:
     *  栈1: 3 -> null
     *  栈2: 0-2-1-4 -> 0-2-1-4-3
     *
     * 最终将栈2打印出来.....34120
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 19-1-9 下午5:01
     */
    public static void backorderTraversalRec1(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();//保存树节点
        Stack<TreeNode> stack2 = new Stack<>();//保存后序遍历结果
        stack1.add(root);
        while (!stack1.isEmpty()) {
            TreeNode tmp = stack1.pop();
            System.out.println("入栈值: " + tmp.val);
            stack2.add(tmp);
            if (tmp.left != null) {
                stack1.add(tmp.left);
            }
            if (tmp.right != null) {
                stack1.add(tmp.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val + "->");
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
