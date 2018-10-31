package more.thread.example.java多线程技能;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T26等待与通知
 * @Description
 * @Author root
 * @Date 18-10-30 下午3:04
 * @Version 1.0
 **/
public class T26等待与通知 {

    public static void main(String args[]) {
        MyList26 myList26 = new MyList26();
        MyThread26 myThread26 = new MyThread26(myList26);
        MyThread26_1 myThread26_1 = new MyThread26_1(myList26);
        myThread26.start();
        myThread26_1.start();
    }

}

class MyList26 {
    private List list = new ArrayList();
    public void add() {
        list.add("Fmbah");
    }

    public int size() {
        return list.size();
    }
}

class MyThread26 extends Thread{
    private MyList26 myList26;
    public MyThread26(MyList26 myList26) {
        this.myList26 = myList26;
    }

    @Override
    public void run() {
        super.run();
        try {
            for(int i = 0; i < 10; i++) {
                myList26.add();
                System.out.println("添加了[" + (i+1) + "]个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread26_1 extends Thread{
    private MyList26 myList26;
    public MyThread26_1(MyList26 myList26) {
        this.myList26 = myList26;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
//                System.out.println(myList26.size());
                int size = myList26.size();
//                System.out.println(size);
// ????为什么我加上打印,就可以正常监听数组的变化了?不加线程就停不下来,给我的感觉好像是线程把变量拷贝到工作内存中去,就一直使用的工作内存中的数组,
// 然后加上这个输出之后,就必须切换线程达到控制顺序的目的,也就会从主存中重新加载这个数组了!
                if (size == 5) {
                    System.out.println("myList26的容量有5个了,退出了");
                    throw new InterruptedException("myList26的容量有5个了,退出了");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}