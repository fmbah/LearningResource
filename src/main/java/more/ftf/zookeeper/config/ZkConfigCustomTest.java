package more.ftf.zookeeper.config;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author a8079
 * @title: ZkConfigCustomTest
 * @projectName nio
 * @description: TODO
 * @date 2020/1/920:47
 */
public class ZkConfigCustomTest {


    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient("192.168.56.104:2181");
        boolean exists = zkClient.exists("/test");
        if (!exists) {
            zkClient.createPersistent("/test");
        }

        Config config = new Config("root", "123");
        zkClient.writeData("/test", config);

        Config readConfig = (Config)zkClient.readData("/test");
        System.out.println(readConfig);

    }

}
