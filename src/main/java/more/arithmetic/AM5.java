package more.arithmetic;

/**
 * @ClassName AM5
 * @Description
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 * @Author root
 * @Date 18-11-8 上午9:21
 * @Version 1.0
 **/
public class AM5 {

    public static void main (String args[]) {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;

//        System.out.println(removeNthFromEnd(listNode, 3));

        System.out.println(removeNthFromEnd1(listNode, 5));
    }


    /**
     * 9 ms, 在Remove Nth Node From End of List的Java提交中击败了99.78% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-8 上午11:40
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        //0->1->2->3->4->5
        //1->2->3->4->5 //去头
        //0->1->2->3->4 //去尾
        //0->1->2->3->5 //去中间某元素

        //获取链表长度
        int length = 0;
        ListNode listNode = head;
        while (listNode != null) {
            length ++;
            listNode = listNode.next;
        }

        ListNode resultHead = null;
        if (length == n) {//去头
            ListNode tmp = head;
            int tmpIndex = 0;
            while (tmp != null) {
                if (tmpIndex == 1) {
                    resultHead = tmp;
                    break;
                }
                tmpIndex++;
                tmp = tmp.next;
            }
        } else if (n == 1) {//去尾
            ListNode tmp = head;
            int tmpIndex = 0;
            resultHead = tmp;
            while (tmp != null) {
                if (tmpIndex == length-2) {
                    tmp.next = null;
                    break;
                }
                tmpIndex++;
                tmp = tmp.next;
            }
        } else {//去中间元素
            ListNode tmp = head;
            int tmpIndex = 0;
            resultHead = tmp;
            while (tmp != null) {
                if (tmpIndex == length - n - 1) {
                    tmp.next = tmp.next.next;
                    break;
                }
                tmpIndex++;
                tmp = tmp.next;
            }
        }

        return resultHead;
    }

    /**
     *
     * 功能描述: 官方解答
     *
     *
     * 20181127补充:
     *
     * head: 0->1->2->3
     * length: 4
     * n:2
     * 移除的索引为4-2 = 2
     * dummy: 0->0>->1->[2]->3
     * 其实目的就是让1的下一个指针直接指向3
     *
     * 值得注意: 利用引用来操作堆数据
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-8 上午10:34
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);//暂存元素,其中使用的是next元素
        dummy.next = head;//将next元素指到head上
        int length  = 0;//获取到容器的长度
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;//获取要移除的元素索引
        first = dummy;

        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

}

class ListNode {
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