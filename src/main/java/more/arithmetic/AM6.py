# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self):
        tmp = self
        res = ""
        while tmp is not None:
            res = res + str(tmp.val)
            tmp = tmp.next
        return res



class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        执行用时: 32 ms, 在Merge Two Sorted Lists的Python提交中击败了99.39% 的用户
        """
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        root = ListNode(0)
        pointer = root
        while l1 is not None and l2 is not None:
            if l1.val < l2.val:
                pointer.next = l1
                l1 = l1.next
            else:
                pointer.next = l2
                l2 = l2.next
            pointer = pointer.next
        if l1 is not None:
            pointer.next = l1
        if l2 is not None:
            pointer.next = l2
        return root.next


    def mergeTwoLists1(self, l1, l2):
        """
        :param l1: ListNode
        :param l2: ListNode
        :return: ListNode
        执行用时: 32 ms, 在Merge Two Sorted Lists的Python提交中击败了99.39% 的用户
        """
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        tmp = l1
        if tmp.val < l2.val:
            tmp.next = self.mergeTwoLists1(l1.next, l2)
        else:
            tmp = l2
            tmp.next = self.mergeTwoLists1(l1, l2.next)
        return tmp


listNode0 = ListNode(0)
listNode1 = ListNode(1)
listNode0.next = listNode1

listNode00 = ListNode(0)
listNode11 = ListNode(1)
listNode00.next = listNode11
solution = Solution()
print(solution.mergeTwoLists1(listNode0, listNode00))