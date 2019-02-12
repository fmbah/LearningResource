class Solution:
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        print('s: [%s]' % s)
        list1 = s.split()
        if list1 != []:
            return len(list1[-1])
        else:
            return 0

solution = Solution()
print(solution.lengthOfLastWord("Hello World"))
print(solution.lengthOfLastWord("a"))