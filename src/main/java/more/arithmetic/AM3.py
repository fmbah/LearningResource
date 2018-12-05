def romanToInt(self, s):
    """
    :type s: str
    :rtype: int
    执行用时: 100 ms, 在Roman to Integer的Python提交中击败了82.19% 的用户
    程序中不要加过多的输出
    """
    a = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}
    ans=0
    for i in range(len(s)):
        if i < len(s)-1 and a[s[i]] < a[s[i+1]]:
            ans -= a[s[i]]
        else:
            ans += a[s[i]]
    return ans

if __name__ == '__main__':
    print('main run....')
    print(romanToInt(1, "MMDCXXI"))