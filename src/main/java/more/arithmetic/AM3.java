package more.arithmetic;

import java.util.HashMap;

/**
 * @ClassName AM3
 * @Description
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 * @Author root
 * @Date 18-11-6 上午9:18
 * @Version 1.0
 **/
public class AM3 {

    public static void main (String args[]) {
        String str = "VIIIIVI";
        System.out.println(str.length() + "==" + str.toCharArray()[0] + "==" + str.indexOf("IV"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    /**
     * 自写:  执行速度特别慢!!!才击败了6%的人
     * 功能描述:
     *
     *      先定义每一个罗马字符代表的数字
     *      字符串转化为字符数组
     *      循环每一个字符数组元素,并且判断是否有
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-6 上午9:20
     */
    public static int romanToInt(String s) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("I", 1);
        hashMap.put("V", 5);
        hashMap.put("X", 10);
        hashMap.put("L", 50);
        hashMap.put("C", 100);
        hashMap.put("D", 500);
        hashMap.put("M", 1000);

        hashMap.put("IV", 4);
        hashMap.put("IX", 9);
        hashMap.put("XL", 40);
        hashMap.put("XC", 90);
        hashMap.put("CD", 400);
        hashMap.put("CM", 900);

        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length; i < j; i++) {
            boolean canAdd = true;
            if ((chars[i] + "").equals("I")) {
                if ((i+1) < j) {
                    if ((chars[i] + "" + chars[i + 1]).equals("IV")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else if ((chars[i] + "" + chars[i + 1]).equals("IX")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else {
                        canAdd = false;
                        result += hashMap.get(chars[i] + "");
                    }
                } else {
                    canAdd = false;
                    result += hashMap.get(chars[i] + "");
                }
            } else if ((chars[i] + "").equals("X")) {
                if ((i+1) < j) {
                    if ((chars[i] + "" + chars[i + 1]).equals("XL")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else if ((chars[i] + "" + chars[i + 1]).equals("XC")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else {
                        canAdd = false;
                        result += hashMap.get(chars[i] + "");
                    }
                } else {
                    canAdd = false;
                    result += hashMap.get(chars[i] + "");
                }
            } else if ((chars[i] + "").equals("C")) {
                if ((i+1) < j) {
                    if ((chars[i] + "" + chars[i + 1]).equals("CD")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else if ((chars[i] + "" + chars[i + 1]).equals("CM")) {
                        result += hashMap.get(chars[i] + "" + chars[i + 1]);
                    } else {
                        canAdd = false;
                        result += hashMap.get(chars[i] + "");
                    }
                } else {
                    canAdd = false;
                    result += hashMap.get(chars[i] + "");
                }
            } else {
                canAdd = false;
                result += hashMap.get(chars[i] + "");
            }
            if (canAdd) {
                ++i;
            }
        }
        return result;
    }
}
