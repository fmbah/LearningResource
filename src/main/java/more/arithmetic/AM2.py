def isPalindrome(self, x):
    """
    :type x: int
    :rtype: bool
    执行用时: 156 ms, 在Palindrome Number的Python提交中击败了93.48% 的用户
    """
    #当一个数字小于0 或者 数字的余数为0且不为0  ==> 非回文数
    if (x < 0 or (x % 10 ==0 and x != 0)):
        self = False
        return self

    reversenum = 0#反转数字
    while (x > reversenum):
        tmp = x % 10
        x = x // 10
        reversenum = reversenum * 10 + tmp

    return x == reversenum or x == reversenum // 10


if __name__ == '__main__':
    print('main run....')
    print(isPalindrome(True, 101))