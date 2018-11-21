package more.arithmetic;

/**
 * @ClassName AM11
 * @Description
 *
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 * 这个题目有点坑,可以改为共30项报数序列,规则为下面示例规则,如下面扩展的,问假若15项应该是什么样子的报数序列
 * 6.     312211
 * 7.     13112221
 * 8.     1113213211
 * 9.     31131211131221
 * 10.    13211311123113112211
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 *
 * @Author root
 * @Date 18-11-16 上午9:14
 * @Version 1.0
 **/
public class AM11 {

    public static void main(String args[]) {

//        System.out.println(digui(10-1,"1"));

        System.out.println(countAndSay(2));
    }


    public static String countAndSay(int n) {


        return null;
    }


    /**
     * @Author zx
     * @Description 兔斯基的解法
     * @Date 上午9:37 18-11-16
     * @Param
     * @return
     **/
    public static String digui(int n,String value){
        if (n == 0) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        if (value.length() ==1 ){
            sb.append(count).append((value.charAt(0)));
        }else{
            for (int i = 0; i < value.length(); i++) {
                if (i+1 == value.length()){
                    sb.append(count).append(value.charAt(i));
                }else {
                    if(value.charAt(i) == value.charAt(i+1)){
                        count++;
                    }else {
                        sb.append(count).append(value.charAt(i));
                        count = 1;
                    }
                }
            }
        }
        return digui(--n,sb.toString());
    }
}
