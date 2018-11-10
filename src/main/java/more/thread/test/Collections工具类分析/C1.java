package more.thread.test.Collections工具类分析;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName C1
 * @Description
 * @Author root
 * @Date 18-11-8 下午7:48
 * @Version 1.0
 **/
public class C1 {

    public static void main (String args[]) {
        Map emptyMap = Collections.EMPTY_MAP;
        Integer[] nums = {3, 1, 5, 6, 2};
        List<Integer> ints = Arrays.asList(nums);
        System.out.println(ints);
        Collections.sort(ints);
        System.out.println(ints);
    }
}
