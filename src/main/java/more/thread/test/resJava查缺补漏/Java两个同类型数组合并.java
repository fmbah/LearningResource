package more.thread.test.resJava查缺补漏;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @ClassName Java两个同类型数组合并
 * @Description
 * @Author root
 * @Date 18-11-29 下午2:57
 * @Version 1.0
 **/
public class Java两个同类型数组合并 {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        Integer[] b = {4, 5, 6};
        Object[] objects = Stream.of(a, b).flatMap(Stream :: of).toArray();
        System.out.println(Arrays.toString(objects));
        List<Object> n = new ArrayList<>();
        Stream.of(a, b).flatMap(Stream :: of).forEach(n::add);
        System.out.println(n);


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            System.out.println(nextLine);
        }
    }
}
