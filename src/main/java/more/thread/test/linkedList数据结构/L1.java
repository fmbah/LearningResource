package more.thread.test.linkedList数据结构;

import java.util.LinkedList;

/**
 * @ClassName L1
 * @Description
 * @Author root
 * @Date 18-11-6 下午4:10
 * @Version 1.0
 **/
public class L1 {

    public static void main(String args[]) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.getLast());
    }

}