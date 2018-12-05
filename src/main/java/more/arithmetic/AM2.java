package more.arithmetic;

/**
 * @ClassName AM2
 * @Description
 * @Author root
 * @Date 18-11-5 上午9:50
 * @Version 1.0
 **/
public class AM2 {

    /**
     *
     * 功能描述: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 补充: 20181127 可使用stringbuffer的reverse()方法,也可根据数字进行判断比较
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-5 上午9:50
     */
    public static void main (String args[]) {
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome1(1221));
    }

    /**
     *
     * 功能描述: 非官方正解
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-5 上午10:07
     */
    public static boolean isPalindrome(int x) {
        StringBuffer stringBuffer = new StringBuffer(x + "");
        StringBuffer reverse = stringBuffer.reverse();
        if ((x + "").equals(reverse.toString())) {
            return true;
        }
        return false;
    }


    //12321
    //将整个数字进行反正会出现溢出情况,如果是一个回文数,那么指定是一半反转过来和另一半相等
    //原   新
    //1232 1
    //123 12
    //(12) (123)=======这个情况就证明已经处理了一半的数字了,并且我们比较的是括号中的两个数字,当数字为奇数时,通过取除数的方式获取
    //测试下如果为o数
    //122 1
    //(12) (12)========完成此次反转,直接比较最后的结果即可

    public static boolean isPalindrome1(int x) {

        if (x < 0 || (x % 10 == 0 && x != 0)) {//负数不是回文数,当余数为0时,则整数的第一个数字必须为0,才能构成回文数,满足此条件的只有0,所以不满足此条件 的都不是回文数
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x /= 10;
        }

        return x == reverseNum || x == (reverseNum / 10);
    }
}
