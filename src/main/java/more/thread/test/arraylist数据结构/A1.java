package more.thread.test.arraylist数据结构;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName A1
 * @Description
 * @Author root
 * @Date 18-11-5 下午3:40
 * @Version 1.0
 **/
public class A1 {

    Object[] elementData;

    public static void main(String args[]) {
        Integer[] nums = {1, 2, 3, 4};
        System.arraycopy(nums, 2, nums, 0, 2);
        List<Integer> integers = Arrays.asList(nums);
        System.out.println(integers.toString());

        List<Integer> list = new ArrayList<Integer>();
        Object[] objects = Arrays.copyOf(nums, nums.length, Object[].class);
        System.out.println(Arrays.asList(objects).toString());

        Object[] objects1 = new Object[nums.length];
//        System.arraycopy(integers, 0, objects1, 0, nums.length);
//        objects1 = list.toArray(nums);
        System.out.println(Arrays.asList(objects1).toString());
        System.out.println(3>>1);

        Integer[] integers1 = Arrays.copyOf(nums, 5);
        System.out.println(Arrays.asList(nums).toString());

        System.out.println(new MyA1().getSize());
    }

}

class MyA1 {
    private int size;
    public int getSize () {
        return this.size + 1;
    }
}