import java.util.ArrayList;
import java.util.List;

/**
 * @author a8079
 * @title: ListTest
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1019:14
 */
public class ListTest {
    public static void main(String[] args) {
        System.out.println("开始测试....");
        List<List<String>> datas = new ArrayList<>();
        for (int r = 0; r < 1000000; r++) {
            List<String> row = new ArrayList<>();
            for (int c = 0; c < 15; c++) {
                row.add(c + "_column");
            }

            datas.add(row);
        }
        // 一百万 数据  占用内存  高达至：1.6G
        System.out.println("结束测试....当前占用内存：" + Runtime.getRuntime().totalMemory());
    }
}
