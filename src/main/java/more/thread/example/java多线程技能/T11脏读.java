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
     *
     *          注释 2018-10-30: Mysql InnoDB引擎,默认隔离级别为可重复读
     *
     *                     A用户:  set session transaction isolation level read uncommitted；
     *                            start transaction;
     *                            select * from account;
     *                     B用户:  set session transaction isolation level read uncommitted；
     *                           start transaction;
     *                           update account set account=account+200 where id = 1;
     *                     A用户此时会读取到B用户未提交的数据,这就称为脏读
     *
     *
     *                    A用户:  set session transaction isolation level read committed；
     *                           start transaction;
     *                           update account set account=account-200 where id=1;
     *                    B用户:  select * from account； 此时是正常的
     *                    A用户:  提交事物
     *                    B用户:                                    select * from account； 可以正常读取到A用户操作的数据
     *                          此时的问题是:在B用户的事物中,可能会读取到A用户的两个结果(事物前,事物后),这种现象叫不可重复读
     *
     *                    A用户:      set session transaction isolation level repeatable read;
     *                               start transaction;
     *                    B用户:      select * from account;
     *                    A用户:      insert into account(id,account) value(3,1000);
     *                                commit;
     *                                select * from account;   结果正常
     *                    B用户:      select * from account;还是原来的那么几条数据
     *                                此时也想插入  insert into account(id,account) value(3,1000); 一条数据,则出现数据重复问题
     *                                虽然说在B事物中保证了读取数据的一致性,但是可能会出现幻读的情况
     *
     *                    serializable（串行化）
     *                    B用户:  set session transaction isolation level serializable;
     *                           start transaction;
     *                           select * from account;
     *                    A用户:      insert into account(id,account) value(3,1000);
     *                                  A用户会发现陷入了等待(B事物一直未提交),可能会超时也可能成功(B事物提交完成了)
     *                             此种隔离级别最严格,其它会话操作此表的写操作都会被挂起!
     *
     *                           
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