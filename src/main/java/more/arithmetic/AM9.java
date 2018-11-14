package more.arithmetic;

/**
 * @ClassName AM9
 * @Description
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * @Author root
 * @Date 18-11-13 下午1:54
 * @Version 1.0
 **/
public class AM9 {
    public static void main (String args[]) {
        System.out.println(strStr("lsssll", "ll"));

        System.out.println("12".indexOf(""));
    }

    /**
     * 个人笨重解法: 执行用时: 5 ms, 在Implement strStr()的Java提交中击败了94.82% 的用户
     * 这里面有人提及了KMP算法,后续我在研究下
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-14 上午10:32
     */
    public static int strStr(String haystack, String needle) {

        if (needle == null || needle.equals("")) {
            return 0;
        }
        if (haystack == null || haystack.equals("")) {
            return -1;
        }

        char[] needles = needle.toCharArray();
        char[] haystacks = haystack.toCharArray();
        int haystacksSize = haystacks.length;
        int needleSize = needles.length;

        int index = -1;

        if (needleSize > haystacksSize) {

        } else if (needleSize == haystacksSize) {
            boolean isIndex = true;
            for (int i = 0; i < needleSize; i++) {
                if (needles[i] != haystacks[i]) {
                    isIndex = false;
                    break;
                }
            }
            if (isIndex) {
                index = 0;
            }
        } else {
            char first = needles[0];
            for (int i = 0; i < haystacksSize; i++) {
                int tmp = 0;
                if (first == haystacks[i] && (i + needleSize - 1) < haystacksSize) {//先找到第一个元素并且当前索引+目标字符串长度 < 比较的字符串(长)
                    //循环目标字符串,匹配每一个相应位置的比较字符串,只要匹配完成,则退出两层循环
                    for (int y = 0; y < needleSize; y++) {
                        if (haystacks[i + y] == needles[y]) {
                            tmp++;
                        }
                    }
                }
                if (tmp == needleSize) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }
}
