package more.arithmetic;

/**
 * @ClassName AM13
 * @Description
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 * @Author root
 * @Date 18-11-19 下午4:48
 * @Version 1.0
 **/
public class AM13 {
    public static void main (String args[]) {
        System.out.println("  result: " + lengthOfLastWord(" a"));
    }

    /**
     * 个人解法: 执行用时: 140 ms, 在Length of Last Word的Java提交中击败了0.97% 的用户
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-19 下午5:08
     */
    public static int lengthOfLastWord(String s) {

        int result = 0;
        String trimStr = s.trim();
        for (int i = 0, j = trimStr.length(); i < j; i++) {
            if (trimStr.charAt(i) != ' ') {
                result++;
            } else {
                if (i < j - 1) {
                    result = 0;
                }
            }
            System.out.print(trimStr.charAt(i));
        }
        return result;
    }
}
