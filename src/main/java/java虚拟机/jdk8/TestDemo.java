package java虚拟机.jdk8;

import org.junit.Test;

/**
 * @author a8079
 * @title: TestDemo
 * @projectName LearningResource
 * @description: TODO
 * @date 2019/11/111:32
 */
public class TestDemo {
    @Test
    public void test0() {
        char c = 'A';
        int sum = 10;
        switch (c) {
            case 'B':
                sum++;
            case 'A':
                sum++;
            case 'C':
                sum++;
                break;
            default:sum--;
        }
        System.out.println(sum);
    }
}
