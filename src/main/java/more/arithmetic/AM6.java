package more.arithmetic;

import java.util.ArrayList;

/**
 * @ClassName AM6
 * @Description
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @Author root
 * @Date 18-11-9 上午9:12
 * @Version 1.0
 **/
public class AM6 {

    public static void main (String args[]) {
        ListNode6 a_listNode6 = new ListNode6(1);
        ListNode6 a_listNode6_1 = new ListNode6(2);
        a_listNode6.next = a_listNode6_1;
        ListNode6 a_listNode6_2 = new ListNode6(4);
        a_listNode6_1.next = a_listNode6_2;

        ListNode6 b_listNode6 = new ListNode6(1);
        ListNode6 b_listNode6_1 = new ListNode6(3);
        b_listNode6.next = b_listNode6_1;
        ListNode6 b_listNode6_2 = new ListNode6(4);
        b_listNode6_1.next = b_listNode6_2;

        System.out.println(a_listNode6);
        System.out.println(b_listNode6);


        System.out.println(mergeTwoLists(a_listNode6, b_listNode6));

    }


    /**
     * 个人解法: 执行用时: 26 ms, 在Merge Two Sorted Lists的Java提交中击败了19.04% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-9 上午10:17
     */
    public static ListNode6 mergeTwoLists(ListNode6 l1, ListNode6 l2) {

        //将两个链表转化为ArrayList
        ArrayList<Integer> tmpList = new ArrayList<Integer>();
        ListNode6 tmpNode = l1;
        while (tmpNode != null) {
            tmpList.add(tmpNode.val);
            tmpNode = tmpNode.next;
        }
        tmpNode = l2;
        while (tmpNode != null) {
            tmpList.add(tmpNode.val);
            tmpNode = tmpNode.next;
        }

        //对arrayList进行排序
        for (int i = 0; i < tmpList.size(); i++) {
            int min = tmpList.get(i);
            for (int j = i + 1; j < tmpList.size(); j++) {
                if (min > tmpList.get(j)) {
                    int tmp = min;
                    min = tmpList.get(j);
                    tmpList.set(i, min);
                    tmpList.set(j, tmp);
                }
            }
        }

        //将排序后的结果组装成listNode
        //索引     下一个值
        // 0        当前元素
        // 1        当前元素的下一个元素
        //
        ListNode6 result = new ListNode6(0);
        ListNode6 tmp = result;
        for (int i = 0; i < tmpList.size(); i++) {
            tmp.next = new ListNode6(tmpList.get(i));
            tmp = tmp.next;
        }

        return result.next;
    }
}

class ListNode6 {
    int val;
    ListNode6 next;
    ListNode6(int x) { val = x; }

    @Override
    public String toString() {
        String con = "";
        ListNode6 tmp = this;
        while (tmp != null) {
            con = con + tmp.val + "->";
            tmp = tmp.next;
        }
        return con.substring(0, con.length()-2);
    }
}
