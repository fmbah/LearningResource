package more.arithmetic.排序;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void sort(int[] array) {
        for (int i = 1; i< array.length; i++) {
            int insertValue = array[i];
            int j = i - 1;
            for (; j >= 0&& insertValue < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = insertValue;
        }
    }

    public static void main(String[] args) {
        int array[] = {12, 1, 3, 5, 46, 7, 8, 16};
        sort(array);
        System.out.println((Arrays.toString(array)));
    }


}
