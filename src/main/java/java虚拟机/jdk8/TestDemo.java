package java虚拟机.jdk8;

import org.junit.Test;

import java.io.Serializable;
import java.util.Optional;

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

    private static final Integer ZERO = 0;
    @Test
    public void test1() {
        TestBean testBean = new TestBean();
        testBean.setId(ZERO);


        System.out.println(Optional.ofNullable(testBean.getId()).orElse(ZERO) == ZERO);

    }
    public class TestBean implements Serializable {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
