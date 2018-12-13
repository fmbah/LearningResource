class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        执行用时: 28 ms, 在Remove Element的Python提交中击败了97.47% 的用户
        """
        slowIndex = 0
        for num in nums:
            if num != val:
                nums[slowIndex] = num
                slowIndex = slowIndex + 1
        return slowIndex

solution = Solution()
nums = [0,1,2,2,3,0,4,2]
print(solution.removeElement(nums, 2))
print(nums)