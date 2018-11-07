package more.arithmetic;

import java.util.HashMap;

/**
 * @ClassName AM4
 * @Description
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @Author root
 * @Date 18-11-7 上午9:17
 * @Version 1.0
 **/
public class AM4 {

    public static void main (String args[]) {

        String[] strs = {"aca","cba"};

        System.out.println(longestCommonPrefix(strs));
    }

    /**
     *
     * 个人解法  执行用时: 17 ms, 在Longest Common Prefix的Java提交中击败了26.56% 的用户
     * 功能描述: 取出字符数组中,最短的一个字符串,进行循环比较,
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-7 上午9:19
     */
    public static String longestCommonPrefix(String[] strs) {

        String result = "";
        if (strs.length > 0) {
            String minlenStr = strs[0];
            for (String str : strs) {
                if (str.length() < minlenStr.length()) {
                    minlenStr = str;
                }
            }

            HashMap hashMap = new HashMap();
            for (int i = 0; i < minlenStr.toCharArray().length; i++) {
                hashMap.put(i, minlenStr.toCharArray()[i] + "");
            }

            for (int i= 0, j = hashMap.keySet().size(); i < j; i++) {
                String str = (String)hashMap.get(i);
                boolean canContract = true;
                for (int x = 0, y = strs.length; x < y; x++) {
                    String tmp = strs[x].toCharArray()[i] + "";
                    if (!tmp.equals(str)) {
                        canContract = false;
                        break;
                    }
                }
                if (canContract) {
                    result += str;
                } else if (!canContract && i == 0) {
                    break;
                }
            }
        }

        return result;
    }

}
