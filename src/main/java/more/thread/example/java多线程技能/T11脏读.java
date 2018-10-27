package more.thread.example.java多线程技能;

/**
 * @ClassName T11脏读
 * @Description
 * @Author root
 * @Date 18-10-26 上午9:37
 * @Version 1.0
 **/
public class T11脏读 {

    /**
     *
     * 功能描述: 强力分析一波:
     *          启动main方法,启动子线程,(开始线程)
     *          子线程设置username,子线程暂停1s,
     *              可能的情况: main线程最先跑了!导致都没获取到子线程设置的username,
     *                        子线程先设置好了username,main线程跑,导致读取到了子线程的username,默认的password
     *                        子线程将username,password都设置号了,main线程才跑,读取到了子线程设置的相应值
     *               main线程休眠的时间,提高了问题增大的几率
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-10-26 上午9:52
     */
    public static void main (String args[]) {
        MyDirtReadExample myDirtReadExample = new MyDirtReadExample();
        MyThread11 myThread11 = new MyThread11(myDirtReadExample);
        myThread11.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDirtReadExample.getValue();
    }
}

class MyDirtReadExample {
    private String username = "AU";
    private String password = "AP";

    synchronized public void setValue(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;

        System.out.println("setValue____username:" + username + "===password:" + password);
    }

    public void getValue () {
        System.out.println("getValue___username:" + username + "===password:" + password);
    }
}

class MyThread11 extends Thread {
    private MyDirtReadExample myDirtReadExample;
    public MyThread11 (MyDirtReadExample myDirtReadExample) {
        this.myDirtReadExample = myDirtReadExample;
    }

    @Override
    public void run() {
        super.run();
        myDirtReadExample.setValue("MyThread11_AU", "MyThread11_AP");
    }
}