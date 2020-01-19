package more.ftf.zookeeper.balance.test;

import more.ftf.zookeeper.balance.server.ServerData;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author a8079
 * @title: ZkClientNodeVersionTest
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1617:47
 */
public class ZkClientNodeVersionTest {


    @Test
    public void test0 () {
        // 获取zk客户端连接
        ZkClient zkClient = new ZkClient("192.168.56.104:2181");

        // 获取父路径下的子节点信息，展示子节点信息
//        zkClient.createPersistent("/servers");
        List<String> children = zkClient.getChildren("/servers");
        children.stream().forEach(System.out::println);


        ServerData sd = new ServerData();
        sd.setBalance(0);
        sd.setHost("127.0.0.1");
        sd.setPort(6000);
        for (int i = 0; i< 100; i++) {
            zkClient.createEphemeral("/servers/" + (sd.getPort() + i), sd);
        }

        children = zkClient.getChildren("/servers");
        children.stream().forEach(System.out::println);

        Stat stat = new Stat();
        int i = sd.getPort() + 1;
        List<Thread> threads = new ArrayList<>();
        for (int c = 0; c< 3; c++) {
            threads.add(new Thread(()->{
                ServerData sd1 = zkClient.readData("/servers/" + i, stat);
                String errorMsg = Thread.currentThread().getName() + ":" + sd1.getBalance();
                sd1.setBalance(sd1.getBalance() + 1);
                // 带上版本，因为可能有其他客户端连接到服务器修改了负载
                try {
                    zkClient.writeData("/servers/" + i, sd1, stat.getVersion());
                } catch (Exception e) {
                    System.out.println(errorMsg + ":" + e.getMessage());
                }
            }));
        }

        threads.forEach(Thread::start);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ServerData sd2 = zkClient.readData("/servers/" + i, stat);
        System.out.println("当前更改后节点：" + sd2.getBalance());
    }

    @Test
    public void test1() {
        System.out.println("1111\n");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            String s = new BufferedReader(inputStreamReader).readLine();
            Assert.assertEquals("test", s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("2222");
    }



}
