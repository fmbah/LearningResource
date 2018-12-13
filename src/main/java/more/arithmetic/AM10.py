class Solution:
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        执行用时: 56 ms, 在Search Insert Position的Python3提交中击败了23.06% 的用户
        """
        index = 0
        for num in nums:
            if target <= num:
                return index
            index = index + 1
        return index


solution = Solution()
l = [1,2,3,4]
target = 0
print(solution.searchInsert(l, target))