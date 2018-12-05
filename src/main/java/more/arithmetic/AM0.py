

def twoSum(self, nums, target):
    """
    :type nums: List[int]
    :type target: int
    :rtype: List[int]
    执行用时: 48 ms, 在Two Sum的Python提交中击败了70.45% 的用户
    """
    print(len(nums), target)
    dicts = {}
    for i in range(len(nums)):
        tmpDict = dicts.get(target - nums[i])
        if tmpDict == None:
            dicts[nums[i]] = i
        else:
            self = [tmpDict, i]

    return self


if __name__ == '__main__':
    print('main run')
    res = twoSum([], [2, 7, 11, 15], 9)
    print(res)
