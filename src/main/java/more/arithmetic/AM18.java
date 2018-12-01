package more.arithmetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName AM18
 * @Description
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * @Author root
 * @Date 18-11-26 下午5:55
 * @Version 1.0
 **/
public class AM18 {
    public static void main (String args[]) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(1);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;

        System.out.println(deleteDuplicates1(listNode));
    }

    /**
     * 执行用时: 19 ms, 在Remove Duplicates from Sorted List的Java提交中击败了0.99% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-27 上午9:38
     */
    public static ListNode deleteDuplicates(ListNode head) {

        ListNode tmp = head;
        HashSet<Integer> hashSet = new HashSet<>();
        while (tmp != null) {
            hashSet.add(tmp.val);
            tmp = tmp.next;
        }
        Iterator<Integer> iterator = hashSet.iterator();
        Integer[] vals = new Integer[hashSet.size()];
        int index = 0;
        while (iterator.hasNext()) {
            vals[index++] = iterator.next();
        }
        for (int i = 0; i < vals.length; i++) {
            for (int j = i + 1; j < vals.length; j++) {
                if (vals[i] > vals[j]) {
                    int var = vals[i];
                    vals[i] = vals[j];
                    vals[j] = var;
                }
            }
        }

        ListNode listNode = new ListNode(0);
        List<ListNode> list = new ArrayList<>();
        for (Integer var : vals) {
            list.add(new ListNode(var));
        }
        for (int i = 0, j = list.size(); i < j; i++) {
            if (i == 0) {
                listNode.next = list.get(i);
            } else {
                list.get(i-1).next = list.get(i);
            }
        }

        return listNode.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            String con = "";
            ListNode tmp = this;
            while (tmp != null) {
                con = con + tmp.val + "->";
                tmp = tmp.next;
            }
            return con.substring(0, con.length()-2);
        }

    }

    /**
     *
     * 功能描述: 直接法
     *
     * 引用1---->原对象
     * 引用2---->引用1后,更改引用用来判断
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-27 上午9:38
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
