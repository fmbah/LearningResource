# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __str__(self):
        res = ''
        tmp = self
        while tmp is not None:
            res = res + str(tmp.val)
            tmp = tmp.next
        return res

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        size = 0
        tmp = head
        while tmp is not None:
            size += 1
            tmp = tmp.next

        res = ListNode(0)
        res.next = head
        removeIndex = size - n

        tmp1 = res
        while removeIndex > 0:
            removeIndex = removeIndex - 1
            tmp1 = tmp1.next

        tmp1.next = tmp1.next.next
        return res.next
    def removeNthFromEnd1(self, head, n):
        dummy = ListNode(0)
        dummy.next = head
        first = dummy
        second = dummy
        for i in range(n+1):
            first = first.next

        while first is not None:
            first = first.next
            second = second.next

        second.next = second.next.next
        return dummy.next

zeroNode = ListNode(0)
oneNode = ListNode(1)
zeroNode.next = oneNode
twoNode = ListNode(2)
oneNode.next = twoNode

solution = Solution()
print(solution.removeNthFromEnd1(zeroNode, 2))