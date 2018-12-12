class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        使用zip函数,进行字符串对接
        执行用时: 28 ms, 在Longest Common Prefix的Python提交中击败了93.96% 的用户
        """
        if strs is None or len(strs) == 0:
            return ''
        for i in range(len(strs)):
            strs[i] = list(strs[i])
        tmp = zip(*strs)
        res = ''
        for i in tmp:
            if len(set(i)) == 1:
                res += i[0]
            else:
                break
        return res

    def longestCommonPrefix1(self, strs):
        """
        两两比较
        @param: strs: A list of strings
        @return: The longest common prefix
        """
        if strs is None or len(strs) == 0:
            return ''
        res = strs[0]
        for i in range(1, len(strs)):
            tmp = res
            res = ''
            for j in range(min(len(strs[i]),len(tmp))):
                if tmp[j] == strs[i][j]:
                    res += tmp[j]
                else:
                    break
        return res

s = Solution()
print(s.longestCommonPrefix1(['aca', 'acba', 'bac']))
for j in range(len('abc')):
    print(j, 'abc'[j])
