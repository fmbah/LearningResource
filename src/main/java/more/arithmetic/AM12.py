class Solution:
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        python3数组循环中,如果通过list.index(value)获取索引值的话,可能会出现值相同的情况,返回错误的索引
        """
        compareNums = list(range(len(nums)))
        result = nums[0]
        compareNums[0] = nums[0]
        index = 1
        for n in nums[1:]:
            if compareNums[index - 1] + n < n:
                compareNums[index] = n
            else:
                compareNums[index] = compareNums[index - 1] + n
            if result < compareNums[index]:
                result = compareNums[index]
            index = index + 1

        return result

print('am12 run....')
nums = [-2,1,-3,4,-1,2,1,-5,4]
solution = Solution()
print(solution.maxSubArray(nums))